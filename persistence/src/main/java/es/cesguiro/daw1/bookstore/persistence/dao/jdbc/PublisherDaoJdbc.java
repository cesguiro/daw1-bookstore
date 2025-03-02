package es.cesguiro.daw1.bookstore.persistence.dao.jdbc;

import es.cesguiro.daw1.bookstore.persistence.dao.PublisherDao;
import es.cesguiro.daw1.bookstore.persistence.dao.db.QueryBuilder;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.mapper.PublisherMapper;
import es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model.PublisherRecord;
import es.cesguiro.daw1.bookstore.util.exception.Error500;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PublisherDaoJdbc implements PublisherDao {

    @Override
    public Optional<PublisherRecord> findByBookIsbn(String isbn) {
        try {
            return Optional.ofNullable(PublisherMapper.toPublisherRecord(
                    QueryBuilder
                            .table("publishers")
                            .select("publishers.*")
                            .join("books", "publishers.id", "books.publisher_id")
                            .where("books.isbn", "=", isbn)
                            .getOne()
                    )
            );
        } catch (SQLException e) {
            throw new Error500("Error finding publisher by book isbn", e);
        }
    }

    @Override
    public List<PublisherRecord> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public List<PublisherRecord> findAll() {
        return List.of();
    }

    @Override
    public Optional<PublisherRecord> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PublisherRecord save(PublisherRecord entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Long count() {
        return 0L;
    }

}
