package es.cesguiro.daw1.bookstore.test.suite.persistence.template;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import org.junit.jupiter.api.extension.*;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AuthorTestTemplateProvider implements TestTemplateInvocationContextProvider {

    private final AuthorDao authorDaoMock;
    private final AuthorDao authorDaoJdbc;

    public AuthorTestTemplateProvider(AuthorDao authorDaoMock, AuthorDao authorDaoJdbc) {
        this.authorDaoJdbc = authorDaoJdbc;
        this.authorDaoMock = authorDaoMock;
    }

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        return true; // Permite que se ejecute el test template siempre
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        List<Author> authors = loader.loadAuthorsFromCSV();
        List<AuthorRecord> authorRecords = loader.loadAuthorRecordsFromCSV();

        String isbn1 = "9780142424179";
        List<Author> expected1 = List.of(authors.getFirst());
        String isbn2 = "9780060557912";
        List<Author> expected2 = List.of(authors.get(15), authors.get(16));
        String isbn3 = "1234567890";
        List<Author> expected3 = Collections.emptyList();

        AuthorDao authorDaoMock = Mockito.mock(AuthorDao.class);
        Mockito.when(authorDaoMock.findAllByBookIsbn("9780142424179"))
                .thenReturn(List.of(authorRecords.getFirst()));
        Mockito.when(authorDaoMock.findAllByBookIsbn("9780060557912"))
                .thenReturn(List.of(authorRecords.get(15), authorRecords.get(16)));
        Mockito.when(authorDaoMock.findAllByBookIsbn("1234567890"))
                .thenReturn(Collections.emptyList());

        return Stream.of(
            invocationContext(new AuthorDaoJdbc(), isbn1, expected1),
            invocationContext(authorDaoMock, isbn1, expected1),
            invocationContext(new AuthorDaoJdbc(), isbn2, expected2),
            invocationContext(authorDaoMock, isbn2, expected2),
            invocationContext(new AuthorDaoJdbc(), isbn3, expected3),
            invocationContext(authorDaoMock, isbn3, expected3)
        );
    }

    private TestTemplateInvocationContext invocationContext(AuthorDao authorDao, String isbn, List<Author> expected) {

        return new TestTemplateInvocationContext() {

            @Override
            public String getDisplayName(int invocationIndex) {
                return "Execution with ISBN: " + isbn;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                                                     ExtensionContext extensionContext) throws ParameterResolutionException {
                        return parameterContext.getParameter().getType() == AuthorDao.class ||
                                parameterContext.getParameter().getType() == List.class ||
                                parameterContext.getParameter().getType() == String.class;
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        if (parameterContext.getParameter().getType() == AuthorDao.class) {
                            return authorDao; // authorDao
                        } else if (parameterContext.getParameter().getType() == String.class) {
                            return isbn; // isbn
                        } else if (parameterContext.getParameter().getType() == List.class) {
                            return expected; // expected
                        }
                        throw new ParameterResolutionException("Unsupported parameter type");
                    }
                });
            }
        };
    }

}
