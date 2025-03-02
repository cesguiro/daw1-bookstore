package es.cesguiro.daw1.bookstore.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.repository.mapper.AuthorMapper;

import java.util.List;

public class AuthorRepositoryJdbc implements AuthorRepository {

    private final AuthorDao authorDao;

    public AuthorRepositoryJdbc(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> findAllByBookIsbn(String isbn) {
        return authorDao.findAllByBookIsbn(isbn)
                .stream()
                .map(AuthorMapper::toAuthor)
                .toList();
    }
}
