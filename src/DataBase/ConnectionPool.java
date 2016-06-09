package DataBase;

/**
 * Created by Boris on 09.06.2016.
 */
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.SQLException;

public class ConnectionPool {
    private int maxActive = 100;
    private int maxWait = 10000;
    private int maxIdle = 10;
    private static BasicDataSource eventDataSource;

    public ConnectionPool() throws SQLException{
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(DBInfo.MYSQL_DATABASE_SERVER);
        dataSource.setUsername(DBInfo.MYSQL_USERNAME);
        dataSource.setPassword(DBInfo.MYSQL_PASSWORD);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setMaxIdle(maxIdle);
        eventDataSource = dataSource;
    }

    public BasicDataSource getEventDataSource()
    {
        return eventDataSource;
    }
}
