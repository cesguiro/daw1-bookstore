package es.cesguiro.daw1bookstore.persistence.repository;

import es.cesguiro.daw1bookstore.domain.model.Publisher;

public interface PublisherRepository {

    Publisher findByBookId(Integer bookId);
}
