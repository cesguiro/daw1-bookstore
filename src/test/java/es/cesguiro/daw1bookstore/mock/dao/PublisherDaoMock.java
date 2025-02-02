package es.cesguiro.daw1bookstore.mock.dao;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;

import java.util.List;
import java.util.Map;

public class PublisherDaoMock implements PublisherDao {

    private final List<Publisher> publisherList = List.of(
            new Publisher(1, "Editorial Anagrama"),
            new Publisher(2, "Penguin Random House Grupo Editorial España"),
            new Publisher(3, "Editorial Planeta"),
            new Publisher(4, "DeBolsillo"),
            new Publisher(5, "Minotauro")
    );

    Map<Integer, Integer> booksPublishersMap = Map.of(
            1, 1,
            2, 2,
            3, 3,
            4, 4,
            5, 5
    );

    public Publisher findById(Integer id) {
        for (Publisher publisher : publisherList) {
            if (publisher.getId() == id) {
                return publisher;
            }
        }
        return null;
    }

    @Override
    public Publisher findByBookId(Integer bookId) {
        Integer publisherId = booksPublishersMap.get(bookId);
        if (publisherId == null) {
            return null;
        }
        return findById(publisherId);
    }
}
