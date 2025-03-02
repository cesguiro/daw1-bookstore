package es.cesguiro.daw1.bookstore.domain.repository;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;

import java.util.Optional;

public interface PublisherRepository {

    Optional<Publisher> findByBookIsbn(String isbn);
}
