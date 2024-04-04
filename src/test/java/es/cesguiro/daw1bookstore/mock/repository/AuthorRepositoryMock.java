package es.cesguiro.daw1bookstore.mock.repository;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorRepositoryMock implements AuthorRepository {

    private final List<Author> authorList = List.of(
            new Author(1, "John Kennedy Toole"),
            new Author(2, "Umberto Eco"),
            new Author(3, "Milan Kundera"),
            new Author(4, "Terry Pratchett"),
            new Author(5, "Neil Gaiman")
    );

    private final Map<Integer, List<Integer>> booksAuthorsMap = new HashMap<>();

    public AuthorRepositoryMock() {
        booksAuthorsMap.put(1, List.of(1));
        booksAuthorsMap.put(2, List.of(2));
        booksAuthorsMap.put(3, List.of(3));
        booksAuthorsMap.put(4, List.of(2));
        booksAuthorsMap.put(5, List.of(4, 5));
    }

    public Author findById(Integer id) {
        for (Author author : authorList) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    @Override
    public List<Author> findByBookId(Integer bookId) {
        List<Integer> authorIds = booksAuthorsMap.get(bookId);
        if (authorIds == null) {
            return List.of();
        }
        List<Author> authors = new ArrayList<>();
        for (Integer authorId : authorIds) {
            authors.add(findById(authorId));
        }
        return authors;
    }
}
