package es.cesguiro.daw1.bookstore.test.suite.persistence.integration;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;
import es.cesguiro.daw1.bookstore.test.suite.persistence.template.AuthorTestTemplateProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AuthorTestTemplateProvider.class)
public class PruebaTest {

    @TestTemplate
    void testFindAuthorsByBookIsbn(AuthorDao authorDao, String isbn, List<AuthorRecord> authorRecords, List<Author> expected) {
        AuthorRepository authorRepository = new AuthorRepositoryJdbc(authorDao);

        // 🔹 Simulamos comportamiento si es un mock
        if (authorDao instanceof org.mockito.MockedStatic) {
            Mockito.when(authorDao.findAllByBookIsbn(isbn))
                    .thenReturn(authorRecords);
        }

        // 🔹 Ejecutamos la prueba
        List<Author> actual = authorRepository.findAllByBookIsbn(isbn);

        // 🔹 Validamos la respuesta
        if (expected.isEmpty()) {
            assertTrue(actual.isEmpty(), "No deberían existir autores para ISBN: " + isbn);
        } else {
            assertFalse(actual.isEmpty(), "Los autores deberían existir para ISBN: " + isbn);
            assertEquals(expected.size(), actual.size(), "Número de autores incorrecto");
        }

        System.out.println("✅ Test ejecutado con ISBN: " + isbn + " → Resultado: " + actual);
    }
}
