package es.cesguiro.daw1bookstore.integration.repository;

import es.cesguiro.daw1bookstore.common.container.AuthorIoc;
import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Author Repository-DAO Integration Test")
public class AuthorRepositoryDaoIntegrationTest {

    private static final AuthorRepository authorRepository = AuthorIoc.getAuthorRepository();

    @DisplayName("Test find author list with only 1 author by book id")
    @Test
    public void testFindAuthorListByBookId() {
        List<Author> actualAuthorList = authorRepository.findByBookId(1);
        List<Author> expectedAuthorList = List.of(new Author(1, "John Kennedy Toole"));
        assertEquals(actualAuthorList, expectedAuthorList, "Autor incorrecto");
    }

    @DisplayName("Test find author list with more than 1 author by book id")
    @Test
    public void testFindAurhotListMoreThanOneByBookId() {
        List<Author> actualAuthorList = authorRepository.findByBookId(5);
        List<Author> expectedAuthorList = List.of(
                new Author(4, "Terry Pratchett"),
                new Author(5, "Neil Gaiman")
        );
        assertEquals(actualAuthorList, expectedAuthorList, "Autor incorrecto");
    }

    @DisplayName("Test find author list by not existing book id")
    @Test
    public void testFindByNotExistingBookId() {
        List<Author> actualAuthorList = authorRepository.findByBookId(100);
        assertEquals(actualAuthorList, List.of(), "Autor incorrecto");
    }

}
