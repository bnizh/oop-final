package DataBase;

import Accounts.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER + "/" + MyDBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD)) {

            return con;
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

}
