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

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        return true; // Permite que se ejecute el test template siempre
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        AuthorsDataLoader loader = new AuthorsDataLoader();
        List<Author> authors = loader.loadAuthorsFromCSV();
        List<AuthorRecord> authorRecords = loader.loadAuthorRecordsFromCSV();

        String isbn = "9780142424179";
        List<Author> expected = List.of(authors.getFirst());

        AuthorDao authorDaoMock = Mockito.mock(AuthorDao.class);
        Mockito.when(authorDaoMock.findAllByBookIsbn("9780142424179"))
                .thenReturn(List.of(authorRecords.getFirst()));
        Mockito.when(authorDaoMock.findAllByBookIsbn("9780060557912"))
                .thenReturn(List.of(authorRecords.get(15), authorRecords.get(16)));
        Mockito.when(authorDaoMock.findAllByBookIsbn("1234567890"))
                .thenReturn(Collections.emptyList());

        return Stream.of(
            invocationContext(new AuthorDaoJdbc(), isbn, expected),
            invocationContext(authorDaoMock, isbn, expected)
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
