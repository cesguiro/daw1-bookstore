package es.cesguiro.daw1bookstore.common;



import com.sun.tools.javac.Main;
import es.cesguiro.daw1bookstore.common.exception.AppReaderException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

public class AppPropertiesReader {

    private static final Properties properties = new Properties();

    static {
        loadProperties("application.properties"); // Carga las propiedades por defecto

        // Detectar el perfil y cargar las propiedades correspondientes
        String activeProfile = getProperty("app.profiles.active");
        if (activeProfile != null) {
            LogManager.getLogManager().getLogger("").info("Active profile: " + activeProfile);
            loadProperties("application-" + activeProfile + ".properties");
        }

    }

    private static void loadProperties(String filename) {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                LogManager.getLogManager().getLogger("").severe("Properties resource file not found: " + filename);
                return;
            }

            // Cargar las propiedades desde el archivo de configuración
            properties.load(input);
        } catch (IOException e) {
            LogManager.getLogManager().getLogger("").severe("Error loading properties file: " + filename);
            throw new AppReaderException("Error loading properties file: " + filename);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}