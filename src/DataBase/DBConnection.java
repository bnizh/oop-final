package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            this.con = con;
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    private Connection con;

    public Connection getConnection() {
        return con;
    }

}
