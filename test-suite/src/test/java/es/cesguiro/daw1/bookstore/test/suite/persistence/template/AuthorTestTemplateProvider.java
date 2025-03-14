package es.cesguiro.daw1.bookstore.test.suite.persistence.template;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.test.suite.AuthorsDataLoader;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.Arguments;
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

        Stream<Arguments> argumets = Stream.of(
                    Arguments.of("9780142424179", List.of(authors.get(0), authors.get(1))),
                    Arguments.of("9780060557912", List.of(authors.get(0))),
                    Arguments.of("1234567890", List.of())
        );

        AuthorDao authorDao = Mockito.mock(AuthorDao.class);
        Mockito.when(authorDao.findAllByBookIsbn("9780142424179"))
                .thenReturn(List.of(authorRecords.get(0), authorRecords.get(1)));
        Mockito.when(authorDao.findAllByBookIsbn("9780060557912"))
                .thenReturn(List.of(authorRecords.get(0)));
        Mockito.when(authorDao.findAllByBookIsbn("1234567890"))
                .thenReturn(Collections.emptyList());

        return Stream.of(
            invocationContext(new AuthorDaoJdbc(), argumets, "Integration test (Real DAO)"),
            invocationContext(Mockito.mock(AuthorDao.class), argumets, "Unit test (Mocked DAO)")
        );
    }

    private TestTemplateInvocationContext invocationContext(AuthorDao authorDao, Stream<Arguments> arguments, String testName) {

        return new TestTemplateInvocationContext() {

            @Override
            public String getDisplayName(int invocationIndex) {
                return testName + " - Execution #" + invocationIndex;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                                                     ExtensionContext extensionContext) throws ParameterResolutionException {
                        return parameterContext.getParameter().getType() == AuthorDao.class ||
                                parameterContext.getParameter().getType() == String.class ||
                                parameterContext.getParameter().getType() == List.class;
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        if (parameterContext.getParameter().getType() == AuthorDao.class) {
                            return authorDao; // authorDao
                        } else if (parameterContext.getParameter().getType() == String.class) {
                            return arguments.iterator().next().get()[0]; // isbn
                        } else if (parameterContext.getParameter().getType() == List.class) {
                            return arguments.iterator().next().get()[2]; // expected authors
                        }
                        throw new ParameterResolutionException("Unsupported parameter type");
                    }
                });
            }
        };
    }

}
