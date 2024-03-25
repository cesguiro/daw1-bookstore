package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.AuthorRecord;

import java.util.List;

public class AuthorTable {

    private List<AuthorRecord> authorRecordList = List.of(
            new AuthorRecord(1, "John Kennedy Toole"),
            new AuthorRecord(2, "Umberto Eco"),
            new AuthorRecord(3, "Milan Kundera"),
            new AuthorRecord(4, "Terry Pratchett"),
            new AuthorRecord(5, "Neil Gaiman")

    );

    public List<AuthorRecord> selectAll() {
        return authorRecordList;
    }

    public AuthorRecord selectById(Integer id) {
        for(AuthorRecord authorRecord : authorRecordList) {
            if(authorRecord.getId().equals(id)) {
                return authorRecord;
            }
        }
        return null;
    }
}
