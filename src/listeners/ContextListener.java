package listeners;
import DataBase.DBFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
        ServletContext servletCont = servletContextEvent.getServletContext();
        servletCont.setAttribute("dataBase", DBFactory.getDBConnection());
    }

    /**
     * saves in servlet context hashmaps
     * this hashmaps are used, to find connections between sessions and who was logged in on that session(user or admin)
     */


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}