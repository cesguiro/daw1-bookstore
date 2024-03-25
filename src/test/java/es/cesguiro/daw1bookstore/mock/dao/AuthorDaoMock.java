package es.cesguiro.daw1bookstore.mock.dao;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;

import java.util.List;

public class AuthorDaoMock implements AuthorDao {

    private final List<Author> authorList = List.of(
            new Author(1, "John Kennedy Toole"),
            new Author(2, "Umberto Eco"),
            new Author(3, "Milan Kundera"),
            new Author(4, "Terry Pratchett"),
            new Author(5, "Neil Gaiman")
    );

    @Override
    public List<Author> findByBookId(Integer bookId) {
        switch (bookId) {
            case 1:
                return List.of(authorList.get(0));
            case 2:
                return List.of(authorList.get(1));
            case 3:
                return List.of(authorList.get(2));
            case 4:
                return List.of(authorList.get(1));
            case 5:
                return List.of(authorList.get(3), authorList.get(4));
            default:
                return List.of();
        }
    }
}
