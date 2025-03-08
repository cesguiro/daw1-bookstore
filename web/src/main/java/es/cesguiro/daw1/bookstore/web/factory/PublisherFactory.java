package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.domain.repository.PublisherRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.PublisherDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.PublisherRepositoryJdbc;

public class PublisherFactory {

    private static PublisherDao publisherDao;
    private static PublisherRepository publisherRepository;

    public static PublisherRepository createPublisherRepository() {
        if (publisherRepository == null) {
            publisherRepository = new PublisherRepositoryJdbc(createPublisherDao());
        }
        return publisherRepository;
    }

    public static PublisherDao createPublisherDao() {
        if (publisherDao == null) {
            publisherDao = new PublisherDaoJdbc();
        }
        return publisherDao;
    }
}
