package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.mapper;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.AuthorTable;
import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.AuthorRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static Author toAuthor(AuthorRecord authorRecord) {
        return new Author(authorRecord.getId(), authorRecord.getName());
    }

    public static List<Author> toAuthorList(List<AuthorRecord> authorRecordList) {
        List<Author> authorList = new ArrayList<>();
        for(AuthorRecord authorRecord : authorRecordList) {
            authorList.add(toAuthor(authorRecord));
        }
        return authorList;
    }
}
