package es.cesguiro.daw1bookstore.unit.repository;

import es.cesguiro.daw1bookstore.common.container.AuthorIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.mock.dao.AuthorDaoMock;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AuthorRepository Unit Tests")
public class AuthorRepositoryUnitTest {

    private static AuthorRepository authorRepository;

    @BeforeAll
    public static void setupAll() {
        AuthorIoc.setAuthorDao(new AuthorDaoMock());
        authorRepository = AuthorIoc.getAuthorRepository();
    }

    @AfterAll
    public static void teardownAll() {
        AuthorIoc.reset();
    }

    @DisplayName("Test find single author by book id")
    @Test
    public void testFindByBookId(){
        List<Author> actualAuthorList = authorRepository.findByBookId(1);
        List<Author> expectedAuthorList = List.of(
                new Author(1, "John Kennedy Toole")
        );
        assertEquals(expectedAuthorList, actualAuthorList, "Autor incorrecto");
    }

    @DisplayName("Test find multiple authors by book id")
    @Test
    public void testFindByBookIdMultipleAuthors(){
        List<Author> actualAuthorList = authorRepository.findByBookId(5);
        List<Author> expectedAuthorList = List.of(
                new Author(4, "Terry Pratchett"),
                new Author(5, "Neil Gaiman")
        );
        assertEquals(expectedAuthorList, actualAuthorList, "Autores incorrectos");
    }

    @DisplayName("Test find author by non-existent book id")
    @Test
    public void testFindByBookIdNonExistent() {
        List<Author> actualAuthorList = authorRepository.findByBookId(6);
        assertEquals(0, actualAuthorList.size(), "Autor encontrado");
    }
}
