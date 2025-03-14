package es.cesguiro.daw1.bookstore.util.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyUtilIntegrationTest {

    @BeforeEach
    void setUp() {
        String projectBaseDir = new File(System.getProperty("user.dir")).getParent();
        System.out.println(projectBaseDir);
        String testPropertiesFile = projectBaseDir + "/config/test.properties";
        System.setProperty("app.properties.location", testPropertiesFile);
        PropertyUtil.loadPropertyFiles();
    }

    @AfterEach
    void tearDown(){
        System.clearProperty("app.properties.location");
    }


    @Test
    @DisplayName("Test getProperty should return correct value if key exists in system properties")
    void testGetPropertyFromSystemProperties() {
        System.setProperty("app.name", "App Name");
        String property = PropertyUtil.getPropertyProvider().getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("App Name", property)
        );
    }

    @Test
    @DisplayName("getProperty should return correct value if key exists in test-util.properties")
    void testGetPropertyFromPropertiesFile() {
        String property = PropertyUtil.getPropertyProvider().getProperty("app.version");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("1.0", property)
        );
    }

    @Test
    @DisplayName("Test getProperty should return default value if key is not found")
    void testGetPropertyDefaultValue() {
        String property = PropertyUtil.getPropertyProvider().getProperty("app.nonexistent", "default");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("default", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw RuntimeException")
    void testGetNonExistingProperty() {
        assertThrows(RuntimeException.class, () -> PropertyUtil.getPropertyProvider().getProperty("non.existing"));
    }

    @Test
    @DisplayName("Test get property with empty key should throw RuntimeException")
    void testGetPropertyEmptyKey() {
        assertThrows(RuntimeException.class, () -> PropertyUtil.getPropertyProvider().getProperty(""));
    }

    @Test
    @DisplayName("Test get property with null key should throw RuntimeException")
    void testGetPropertyNullKey() {
        assertThrows(RuntimeException.class, () -> PropertyUtil.getPropertyProvider().getProperty(null));
    }
}
