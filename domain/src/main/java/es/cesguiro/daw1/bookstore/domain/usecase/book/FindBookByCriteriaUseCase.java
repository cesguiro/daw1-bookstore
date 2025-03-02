package es.cesguiro.daw1.bookstore.domain.usecase.book;

import es.cesguiro.daw1.bookstore.domain.model.Book;

public interface FindBookByCriteriaUseCase {

    Book findByIsbn(String isbn);
}
