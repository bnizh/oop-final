package listeners;
import DataBase.DBConnection;
import DataBase.DBFactory;
import Managers.SiteConstants;
import Objects.Category;
import Objects.Item;
import Objects.ObjectFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DBConnection dbc= DBFactory.getDBConnection();
        List<Category> catList= dbc.getAllCategories();
        ServletContext servletCont = servletContextEvent.getServletContext();
        servletCont.setAttribute("categories", catList);
        List<Item> itList=dbc.getTopItems(100,0);
        servletCont.setAttribute("items", itList);
    }

    /**
     * saves in servlet context hashmaps
     * this hashmaps are used, to find connections between sessions and who was logged in on that session(user or admin)
     */


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}