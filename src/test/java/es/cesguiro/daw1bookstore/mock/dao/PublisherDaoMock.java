package es.cesguiro.daw1bookstore.mock.dao;

import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.persistence.dao.PublisherDao;

import java.util.List;

public class PublisherDaoMock implements PublisherDao {

    private final List<Publisher> publisherList = List.of(
            new Publisher(1, "Editorial Anagrama"),
            new Publisher(2, "Penguin Random House Grupo Editorial España"),
            new Publisher(3, "Editorial Planeta"),
            new Publisher(4, "DeBolsillo"),
            new Publisher(5, "Minotauro")
    );

    @Override
    public Publisher findByBookId(Integer bookId) {
        switch (bookId) {
            case 1:
                return publisherList.get(0);
            case 2:
                return publisherList.get(1);
            case 3:
                return publisherList.get(2);
            case 4:
                return publisherList.get(1);
            case 5:
                return publisherList.get(3);
            default:
                return null;
        }
    }
}
