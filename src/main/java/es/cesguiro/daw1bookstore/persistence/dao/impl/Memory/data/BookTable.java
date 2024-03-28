package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.BookRecord;

import java.math.BigDecimal;
import java.util.List;

public class BookTable {

    private List<BookRecord> bookRecordList = List.of(
            new BookRecord(
                    1,
                    "9788433920423",
                    "La conjura de los necios",
                    "A confederacy of dunce",
                    "El protagonista de esta novela es uno de los personajes más memorables...",
                    "A monument to sloth, rant and contempt, a behemoth of fat, flatulence and furious suspicion of anything modern - this is Ignatius J. Reilly...",
                    new BigDecimal(13.20),
                    "necios.jpeg",
                    1
            ),
            new BookRecord(
                    2,
                    "9788426418197",
                    "El nombre de la rosa",
                    "The name of the rose",
                    "Valiéndose de las características de la novela gótica, la crónica medieval y la novela policíaca, El nombre de la rosa narra las...",
                    "The year is 1327. Franciscans in a wealthy Italian abbey are suspected of heresy, and Brother William of Baskerville arrives to investigate...",
                    new BigDecimal(12.30),
                    "nombreRosa.jpeg",
                    2
            ),
            new BookRecord(
                    3,
                    "9786074213485",
                    "La insoportable levedad del ser",
                    "The unbearable lightness of being",
                    "La insoportable levedad del ser es una novela del escritor checo Milan Kundera publicada en 1984. La novela es una exploración filosófica de la vida...",
                    "In The Unbearable Lightness of Being, Milan Kundera tells the story of a young...",
                    new BigDecimal(11.50),
                    "levedad.jpeg",
                    3
            ),
            new BookRecord(
                    4,
                    "9788466338141",
                    "La isla del día de antes",
                    "The island of the day before",
                    "La isla del día de antes es una novela del escritor italiano Umberto Eco publicada en 1994. La novela es una exploración filosófica de la vida...",
                    "It is the year 1643. Roberto, a young nobleman, survives war, the Bastille, exile, and shipwreck as he voyages to a Pacific island straddling the date meridian...",
                    new BigDecimal(10.40),
                    "islaDiaAntes.jpeg",
                    4
            ),
            new BookRecord(
                    5,
                    "9788448022440",
                    "Buenos presagios",
                    "Good omens",
                    "Buenos presagios es una novela de humor escrita por Terry Pratchett y Neil Gaiman. Fue publicada por primera vez en 1990. La novela es una narrativa de...",
                    "According to The Nice and Accurate Prophecies of Agnes Nutter, Witch (the world's only completely accurate book of prophecies, written in 1655, before she exploded),...",
                    new BigDecimal(9.30),
                    "buenosPresagios.jpeg",
                    5
            )
    );


    public List<BookRecord> selectAll() {
        return bookRecordList;
    }

    public BookRecord selectById(Integer bookId) {
        for(BookRecord bookRecord : bookRecordList) {
            if(bookRecord.getId().equals(bookId)) {
                return bookRecord;
            }
        }
        return null;
    }
}
