package es.cesguiro.daw1.bookstore.persistence.repository.mapper;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;

public class PublisherMapper {

    public static Publisher toPublisher(PublisherRecord publisherRecord) {
        if (publisherRecord == null) {
            return null;
        }
        return new Publisher(
                publisherRecord.name(),
                publisherRecord.slug()
        );
    }
}
