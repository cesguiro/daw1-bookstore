package es.cesguiro.daw1bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// Necesario para que Spring detecte los filtros y los registre automáticamente. Si utilizamos @Component en el filtro, no es necesario
@ServletComponentScan
public class Daw1BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(Daw1BookstoreApplication.class, args);
	}

}
