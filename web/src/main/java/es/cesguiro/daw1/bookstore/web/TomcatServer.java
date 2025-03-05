package es.cesguiro.daw1.bookstore.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.*;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.naming.NamingException;
import java.io.File;


public class TomcatServer {

    private final Tomcat tomcat;
    private Context context;

    public TomcatServer(int port, String hostname) {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setHostname(hostname);
        tomcat.enableNaming();
    }

    public Tomcat getTomcat() {
        return tomcat;
    }

    public void setContext(String contextPath, String webappDirLocation) {
        File webappDir = new File(webappDirLocation);
        if (!webappDir.exists()) {
            throw new RuntimeException("webappdir does not exists: " + webappDir.getAbsolutePath());
        }
        // Añadimo un Context a Tomcat. Puede haber varios Context en Tomcat
        context = tomcat.addWebapp(contextPath, new File(webappDirLocation).getAbsolutePath());
    }

    public Context getContext() {
        return context;
    }

    public void registerFilter(String filterName, Class<? extends Filter> filterClass, String... urlPatterns) {
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

    public void addServlet(String servletName, HttpServlet servlet, String urlPattern) {
        Tomcat.addServlet(context, servletName, servlet);
        for (String pattern : urlPattern.split(",")) {
            context.addServletMappingDecoded(pattern, servletName);
        }
    }

    public void start() throws ServletException, LifecycleException, NamingException {
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    public void setDatasource(ContextResource dataSource) {
        context.getNamingResources().addResource(dataSource);
    }

}
