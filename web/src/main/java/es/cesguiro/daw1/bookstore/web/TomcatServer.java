package es.cesguiro.daw1.bookstore.web;

import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import es.cesguiro.daw1.bookstore.web.filter.RequestContextFilter;
import es.cesguiro.daw1.bookstore.web.filter.LocaleFilter;
import es.cesguiro.daw1.bookstore.web.listener.FlywayListener;
import es.cesguiro.daw1.bookstore.web.controller.FrontController;
import jakarta.servlet.*;
import org.apache.catalina.*;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;


public class TomcatServer {

    private static final Logger logger = LogManager.getLogger(TomcatServer.class);

    public static void main(String[] args) throws LifecycleException, NamingException {

    // Configurar Log4j2 como LogManager de java.util.logging
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    // Redirigir todos los logs de java.util.logging (JUL) a Log4j2
    java.util.logging.LogManager.getLogManager().reset();

    // Cargar propiedades (application.properties y fichero definido en la variable de entorno app.properties.location)
    logger.info("Loading properties...");
    PropertyUtil.loadPropertyFiles();


    // Iniciar Tomcat
    logger.info("Starting Tomcat...");
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.setHostname(PropertyUtil.getPropertyProvider().getProperty("app.server.hostname", "localhost"));

    // Configurar el directorio de la aplicación web
    String webappDirLocation = "web/src/main/resources";
    File webappDir = new File(webappDirLocation);
    if (!webappDir.exists()) {
        logger.warn("webappdir does not exists: " + webappDir.getAbsolutePath());
        return;
    }
    Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
    context.addWelcomeFile("index.html");


    // 🔥 Habilitar escaneo automático de @WebServlet
    /*File classDir = new File("web/target/classes");
    if (!classDir.exists()) {
        logger.error("Class directory does not exists: " + classDir.getAbsolutePath());
        return;
    }
    WebResourceRoot resources = new StandardRoot(context);
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", classDir.getAbsolutePath(), "/"));
    context.setResources(resources);*/

    // Configurar error pages
    setErrorPages(context);

    // Registrar DataSource
    tomcat.enableNaming();
    context.getNamingResources().addResource(getDataSource());

    // Listener manual
    /*FlywayInitializar flywayInitializar = new FlywayInitializar();
    flywayInitializar.contextInitialized(null);*/

    // Registrar Listener por código
    context.addApplicationListener(FlywayListener.class.getName());

    // Registrar filtros
    registerFilters(context);

    // Registrar el RouterServlet (único sesrvlet de la aplicación)
    Class<FrontController> servletClass = FrontController.class;
    Tomcat.addServlet(context, servletClass.getSimpleName(), servletClass.getName());
    context.addServletMappingDecoded("/*", servletClass.getSimpleName());

    // Registrar el DefaultServlet para servir archivos estáticos (DeafaultServlet es el encargado de servir contenido estático)
    Class<DefaultServlet> defaultServletClass = DefaultServlet.class;
    Tomcat.addServlet(context, defaultServletClass.getSimpleName(), defaultServletClass.getName());
    context.addServletMappingDecoded("/static/*", defaultServletClass.getSimpleName());

    // Iniciar Tomcat
    tomcat.getConnector();
    tomcat.start();
    logger.info("Tomcat started on port 8080");
    tomcat.getServer().await();
}



    private static ContextResource getDataSource() {
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

    private static void registerFilters(Context context) {
        registerFilter(context, "requestContextFilter", RequestContextFilter.class, "/*");
        registerFilter(context, "localeFilter", LocaleFilter.class, "/*");

    }

    private static void registerFilter(Context context, String filterName, Class<? extends Filter> filterClass, String... urlPatterns) {
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterClass(filterClass.getName());
        filterDef.setFilterName(filterName);

        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName(filterName);
        for (String urlPattern : urlPatterns) {
            filterMap.addURLPattern(urlPattern);
        }

        context.addFilterDef(filterDef);
        context.addFilterMap(filterMap);
    }

    private static void setErrorPages(Context context) {
        /*ErrorPage error500 = new ErrorPage();
        error500.setExceptionType(Error500.class.getName());
        //error500.setErrorCode(500);
        error500.setLocation("/error");
        context.addErrorPage(error500);

        ErrorPage error404 = new ErrorPage();
        error404.setExceptionType(Error404.class.getName());
        //error404.setErrorCode(404);
        //error404.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
        error404.setLocation("/error");
        context.addErrorPage(error404);

        ErrorPage error400 = new ErrorPage();
        error400.setExceptionType(Error400.class.getName());
        error400.setLocation("/error");
        context.addErrorPage(error400);*/

    }

}
