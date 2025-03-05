package es.cesguiro.daw1.bookstore.persistence.dao.jdbc.model;

public record UserRecord (
        Long id,
        String email,
        String password,
        String name,
        String address,
        String language,
        boolean admin

){

}
