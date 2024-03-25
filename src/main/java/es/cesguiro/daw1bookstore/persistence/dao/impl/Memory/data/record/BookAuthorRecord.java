package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record;

public class BookAuthorRecord {

    int id;
    int bookId;
    int authorId;

    public BookAuthorRecord(int id, int bookId, int authorId) {
        this.id = id;
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
