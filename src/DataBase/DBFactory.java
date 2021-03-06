package DataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class DBFactory {
    public static AccountDB getAccountDB(Connection con){
        return new AccountDB(con);
    }
    public static DBConnection getDBConnection(){
        return new DBConnection();
    }
    public static ConnectionPool getConnectionPool () throws SQLException {return ConnectionPool.getInstance();}
}
