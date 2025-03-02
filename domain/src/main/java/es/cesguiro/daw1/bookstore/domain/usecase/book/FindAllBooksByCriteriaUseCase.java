package es.cesguiro.daw1.bookstore.domain.usecase.book;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.util.pagination.Page;

public interface FindAllBooksByCriteriaUseCase {

    Page<Book> findAll(int page, int size);
}
