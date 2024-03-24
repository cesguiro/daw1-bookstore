package es.cesguiro.daw1bookstore.persistence.repository.mapper;

import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.entity.PublisherEntity;

public class PublisherMapper {

    public static Publisher toPublisher(PublisherEntity publisherEntity) {
        if(publisherEntity == null) {
            return null;
        }
        return new Publisher(
                publisherEntity.getId(),
                publisherEntity.getName()
        );
    }
}
