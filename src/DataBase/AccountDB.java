package DataBase;

import Objects.Buyer;
import Objects.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class AccountDB implements DBQueries {
    private Connection con;

    public AccountDB(Connection con) {
        this.con = con;
    }

    private Seller getSellerFromBase(PreparedStatement stm){
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()){
                Seller sel = new Seller(rs.getString("username"),rs.getString("password"), rs.getString("email")
                        ,rs.getString("name"),rs.getInt("rating"),rs.getString("mobileNumber"),rs.getInt("voters"), rs.getString("imageUrl"));
                sel.setID(rs.getInt("userID"));
                return  sel;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Seller getSellerByUsername(String username) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where username = \""+username+"\" ")) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Seller getSellerByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where email = \""+email+"\" ")) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seller> getSellerByName(String name) {
        return null;
    }

    @Override
    public Seller getSellerByID(int ID) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where userID = "+ID)) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNewSeller(Seller seller) {
        String s = "insert into "+DBInfo.MYSQL_DATABASE_Users_table+" (password, userName, name, typeOfUser, rating, voters, mobileNumber, imageUrl, email) values("+"\""+seller.getPassword()+"\""+","+
                "\"" +seller.getUserName()+"\""+","+"\""+seller.getName()+"\""+","+1+","+seller.getRating()
                +","+seller.getVoters()+","+"\""+seller.getMobileNumber()+"\""+","+"\""+seller.getImage()+"\""+","+"\""+seller.getEmail()+"\""+")";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Seller> getAllSeller() {
        return null;
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where username = \""+userName+"\" ")) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Buyer getBuyerFromBase(PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                Buyer b = new Buyer(rs.getString("username"), rs.getString("password"), rs.getString("email")
                        , rs.getString("name"), rs.getInt("rating"), rs.getString("mobileNumber"), rs.getInt("voters"), rs.getString("imageUrl"));
                b.setID(rs.getInt("userID"));
                return b;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Buyer getBuyerByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where email = \""+email+"\" ")) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer getBuyerByID(int ID) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where userID = "+ID)) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Buyer> getBuyerByName(String name) {
        return null;
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        String s = "insert into "+DBInfo.MYSQL_DATABASE_Users_table+" (password, userName, name, typeOfUser, rating, voters, mobileNumber, imageUrl, email) values("+"\""+buyer.getPassword()+"\""+","+
                "\"" +buyer.getUserName()+"\""+","+"\""+buyer.getName()+"\""+","+1+","+buyer.getRating()
                +","+buyer.getVoters()+","+"\""+buyer.getMobileNumber()+"\""+","+"\""+buyer.getImage()+"\""+","+"\""+buyer.getEmail()+"\""+")";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Buyer> getAllBuyer() {
        return null;
    }
}
