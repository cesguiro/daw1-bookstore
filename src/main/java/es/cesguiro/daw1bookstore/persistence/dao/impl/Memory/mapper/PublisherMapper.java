package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.PublisherTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.PublisherRecord;

public class PublisherMapper {

    public static Publisher toPublisher(PublisherRecord publisherRecord) {
        return new Publisher(publisherRecord.getId(), publisherRecord.getName());
    }
}
