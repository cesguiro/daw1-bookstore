package es.cesguiro.daw1.bookstore.util.property;


/**
 * Provides an abstraction for retrieving property values.
 * <p>
 * Implementations may fetch properties from different sources such as system properties,
 * environment variables, or configuration files.
 * </p>
 */
public interface PropertyProvider {

    void loadFromClassPath(String fileName);

    void loadFromFile(String fileName);

    /**
     * Retrieves the value of a property by key.
     *
     * @param key The property key to look for.
     * @return The value associated with the given key.
     * @throws KeyNotFoundException If the key is not found.
     */
    String getProperty(String key);

    /**
     * Retrieves the value of a property by key, returning a default value if not found.
     *
     * @param key The property key.
     * @param defaultValue The value to return if the key is not found.
     * @return The property value or the default value if not found.
     */
    String getProperty(String key, String defaultValue);

    /**
     * Retrieves a boolean property by key, returning a default value if not found.
     *
     * @param key The property key.
     * @param defaultValue The value to return if the key is not found.
     * @return The boolean property value or the default value if not found.
     */
    Boolean getBooleanProperty(String key, Boolean defaultValue);
}
