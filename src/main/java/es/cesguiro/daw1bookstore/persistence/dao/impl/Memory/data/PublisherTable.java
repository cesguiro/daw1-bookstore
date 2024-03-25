package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.PublisherRecord;

import java.util.List;

public class PublisherTable {

    private List<PublisherRecord> publisherRecordList = List.of(
            new PublisherRecord(1, "Editorial Anagrama"),
            new PublisherRecord(2, "Penguin Random House Grupo Editorial España"),
            new PublisherRecord(3, "Editorial Planeta"),
            new PublisherRecord(4, "DeBolsillo"),
            new PublisherRecord(5, "Minotauro")
    );

    public List<PublisherRecord> selectAll() {
        return publisherRecordList;
    }

    public PublisherRecord selectById(Integer id) {
        for(PublisherRecord publisherRecord : publisherRecordList) {
            if(publisherRecord.getId().equals(id)) {
                return publisherRecord;
            }
        }
        return null;
    }
}
