package es.cesguiro.daw1bookstore.mock.repository;

import es.cesguiro.daw1bookstore.domain.model.Author;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Publisher;
import es.cesguiro.daw1bookstore.mock.dao.AuthorDaoMock;
import es.cesguiro.daw1bookstore.mock.dao.PublisherDaoMock;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.repository.AuthorRepository;
import es.cesguiro.daw1bookstore.persistence.repository.BookRepository;
import es.cesguiro.daw1bookstore.persistence.repository.PublisherRepository;

import java.math.BigDecimal;
import java.util.List;

public class BookRepositoryMock implements BookRepository {

    AuthorRepositoryMock authorRepositoryMock = new AuthorRepositoryMock();
    PublisherRepositoryMock publisherRepositoryMock = new PublisherRepositoryMock();
    private final List<Book> bookList = List.of(
            new Book(
                    1,
                    "9788433920423",
                    "La conjura de los necios",
                    "El protagonista de esta novela es uno de los personajes más memorables...",
                    new BigDecimal(13.20),
                    "necios.jpeg",
                    publisherRepositoryMock.findById(1),
                    List.of(authorRepositoryMock.findById(1))
            ),
            new Book(
                    2,
                    "9788426418197",
                    "El nombre de la rosa",
                    "Valiéndose de las características de la novela gótica, la crónica medieval y la novela policíaca, El nombre de la rosa narra las investigaciones detectivescas que realiza el fraile franciscano Guillermo de Baskerville para esclarecer los crímenes cometidos en una abadía benedictina en el año 1327. Le ayudará en su labor el novicio Adso, un joven que se enfrenta por primera vez a las realidades de la vida situadas más allá de las puertas del convento.",
                    new BigDecimal(12.30),
                    "nombreRosa.jpeg",
                    publisherRepositoryMock.findById(2),
                    List.of(authorRepositoryMock.findById(2))
            ),
            new Book(
                    3,
                    "9786074213485",
                    "La insoportable levedad del ser",
                    "La insoportable levedad del ser es una novela del escritor checo Milan Kundera publicada en 1984. La novela es una exploración filosófica de la vida, el amor, la muerte y la eternidad. La historia se desarrolla en Praga, capital de Checoslovaquia, en la década de 1960. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(11.50),
                    "levedad.jpeg",
                    publisherRepositoryMock.findById(3),
                    List.of(authorRepositoryMock.findById(3))
            ),
            new Book(
                    4,
                    "9788466338141",
                    "La isla del día de antes",
                    "La isla del día de antes es una novela del escritor italiano Umberto Eco publicada en 1994. La novela es una exploración filosófica de la vida, el amor, la muerte y la eternidad. La historia se desarrolla en Praga, capital de Checoslovaquia, en la década de 1960. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(10.40),
                    "islaDiaAntes.jpeg",
                    publisherRepositoryMock.findById(4),
                    List.of(authorRepositoryMock.findById(2))
            ),
            new Book(
                    5,
                    "9788448022440",
                    "Buenos presagios",
                    "Buenos presagios es una novela de humor escrita por Terry Pratchett y Neil Gaiman. Fue publicada por primera vez en 1990. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(9.30),
                    "buenosPresagios.jpeg",
                    publisherRepositoryMock.findById(5),
                    List.of(authorRepositoryMock.findById(4), authorRepositoryMock.findById(5))
            )
    );

    @Override
    public List<Book> findAll() {
        return bookList;
    }

    @Override
    public Book findById(Integer id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
