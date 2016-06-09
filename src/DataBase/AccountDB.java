package DataBase;

import Objects.Buyer;
import Objects.ObjectFactory;
import Objects.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AccountDB implements DBQueries {
    private Connection con;

    public AccountDB(Connection con) {
        this.con = con;
    }

    private Seller getSellerFromBase(PreparedStatement stm){
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()){
                return  ObjectFactory.getNewSeller(rs.getString("username"),rs.getString("password"), rs.getString("email")
                        ,rs.getString("name"),rs.getInt("rating"),rs.getInt("voters"), rs.getString("mobileNumber"),rs.getString("imageUrl"),rs.getInt("userID"));
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
        ArrayList<Seller> list = new ArrayList<Seller>();
        String s ="Select * from "+DBInfo.MYSQL_DATABASE_Users_table+" where typeOfUser =" + 1+" and name ="+'\"'+name+'\"';
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralSellersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

    private void getSeveralSellersFromBase(List<Seller> list, PreparedStatement stm){
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()){
                list.add(ObjectFactory.getNewSeller(rs.getString("username"),rs.getString("password"), rs.getString("email")
                        ,rs.getString("name"),rs.getInt("rating"),rs.getInt("voters"), rs.getString("mobileNumber"),rs.getString("imageUrl"),rs.getInt("userID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        ArrayList<Seller> list = new ArrayList<Seller>();
        String s ="Select * from "+DBInfo.MYSQL_DATABASE_Users_table+" where typeOfUser =" + 1;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralSellersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public boolean updateSeller(Seller seller) {
        String s = "update "+DBInfo.MYSQL_DATABASE_Users_table +" set userName ="+'\"'+seller.getUserName()+'\"'+", password ="+'\"'+seller.getPassword()+'\"'
                +", name ="+'\"'+seller.getName()+'\"'+",email ="+'\"'+seller.getEmail()+'\"'+", mobileNumber="+'\"'+
                seller.getMobileNumber()+'\"'+", imageUrl ="+'\"'+seller.getImage()+'\"'+" where userID ="+seller.getID();
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSeller(int sellerID) {
        String s ="DELETE FROM "+DBInfo.MYSQL_DATABASE_Users_table +" where userID ="+ sellerID;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBuyer(int buyerID) {
        String s ="DELETE FROM "+DBInfo.MYSQL_DATABASE_Users_table +" where userID ="+ buyerID;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

                return ObjectFactory.getNewBuyer(rs.getString("username"),rs.getString("password"), rs.getString("email")
                        ,rs.getString("name"),rs.getInt("rating"),rs.getInt("voters"),rs.getString("mobileNumber"), rs.getString("imageUrl"),rs.getInt("userID"));

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
    public List<Buyer> getBuyerByName(String name){
        ArrayList<Buyer> list = new ArrayList<>();
        String s ="Select * from "+DBInfo.MYSQL_DATABASE_Users_table+" where typeOfUser =" +0+" and name ="+'\"'+name+'\"';
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralBuyersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        String s = "insert into "+DBInfo.MYSQL_DATABASE_Users_table+" (password, userName, name, typeOfUser, rating, voters, mobileNumber, imageUrl, email) values("+"\""+buyer.getPassword()+"\""+","+
                "\"" +buyer.getUserName()+"\""+","+"\""+buyer.getName()+"\""+","+0+","+buyer.getRating()
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
    public boolean updateBuyer(Buyer buyer) {
        String s = "update "+DBInfo.MYSQL_DATABASE_Users_table +" set userName ="+'\"'+buyer.getUserName()+'\"'+", password ="+'\"'+buyer.getPassword()+'\"'
                +", name ="+'\"'+buyer.getName()+'\"'+",email ="+'\"'+buyer.getEmail()+'\"'+", mobileNumber="+'\"'+
                buyer.getMobileNumber()+'\"'+", imageUrl ="+'\"'+buyer.getImage()+'\"'+" where userID ="+buyer.getID();
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void getSeveralBuyersFromBase(List<Buyer> list, PreparedStatement stm){
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()){
                list.add(ObjectFactory.getNewBuyer(rs.getString("username"),rs.getString("password"), rs.getString("email")
                        ,rs.getString("name"),rs.getInt("rating"),rs.getInt("voters"),rs.getString("mobileNumber"), rs.getString("imageUrl"),rs.getInt("userID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Buyer> getAllBuyer() {
        ArrayList<Buyer> list = new ArrayList<>();
        String s ="Select * from "+DBInfo.MYSQL_DATABASE_Users_table+" where typeOfUser =" + 0;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralBuyersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
