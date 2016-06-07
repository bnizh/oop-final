package DataBase;

import Objects.Buyer;
import Objects.Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection implements  DBQueries{



    @Override
    public Seller getSellerByUsername(String companyName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getSellerByUsername(companyName);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public Seller getSellerByEmail(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getSellerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Seller> getSellerByName(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getSellerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public Seller getSellerByID(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getSellerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean addNewSeller(Seller seller) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return DBFactory.getDBQueries(con).addNewSeller(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getAllSeller();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean updateSeller(Seller seller) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).updateSeller(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean deleteSeller(int sellerID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).deleteSeller(sellerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean deleteBuyer(int buyerID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).deleteBuyer(buyerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getBuyerByUsername(userName);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public Buyer getBuyerByEmail(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getBuyerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public Buyer getBuyerByID(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getBuyerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Buyer> getBuyerByName(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getBuyerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).addNewBuyer(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public boolean updateBuyer(Buyer buyer) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).updateBuyer(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Buyer> getAllBuyer() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER + "/" + DBInfo.MYSQL_DATABASE_NAME +
                        "?characterEncoding=UTF8&useSSL=false", DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD)) {

            return  DBFactory.getDBQueries(con).getAllBuyer();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }
}
