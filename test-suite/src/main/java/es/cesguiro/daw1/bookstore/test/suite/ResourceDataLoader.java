package es.cesguiro.daw1.bookstore.test.suite;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class ResourceDataLoader {

    protected String csvFile;
    private final Logger logger = LogManager.getLogger(ResourceDataLoader.class);

    public ResourceDataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    private static final CSVFormat CSV_FORMAT = CSVFormat.MYSQL.builder()
            .setHeader()
            .setDelimiter(',')
            .setQuote('"')
            .get();

    protected List<CSVRecord> loadDataFromCsv() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile)) {
            if (inputStream == null) {
                logger.error("File not found: {}", csvFile);
                throw new IllegalArgumentException("File not found: " + csvFile);
            }
            try (CSVParser csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, CSV_FORMAT)) {
                return csvParser.getRecords();
            }
        } catch (Exception e) {
            logger.error("Error loading data from CSV file", e);
            throw new IllegalArgumentException("Error loading data from CSV file", e);
        }
    }

    public Optional<CSVRecord> findCsvRecordById(long id) {
        List<CSVRecord> records = loadDataFromCsv();
        return records.stream()
                .filter(record -> Long.parseLong(record.get("id")) == id)
                .findFirst();
    }

    public List<CSVRecord> findAllCsvRecordsByIds(Long[] ids) {
        List<CSVRecord> records = loadDataFromCsv();
        return records.stream()
                .filter(record -> {
                    for (long id : ids) {
                        if (Long.parseLong(record.get("id")) == id) {
                            return true;
                        }
                    }
                    return false;
                })
                .toList();
    }

}
