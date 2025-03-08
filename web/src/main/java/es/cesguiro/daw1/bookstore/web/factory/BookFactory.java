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
import es.cesguiro.daw1.bookstore.web.controller.BookController;
import es.cesguiro.daw1.bookstore.web.controller.Controller;

public class BookFactory {

    private static BookDao bookDao;
    private static BookRepository bookRepository;
    private static FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase;
    private static FindBookByCriteriaUseCase findBookByCriteriaUseCase;
    private static BookHandler bookHandler;
    private static Controller bookController;

    public static Controller createBookController() {
        if (bookController == null) {
            bookController = new BookController(createBookHandler());
        }
        return bookController;
    }

    public static BookHandler createBookHandler() {
        if (bookHandler == null) {
            bookHandler = new BookHandlerImpl(createFindAllBooksByCriteriaUseCase(), createFindBookByCriteriaUseCase());
        }
        return bookHandler;
    }

    public static FindAllBooksByCriteriaUseCase createFindAllBooksByCriteriaUseCase() {
        if (findAllBooksByCriteriaUseCase == null) {
            findAllBooksByCriteriaUseCase = new FindAllBooksByCriteriaService(createBookRepository(), AuthorFactory.createAuthorRepository());
        }
        return findAllBooksByCriteriaUseCase;
    }

    public static FindBookByCriteriaUseCase createFindBookByCriteriaUseCase() {
        if (findBookByCriteriaUseCase == null) {
            findBookByCriteriaUseCase = new FindBookByCriteriaService(
                    createBookRepository(),
                    AuthorFactory.createAuthorRepository(),
                    PublisherFactory.createPublisherRepository()
            );
        }
        return findBookByCriteriaUseCase;
    }

    public static BookRepository createBookRepository() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryJdbc(createBookDao());
        }
        return bookRepository;
    }

    public static BookDao createBookDao() {
        if (bookDao == null) {
            bookDao = new BookDaoJdbc();
        }
        return bookDao;
    }


}
