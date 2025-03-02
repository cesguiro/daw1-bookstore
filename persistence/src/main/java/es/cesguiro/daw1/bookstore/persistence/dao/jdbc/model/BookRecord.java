package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookRecord(
        Long id,
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        BigDecimal basePrice,
        Double discountPercentage,
        String cover,
        LocalDate publicationDate,
        PublisherRecord publisher,
        List<AuthorRecord> authors
) {
}
