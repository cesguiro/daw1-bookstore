package es.cesguiro.daw1bookstore.persistence.dao.impl;

import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.persistence.dao.BookDao;
import es.cesguiro.daw1bookstore.persistence.dao.entity.BookEntity;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private List<BookEntity> bookEntityList = List.of(
            new BookEntity(
                    1,
                    "9788433920423",
                    "La conjura de los necios",
                    "El protagonista de esta novela es uno de los personajes más memorables...",
                    new BigDecimal(13.20),
                    "necios.jpeg"
            ),
            new BookEntity(
                    2,
                    "9788426418197",
                    "El nombre de la rosa",
                    "Valiéndose de las características de la novela gótica, la crónica medieval y la novela policíaca, El nombre de la rosa narra las investigaciones detectivescas que realiza el fraile franciscano Guillermo de Baskerville para esclarecer los crímenes cometidos en una abadía benedictina en el año 1327. Le ayudará en su labor el novicio Adso, un joven que se enfrenta por primera vez a las realidades de la vida situadas más allá de las puertas del convento.",
                    new BigDecimal(12.30),
                    "nombreRosa.jpeg"),
            new BookEntity(
                    3,
                    "9786074213485",
                    "La insoportable levedad del ser",
                    "La insoportable levedad del ser es una novela del escritor checo Milan Kundera publicada en 1984. La novela es una exploración filosófica de la vida, el amor, la muerte y la eternidad. La historia se desarrolla en Praga, capital de Checoslovaquia, en la década de 1960. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(11.50),
                    "levedad.jpeg"),
            new BookEntity(
                    4,
                    "9788466338141",
                    "La isla del día de antes", "La isla del día de antes es una novela del escritor italiano Umberto Eco publicada en 1994. La novela es una exploración filosófica de la vida, el amor, la muerte y la eternidad. La historia se desarrolla en Praga, capital de Checoslovaquia, en la década de 1960. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(10.40),
                    "islaDiaAntes.jpeg"),
            new BookEntity(
                    5,
                    "9788448022440",
                    "Buenos presagios",
                    "Buenos presagios es una novela de humor escrita por Terry Pratchett y Neil Gaiman. Fue publicada por primera vez en 1990. La novela es una narrativa de múltiples capas que se centra en la vida de cuatro personajes principales: Tomáš, Teresa, Sabina y Franz.",
                    new BigDecimal(9.30),
                    "buenosPresagios.jpeg")
    );

    @Override
    public List<BookEntity> findAll() {
        return bookEntityList;
    }

}
