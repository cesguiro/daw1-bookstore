package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model;

public record PublisherRecord(
        Long id,
        String name,
        String slug
) {
}
