package es.cesguiro.daw1.bookstore.test.suite;

import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

public class BaseTest {

    public BaseTest() {
        String projectBaseDir = new File(System.getProperty("user.dir")).getParent();
        String testPropertiesFile = projectBaseDir + "/config/test.properties";
        System.setProperty("app.properties.location", testPropertiesFile);
        PropertyUtil.loadPropertyFiles();

        RequestContext requestContext = new RequestContext();
        RequestContextHolder.setRequestContext(requestContext);
    }
}
