package DataBase;

import java.sql.Connection;

public class DBFactory {
    public static DBQueries getDBQueries(Connection con){
        return new AccountDB(con);
    }
    public static DBConnection getDBConnection(){
        return new DBConnection();
    }
}
