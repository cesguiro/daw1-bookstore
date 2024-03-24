package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.domain.service.BookService;
import es.cesguiro.daw1bookstore.domain.service.impl.BookServiceImpl;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.BookDaoImpl;
import es.cesguiro.daw1bookstore.persistence.repository.BookRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.BookRepositoryImpl;

public class BookIoc {

    private static BookService bookService;

    private static BookRepository bookRepository;

    private static BookDao bookDao;

    public static BookService getBookService() {
        if (bookService == null) {
            bookService = new BookServiceImpl(getBookRepository());
        }
        return bookService;
    }

    public static BookRepository getBookRepository() {
        if(bookRepository == null) {
            bookRepository = new BookRepositoryImpl(getBookDao());
        }
        return bookRepository;
    }

    public static BookDao getBookDao() {
        if(bookDao == null) {
            bookDao = new BookDaoImpl();
        }
        return bookDao;
    }
}
