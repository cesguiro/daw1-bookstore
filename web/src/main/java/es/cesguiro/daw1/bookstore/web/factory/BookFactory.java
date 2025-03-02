package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.application.handler.BookHandler;
import es.cesguiro.daw1.bookstore.application.handler.impl.BookHandlerImpl;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.domain.service.book.FindAllBooksByCriteriaService;
import es.cesguiro.daw1.bookstore.domain.service.book.FindBookByCriteriaService;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindAllBooksByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindBookByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.BookDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.BookRepositoryJdbc;

public class BookFactory {

    private static BookDao bookDao;
    private static BookRepository bookRepository;
    private static FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase;
    private static FindBookByCriteriaUseCase findBookByCriteriaUseCase;
    private static BookHandler bookHandler;

    public static BookHandler bookHandler() {
        if (bookHandler == null) {
            bookHandler = new BookHandlerImpl(findAllBooksByCriteriaUseCase(), findBookByCriteriaUseCase());
        }
        return bookHandler;
    }

    public static FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase() {
        if (findAllBooksByCriteriaUseCase == null) {
            findAllBooksByCriteriaUseCase = new FindAllBooksByCriteriaService(bookRepository(), AuthorFactory.authorRepository());
        }
        return findAllBooksByCriteriaUseCase;
    }

    public static FindBookByCriteriaUseCase findBookByCriteriaUseCase() {
        if (findBookByCriteriaUseCase == null) {
            findBookByCriteriaUseCase = new FindBookByCriteriaService(
                    bookRepository(),
                    AuthorFactory.authorRepository(),
                    PublisherFactory.publisherRepository()
            );
        }
        return findBookByCriteriaUseCase;
    }

    public static BookRepository bookRepository() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryJdbc(bookDao());
        }
        return bookRepository;
    }

    public static BookDao bookDao() {
        if (bookDao == null) {
            bookDao = new BookDaoJdbc();
        }
        return bookDao;
    }


}
