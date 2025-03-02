package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model;

public record AuthorRecord(
        Long id,
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        Integer birthYear,
        Integer deathYear,
        String slug
) {
}
