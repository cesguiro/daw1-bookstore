package es.cesguiro.daw1.bookstore.util.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPropertyProviderTest {

    private DefaultPropertyProvider provider;

    @BeforeEach
    void setUp() {
        provider = new DefaultPropertyProvider();
    }

    @Test
    @DisplayName("loadFromClassPath should load properties from classpath application.properties")
    void testLoadFromClassPath() {
        provider.loadFromClassPath("application.properties");

        String property = provider.getProperties().getProperty("app.test.property");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("value", property)
        );
    }

    @Test
    @DisplayName("loadFromClassPath should throw exception if file does not exist")
    void testLoadFromClassPathFileNotFound() {
        assertThrows(RuntimeException.class, () -> provider.loadFromClassPath("nonexistent-file.properties"),
                "Should throw exception when the file does not exist");
    }

    @Test
    @DisplayName("loadFromFile should load properties from file")
    void testLoadFromFile() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        String property = provider.getProperties().getProperty("app.version");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("1.0", property)
        );
    }

    @Test
    @DisplayName("loadFromFile should throw exception if file does not exist")
    void testLoadFromFileFileNotFound() {
        assertThrows(RuntimeException.class, () -> provider.loadFromFile("nonexistent-file.properties"),
                "Should throw exception when the file does not exist");
    }

    @Test
    @DisplayName("getProperty should return system property if key exists")
    void testGetPropertyFromSystemProperty() {
        System.setProperty("app.name", "App Name");

        String property = provider.getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("App Name", property)
        );
        System.clearProperty("app.name");
    }


    @Test
    @DisplayName("getProperty should return the correct value from properties file in classpath when key exists")
    void testGetPropertyFromClassPathPropertyFile() {
        provider.loadFromClassPath("application.properties");

        String property = provider.getProperty("app.test.property");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("value", property)
        );
    }

    @Test
    @DisplayName("getProperty should return the correct value from properties file when key exists")
    void testGetPropertyFromFilePropertyFile() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");

        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        String property = provider.getProperty("app.version");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("1.0", property)
        );
    }

    @Test
    @DisplayName("getProperty should throw LoadPropertiesFileException when key exists")
    void testGetPropertyKeyNotFound() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        assertThrows(RuntimeException.class, () -> provider.getProperty("app.nonexistent"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("getProperty with default value should return default value when key not exists")
    void testGetPropertyDefaultValue() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        String property = provider.getProperty("app.nonexistent", "default");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("default", property)
        );
    }

    @Test
    @DisplayName("getBooleanProperty should return the correct boolean value from properties file when key exists")
    void testGetBooleanPropertyFromFilePropertyFile() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        boolean property = provider.getBooleanProperty("app.boolean", false);

        assertTrue(property);
    }

    @Test
    @DisplayName("getBooleanProperty should return the default value when key not exists")
    void testGetBooleanPropertyDefaultValue() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        provider.loadFromFile(Paths.get(resourceUrl.toURI()).toString());

        boolean property = provider.getBooleanProperty("app.nonexistent", true);

        assertTrue(property);
    }

}