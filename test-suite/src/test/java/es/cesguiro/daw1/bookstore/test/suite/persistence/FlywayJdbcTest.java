package es.cesguiro.daw1.bookstore.test.suite.persistence;

import es.cesguiro.daw1.bookstore.test.suite.BaseTest;
import es.cesguiro.daw1.bookstore.util.context.RequestContext;
import es.cesguiro.daw1.bookstore.util.context.RequestContextHolder;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.sql.SQLException;

public class FlywayJdbcTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(FlywayJdbcTest.class);

    @BeforeAll
    public static void setUp() {
        /*String projectBaseDir = new File(System.getProperty("user.dir")).getParent();
        String testPropertiesFile = projectBaseDir + "/config/test.properties";
        System.setProperty("app.properties.location", testPropertiesFile);
        PropertyUtil.loadPropertyFiles();*/

        String url = PropertyUtil.getPropertyProvider().getProperty("app.datasource.url");
        String username = PropertyUtil.getPropertyProvider().getProperty("app.datasource.username");
        String password = PropertyUtil.getPropertyProvider().getProperty("app.datasource.password");

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        /*RequestContext requestContext = new RequestContext();
        RequestContextHolder.setRequestContext(requestContext);*/
        try {
            RequestContextHolder.getRequestContext().setConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        logger.info("Creating database...");
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(PropertyUtil.getPropertyProvider().getProperty("app.flyway.locations"))
                .cleanDisabled(PropertyUtil.getPropertyProvider().getBooleanProperty("app.flyway.cleanDisabled", false))
                .load();
        logger.info("Cleaning database...");
        flyway.clean();
        logger.info("Database cleaned successfully");
        logger.info("Migrating database...");
        flyway.migrate();
        logger.info("Database migrated successfully");
    }

    @AfterAll
    static void afterAll() {
        RequestContextHolder.clear();
        System.clearProperty("app.properties.location");
    }

}
