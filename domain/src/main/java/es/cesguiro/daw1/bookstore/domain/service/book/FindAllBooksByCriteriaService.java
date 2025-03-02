package es.cesguiro.daw1.bookstore.domain.service.book;

import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindAllBooksByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.util.exception.Error404;
import es.cesguiro.daw1.bookstore.util.exception.Error500;
import es.cesguiro.daw1.bookstore.util.pagination.Page;

public class FindAllBooksByCriteriaService implements FindAllBooksByCriteriaUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public FindAllBooksByCriteriaService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<Book> findAll(int page, int size) {
        if(page <= 0 || size <= 0) {
            throw new Error500("Page number and page size must be greater than zero");
        }
        Page<Book> bookPage = bookRepository.findAll(page, size);
        bookPage.data().forEach(
                book -> {
                    book.setAuthors(authorRepository.findAllByBookIsbn(book.getIsbn()));
                }
        );
        return bookPage;
    }

}
