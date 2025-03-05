package es.cesguiro.daw1.bookstore.util.property;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultPropertyProvider implements PropertyProvider{

    private final Properties properties = new Properties();

    public void loadFromClassPath(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("File not found in classpath: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file from classpath: " + fileName);
        }
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + fileName);
        }

        try (InputStream input = new FileInputStream(new File(fileName))) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + fileName, e);
        }
    }

    @Override
    public String getProperty(String key) {
        if (key == null || key.isEmpty()) {
            throw new RuntimeException("Key cannot be null or empty");
        }

        String value = System.getProperty(key, System.getenv(key) != null ? System.getenv(key) : properties.getProperty(key));
        if (value == null) {
            throw new RuntimeException("Key not found or has a null value: " + key);
        }
        return value;
    }


    /**
     * Retrieves the value of a property by key, returning a default value if not found.
     * <p>
     * The search order is the same as {@link #getProperty(String)}, but if the key is
     * not found in any source, the provided default value is returned.
     * </p>
     *
     * @param key          The property key.
     * @param defaultValue The default value to return if the key is not found.
     * @return The property value or the default value if not found.
     */
    @Override
    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, System.getenv(key) != null ? System.getenv(key) : properties.getProperty(key, defaultValue));

    }

    /**
     * Retrieves a boolean property by key, returning a default value if not found.
     * <p>
     * The value is resolved in the same way as {@link #getProperty(String, String)} and
     * is then parsed as a boolean.
     * </p>
     *
     * @param key          The property key.
     * @param defaultValue The default boolean value to return if the key is not found.
     * @return The boolean property value or the default value if not found.
     */
    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        return Boolean.parseBoolean(getProperty(key, String.valueOf(defaultValue)));
    }

    public Properties getProperties() {
        return properties;
    }

}
