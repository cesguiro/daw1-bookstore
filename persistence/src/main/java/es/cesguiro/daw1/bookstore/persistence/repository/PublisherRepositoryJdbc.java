package es.cesguiro.daw1.bookstore.persistence.repository;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.domain.repository.PublisherRepository;
import es.cesguiro.daw1.bookstore.persistence.repository.mapper.PublisherMapper;

import java.util.Optional;

public class PublisherRepositoryJdbc implements PublisherRepository {

    private final PublisherDao publisherDao;

    public PublisherRepositoryJdbc(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Optional<Publisher> findByBookIsbn(String isbn) {
        return publisherDao.findByBookIsbn(isbn).map(PublisherMapper::toPublisher);
    }
}
