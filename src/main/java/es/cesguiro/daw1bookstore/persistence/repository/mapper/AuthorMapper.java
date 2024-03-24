package es.cesguiro.daw1bookstore.persistence.repository.mapper;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.entity.AuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static Author toAuthor(AuthorEntity authorEntity) {
        if(authorEntity == null) {
            return null;
        }
        return new Author(
                authorEntity.getId(),
                authorEntity.getName()
        );
    }

    public static List<Author> toAuthorList(List<AuthorEntity> authorEntityList) {
        if(authorEntityList == null) {
            return null;
        }
        List<Author> authorList = new ArrayList<>();
        for(AuthorEntity authorEntity : authorEntityList) {
            authorList.add(toAuthor(authorEntity));
        }
        return authorList;
    }
}
