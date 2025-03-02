package es.cesguiro.daw1.bookstore.util.property;

import es.cesguiro.daw1.bookstore.util.exception.Error500;

public class PropertyUtil {

    private static PropertyProvider propertyProvider;

    private PropertyUtil() {
        throw new Error500("Utility class");
    }

    public static PropertyProvider getPropertyProvider() {
        if (propertyProvider == null) {
            throw new Error500("Property provider is required");
        }
        return propertyProvider;
    }

    public static void loadPropertyFiles() {
        if (propertyProvider == null) {
            propertyProvider = new DefaultPropertyProvider();
        }
        if (PropertyUtil.class.getClassLoader().getResource("application.properties") != null) {
            propertyProvider.loadFromClassPath("application.properties");
        }

        String propertyFile = System.getProperty("app.properties.location", System.getenv("app.properties.location"));
        if (propertyFile != null) {
            propertyProvider.loadFromFile(propertyFile);
        }
    }

    public static void setPropertyProvider(PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new Error500("Property provider is required");
        }
        PropertyUtil.propertyProvider = propertyProvider;
    }

    /**
     * Reset the property provider to null. This method is intended for testing purposes only.
     */
    public static void resetPropertyProvider() {
        PropertyUtil.propertyProvider = null;
    }
}