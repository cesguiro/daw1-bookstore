package es.cesguiro.daw1.bookstore.persistence.repository.mapper;


import es.cesguiro.daw1.bookstore.domain.model.Author;
import es.cesguiro.daw1.bookstore.domain.model.vo.LocaleString;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.AuthorRecord;

public class AuthorMapper {

    public static Author toAuthor(AuthorRecord authorRecord) {
        if (authorRecord == null) {
            return null;
        }
        return new Author(
                authorRecord.name(),
                authorRecord.nationality(),
                new LocaleString(authorRecord.biographyEs(), authorRecord.biographyEn()),
                authorRecord.birthYear(),
                authorRecord.deathYear(),
                authorRecord.slug()
        );
    }
}
