package es.cesguiro.daw1bookstore.persistence.repository.impl;

import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;

public class PublisherRepositoryImpl implements PublisherRepository {

    private final PublisherDao publisherDao;

    public PublisherRepositoryImpl(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher findByBookId(Integer bookId) {
        return publisherDao.findByBookId(bookId);
    }
}
