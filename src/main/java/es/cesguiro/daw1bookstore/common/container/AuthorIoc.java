package es.cesguiro.daw1bookstore.common.container;

import es.cesguiro.daw1bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.AuthorDaoMemory;
import es.cesguiro.daw1bookstore.persistence.dao.impl.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;
import es.cesguiro.daw1bookstore.persistence.repository.impl.AuthorRepositoryImpl;

public class AuthorIoc {

    private static AuthorRepository authorRepository;
    private static AuthorDao authorDao;

    public static AuthorRepository getAuthorRepository() {
        if (authorRepository == null) {
            authorRepository = new AuthorRepositoryImpl(getAuthorDao());
        }
        return authorRepository;
    }

    public static AuthorDao getAuthorDao() {
        if (authorDao == null) {
            //authorDao = new AuthorDaoMemory();
            authorDao = new AuthorDaoJdbc();
        }
        return authorDao;
    }

    public static void setAuthorRepository(AuthorRepository authorRepository) {
        AuthorIoc.authorRepository = authorRepository;
    }

    public static void setAuthorDao(AuthorDao authorDao) {
        AuthorIoc.authorDao = authorDao;
    }

    public static void reset() {
        authorRepository = null;
        authorDao = null;
    }
}
