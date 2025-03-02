package es.cesguiro.daw1.bookstore.web.listener;

import es.cesguiro.daw1.bookstore.util.exception.Error500;
import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import javax.naming.InitialContext;
import javax.sql.DataSource;

//@WebListener
public class FlywayListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(FlywayListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing Flyway...");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/" + PropertyUtil.getPropertyProvider().getProperty("app.datasource.jndi"));

            //DataSource dataSource = ContextUtil.getContextProvider().getDataSource();
            Boolean cleanDisabled = PropertyUtil.getPropertyProvider().getBooleanProperty("app.flyway.cleanDisabled", true);
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations(PropertyUtil.getPropertyProvider().getProperty("app.flyway.locations"))
                    .cleanDisabled(cleanDisabled)
                    .load();
            if(!cleanDisabled) {
                try {
                    logger.info("Flyway clean started");
                    flyway.clean();
                    logger.info("Flyway clean completed");
                } catch (Exception e) {
                    logger.error("Flyway clean failed", e);
                }
            }
            logger.info("Flyway migration started");
            flyway.migrate();
            logger.info("Flyway migration completed");
        } catch (Exception e) {
            logger.error("Flyway migration failed", e);
            throw new Error500("Flyway migration failed");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Flyway context destroyed");
    }

}
