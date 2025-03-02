package es.cesguiro.daw1.bookstore.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.persistence.repository.mapper.BookMapper;
import es.cesguiro.daw1.bookstore.util.pagination.Page;

import java.util.List;
import java.util.Optional;

public class BookRepositoryJdbc implements BookRepository {

    private final BookDao bookDao;

    public BookRepositoryJdbc(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Page<Book> findAll(int page, int size) {
        List<Book> data = bookDao
                .findAll(page, size)
                .stream()
                .map(BookMapper::toBook)
                .toList();
        long totalElements = bookDao.count();
        return new Page<>(
                data,
                page,
                size,
                totalElements
        );
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn).map(BookMapper::toBook);
    }
}
