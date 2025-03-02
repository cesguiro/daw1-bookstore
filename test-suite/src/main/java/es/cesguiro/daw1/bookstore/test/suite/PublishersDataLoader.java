package es.cesguiro.daw1.bookstore.test.suite;

import es.cesguiro.daw1.bookstore.domain.model.Publisher;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
import es.cesguiro.daw1.bookstore.test.suite.mapper.PublisherMapper;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class PublishersDataLoader extends ResourceDataLoader {

    private final List<CSVRecord> publisherRawRecords;

    public PublishersDataLoader() {
        super("publishers.csv");
        publisherRawRecords = loadDataFromCsv();
    }

    public List<PublisherRecord> loadPublisherRecordsFromCSV() {
        return publisherRawRecords
                .stream()
                .map(PublisherMapper::toPublisherRecord)
                .toList();
    }

    public List<Publisher> loadPublishersFromCSV() {
        return publisherRawRecords
                .stream()
                .map(PublisherMapper::toPublisher)
                .toList();
    }

}
