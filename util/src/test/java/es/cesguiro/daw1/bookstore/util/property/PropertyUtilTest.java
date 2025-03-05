package es.cesguiro.daw1.bookstore.util.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyUtilTest {

    @Mock
    private PropertyProvider mockPropertyProvider;

    @BeforeEach
    void setUp() {
        PropertyUtil.setPropertyProvider(mockPropertyProvider);
    }

    @AfterEach
    void tearDown() {
        PropertyUtil.resetPropertyProvider();
    }

    @Test
    @DisplayName("Test get PropertyProvider should throw Error500 if PropertyProvider is null")
    void testGetPropertyProvider() {
        PropertyUtil.resetPropertyProvider();
        assertThrows(Error500.class, () -> PropertyUtil.getPropertyProvider(),
                "getPropertyProvider should throw PropertyUtilException if PropertyProvider is null");
    }

    @Test
    @DisplayName("Test set null PropertyProvider should throw Error500")
    void testSetPropertyProviderNull() {
        assertThrows(Error500.class, () -> PropertyUtil.setPropertyProvider(null),
                "getInstance with null PropertyProvider should throw PropertyUtilException");
    }

    @Test
    @DisplayName("loadPropertyFiles should initialize propertyProvider if not already initialized")
    void testLoadPropertyFilesInitializeProvider() {
        PropertyUtil.resetPropertyProvider();
        PropertyUtil.loadPropertyFiles();

        assertNotNull(PropertyUtil.getPropertyProvider(), "PropertyProvider should be initialized");
    }

    @Test
    @DisplayName("loadPropertyFiles should load application.properties from classpath if it exists")
    void testLoadFromClassPath() {
        String classPathFile = "application.properties";

        PropertyUtil.loadPropertyFiles();
        verify(mockPropertyProvider, times(1)).loadFromClassPath(classPathFile);
    }

    @Test
    @DisplayName("loadPropertyFiles should load properties from custom file location if specified")
    void testLoadFromCustomFile() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("test-util.properties");
        String customFilePath = Paths.get(resourceUrl.toURI()).toString();
        System.setProperty("app.properties.location", customFilePath);

        PropertyUtil.loadPropertyFiles();

        verify(mockPropertyProvider, times(1)).loadFromFile(customFilePath);
    }

    @Test
    @DisplayName("loadPropertyFiles should not load properties from custom file location if not specified")
    void testLoadFromCustomFileNotSpecified() {
        System.clearProperty("app.properties.location");

        PropertyUtil.loadPropertyFiles();

        verify(mockPropertyProvider, never()).loadFromFile(anyString());
    }



}