package es.cesguiro.daw1.bookstore.web;

import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.controller.Controller;
import es.cesguiro.daw1.bookstore.web.controller.FrontController;
import es.cesguiro.daw1.bookstore.web.factory.*;
import es.cesguiro.daw1.bookstore.web.filter.*;
import es.cesguiro.daw1.bookstore.web.listener.FlywayListener;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.ContextResource;

import javax.sql.DataSource;
import java.util.List;

public class App {
    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        // Configurar Log4j2 como LogManager de java.util.logging
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        // Redirigir todos los logs de java.util.logging (JUL) a Log4j2
        java.util.logging.LogManager.getLogManager().reset();

        // Cargar propiedades (application.properties y fichero definido en la variable de entorno app.properties.location)
        logger.info("Loading properties...");
        PropertyUtil.loadPropertyFiles();

        String hostname = PropertyUtil.getPropertyProvider().getProperty("app.server.hostname", "localhost");
        int port = Integer.parseInt(PropertyUtil.getPropertyProvider().getProperty("app.server.port", "8080"));

        logger.info("Initializing Tomcat...");
        TomcatServer tomcatServer = new TomcatServer(port, hostname);
        try {
            String webappDirLocation = System.getProperty("user.dir") + "/web/src/main/resources";
            tomcatServer.setContext("/", webappDirLocation);
            // Configura el datasource
            tomcatServer.setDatasource(buildDatasource());
            // Registrar los listeners
            tomcatServer.getContext().addApplicationListener(FlywayListener.class.getName());
            // Registrar los filtros
            tomcatServer.registerFilter("requestContextFilter", RequestContextFilter.class, "/*");
            tomcatServer.registerFilter("localeFilter", LocaleFilter.class, "/*");
            tomcatServer.registerFilter("authFilter", AuthFilter.class, "/*");
            tomcatServer.registerFilter("loginFilter", LoginFilter.class, "/login");
            tomcatServer.registerFilter("logoutFilter", LogoutFilter.class, "/logout");
            // Registrar los servlets. Registrar el DefaultServlet para servir archivos estáticos (DeafaultServlet es el encargado de servir contenido estático)
            FrontController frontController = getFrontController();
            tomcatServer.addServlet("frontController", frontController, "/*");
            DefaultServlet defaultServlet = new DefaultServlet();
            tomcatServer.addServlet("defaultServer", defaultServlet, "/static/*");
            // Inicializa Tomcat
            logger.info("Starting Tomcat...");
            tomcatServer.getTomcat().getConnector();
            tomcatServer.getTomcat().start();
            logger.info("Tomcat started on port {}", port);
            tomcatServer.getTomcat().getServer().await();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static FrontController getFrontController() {
        List<Controller> controllers = List.of(
                BookFactory.createBookController(),
                MainFactory.createMainController(),
                LocaleFactory.createLocaleController(),
                AuthFactory.createAuthController(),
                UserFactory.createUserController()
        );
        return new FrontController(controllers);
    }

    private static ContextResource buildDatasource() {
        ContextResource resource = new ContextResource();
        resource.setName(PropertyUtil.getPropertyProvider().getProperty("app.datasource.jndi"));
        resource.setType(DataSource.class.getName());
        resource.setProperty("driverClassName", PropertyUtil.getPropertyProvider().getProperty("app.datasource.driverClassName"));
        resource.setProperty("url", PropertyUtil.getPropertyProvider().getProperty("app.datasource.url"));
        resource.setProperty("username", PropertyUtil.getPropertyProvider().getProperty("app.datasource.username"));
        resource.setProperty("password", PropertyUtil.getPropertyProvider().getProperty("app.datasource.password"));
        resource.setProperty("maxActive", PropertyUtil.getPropertyProvider().getProperty("app.datasource.maxActive", "20000"));
        resource.setProperty("maxWait", PropertyUtil.getPropertyProvider().getProperty("app.datasource.maxWait", "10000"));
        resource.setProperty("minIdle", PropertyUtil.getPropertyProvider().getProperty("app.datasource.minIdle", "10"));
        resource.setProperty("maxIdle", PropertyUtil.getPropertyProvider().getProperty("app.datasource.maxIdle", "20"));
        resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");

        // Agrega la validación de la conexión
        resource.setProperty("validationQuery", "SELECT 1");  // O puedes usar otro tipo de consulta dependiendo de tu DB
        resource.setProperty("testOnBorrow", "true");  // Activar la validación al obtener la conexión
        resource.setProperty("testWhileIdle", "true");  // Activar la validación mientras la conexión está inactiva
        resource.setProperty("timeBetweenEvictionRunsMillis", "30000");  // Tiempo entre validaciones

        return resource;
    }

}
