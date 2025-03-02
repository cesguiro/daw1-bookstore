package es.cesguiro.daw1.bookstore.application.handler.impl;

import es.cesguiro.daw1.bookstore.application.PageResponse;
import es.cesguiro.daw1.bookstore.application.PaginationUtil;
import es.cesguiro.daw1.bookstore.application.handler.BookHandler;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindAllBooksByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindBookByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.util.pagination.Page;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;

public class BookHandlerImpl implements BookHandler {

    public static final String RESOURCE = "/books";

    private final int DEFAULT_PAGE_SIZE = Integer.parseInt(PropertyUtil.getPropertyProvider().getProperty("app.default.page.size", "10"));

    private final FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase;
    private final FindBookByCriteriaUseCase findBookByCriteriaUseCase;

    public BookHandlerImpl(FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase, FindBookByCriteriaUseCase findBookByCriteriaUseCase) {
        this.findAllBooksByCriteriaUseCase = findAllBooksByCriteriaUseCase;
        this.findBookByCriteriaUseCase = findBookByCriteriaUseCase;
    }

    public PageResponse<Book> findAll(Integer page, Integer size) {

        int pageNumber = page != null ? page : 1;
        int pageSize = size != null ? size : DEFAULT_PAGE_SIZE;

        Page<Book> pageBooks = findAllBooksByCriteriaUseCase.findAll(pageNumber, pageSize);
        return PaginationUtil.generatePageResponse(
                pageBooks.data(),
                pageNumber,
                pageSize,
                pageBooks.totalElements(),
                pageBooks.totalPages(),
                PropertyUtil.getPropertyProvider().getProperty("app.base.url") + RESOURCE
        );
    }

    public Book findByIsbn(String isbn) {
        return findBookByCriteriaUseCase.findByIsbn(isbn);
    }

}
