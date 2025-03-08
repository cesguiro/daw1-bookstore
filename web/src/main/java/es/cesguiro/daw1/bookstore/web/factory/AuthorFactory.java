package es.cesguiro.daw1.bookstore.web.factory;

import es.cesguiro.daw1.bookstore.domain.repository.AuthorRepository;
import es.cesguiro.daw1.bookstore.persistence.dao.AuthorDao;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.AuthorDaoJdbc;
import es.cesguiro.daw1.bookstore.persistence.repository.AuthorRepositoryJdbc;

public class AuthorFactory {

    private static AuthorDao authorDao;
    private static AuthorRepository authorRepository;

    public static AuthorRepository createAuthorRepository() {
        if (authorRepository == null) {
            authorRepository = new AuthorRepositoryJdbc(createAuthorDao());
        }
        return authorRepository;
    }

    public static AuthorDao createAuthorDao() {
        if (authorDao == null) {
            authorDao = new AuthorDaoJdbc();
        }
        return authorDao;
    }
}
