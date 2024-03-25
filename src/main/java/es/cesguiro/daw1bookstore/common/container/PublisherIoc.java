package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.PublisherDaoMemory;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.PublisherRepositoryImpl;

public class PublisherIoc {

    private static PublisherRepository publisherRepository;
    private static PublisherDao publisherDao;

    public static PublisherRepository getPublisherRepository() {
        if (publisherRepository == null) {
            publisherRepository = new PublisherRepositoryImpl(getPublisherDao());
        }
        return publisherRepository;
    }

    public static PublisherDao getPublisherDao() {
        if (publisherDao == null) {
            publisherDao = new PublisherDaoMemory();
        }
        return publisherDao;
    }

    public static void setPublisherRepository(PublisherRepository publisherRepository) {
        PublisherIoc.publisherRepository = publisherRepository;
    }

    public static void setPublisherDao(PublisherDao publisherDao) {
        PublisherIoc.publisherDao = publisherDao;
    }
}
