package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record;

public class PublisherRecord {

    private Integer id;
    private String name;

    public PublisherRecord(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
