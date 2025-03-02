package es.cesguiro.daw1.bookstore.domain.service.book;

import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.model.Book;
import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.domain.usecase.book.FindBookByCriteriaUseCase;
import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.domain.repository.BookRepository;
import es.cesguiro.daw1.bookstore.domain.repository.PublisherRepository;
import es.cesguiro.daw1.bookstore.util.exception.Error404;

import java.util.List;

public class FindBookByCriteriaService implements FindBookByCriteriaUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    public FindBookByCriteriaService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                        .orElseThrow(() -> new Error404("Book with ISBN " + isbn + " not found"));
        List<Author> authors = authorRepository.findAllByBookIsbn(book.getIsbn());
        book.setAuthors(authors);
        Publisher publisher = publisherRepository.findByBookIsbn(book.getIsbn())
                .orElse(null);
        book.setPublisher(publisher);
        return book;
    }


}
