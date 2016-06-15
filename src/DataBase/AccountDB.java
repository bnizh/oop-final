package DataBase;

import Objects.*;

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
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where username = \""+username+"\" AND typeOfUser=1")) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Seller getSellerByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where email = \""+email+"\" AND typeOfUser=1")) {
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

        s = "Select ownerID from "+DBInfo.MYSQL_DATABASE__Tags_table+" where tagType ='user'";
        List<Integer> ls = getIDsByTag(s);
        for(int i = 0; i< ls.size(); i++){
            Seller sel = getSellerByID(ls.get(i));
            if(!list.contains(sel)){
                list.add(sel);
            }
        }
        return list;
    }

    private List<Integer> getIDsByTag(String s){
        ArrayList<Integer> ls = new ArrayList<Integer>();
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()){
                    ls.add(rs.getInt("ownerID"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
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
        return Helper(s);
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
        return Helper(s);
    }

    @Override
    public boolean deleteSeller(int sellerID) {
        String s ="DELETE FROM "+DBInfo.MYSQL_DATABASE_Users_table +" where userID ="+ sellerID;
        return  Helper(s);
    }

    @Override
    public boolean deleteBuyer(int buyerID) {
        String s ="DELETE FROM "+DBInfo.MYSQL_DATABASE_Users_table +" where userID ="+ buyerID;
        return Helper(s);
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where username = '"+userName+"' AND typeOfUser=0")) {
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
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table+" where email = \""+email+"\" AND typeOfUser=0")) {
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
        return Helper(s);
    }

    @Override
    public boolean updateBuyer(Buyer buyer) {
        String s = "update "+DBInfo.MYSQL_DATABASE_Users_table +" set userName ="+'\"'+buyer.getUserName()+'\"'+", password ="+'\"'+buyer.getPassword()+'\"'
                +", name ="+'\"'+buyer.getName()+'\"'+",email ="+'\"'+buyer.getEmail()+'\"'+", mobileNumber="+'\"'+
                buyer.getMobileNumber()+'\"'+", imageUrl ="+'\"'+buyer.getImage()+'\"'+" where userID ="+buyer.getID();
        return Helper(s);
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

    @Override
    public boolean addItem(Item it) {
       String s = "Insert into " + DBInfo.MYSQL_DATABASE_Items_table+" (itemName, itemImageUrl,categoryID,ownerID,price,rating, voters) values ('"+it.getName()+"','"+it.getImage()+"',"
               +it.getCategoryID()+","+it.getOwnerID()+","+it.getPrice()+","+it.getRating()+","+it.getVoters()+")";
        return Helper(s);
    }

    @Override
    public boolean deleteItem(int id) {
        String s = "Delete from "+DBInfo.MYSQL_DATABASE_Items_table+" where itemID ="+id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void getItems(List<Item> ls ,String s) {
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getNewItem(rs.getString("ItemName"), rs.getInt("itemID"), rs.getInt("ownerID"),
                            rs.getString("itemImageUrl"), rs.getInt("price"), rs.getInt("categoryID"), rs.getInt("rating"),
                            rs.getInt("voters")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Item getItemById(int id) {
        String s = "select * from "+DBInfo.MYSQL_DATABASE_Items_table+" where itemID ="+id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewItem(rs.getString("ItemName"), rs.getInt("itemID"), rs.getInt("ownerID"),
                            rs.getString("itemImageUrl"), rs.getInt("price"), rs.getInt("categoryID"), rs.getInt("rating"),
                            rs.getInt("voters"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemsBySeller(int sellerID) {
        List<Item> ls = new ArrayList<Item>();
        String s = "select * from "+DBInfo.MYSQL_DATABASE_Items_table+" where ownerID ="+sellerID;
        getItems(ls,s);
        return ls;
    }

    @Override
    public List<Item> getItemsByName(String name) {
        List<Item> ls = new ArrayList<Item>();
        String s = "select * from "+DBInfo.MYSQL_DATABASE_Items_table+" where ItemName ='"+name+"'";
        getItems(ls,s);
        return ls;
    }

    private boolean Helper(String s){
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> getTopItems(int numberOfItems) {
        List<Item> ls = new ArrayList<Item>();
        String s ="Select *, (rating / voters) as avg from "+ DBInfo.MYSQL_DATABASE_Items_table+" order by avg desc limit " +numberOfItems;
        getItems(ls,s);
        return ls;
    }

    @Override
    public boolean updateItem(Item it) {
        String s = "update "+DBInfo.MYSQL_DATABASE_Items_table+" set itemName ='"+it.getName()+"',itemImageUrl ='"+it.getImage()+
                "',categoryID ="+it.getCategoryID()+", ownerID="+it.getOwnerID()+", price="+it.getPrice()+", rating="+it.getRating()
                +",voters ="+it.getVoters()+" where itemID="+it.getID();
        return Helper(s);
    }

    @Override
    public boolean deleteAllItemsForSeller(int indexOfSeller) {
        String s = "delete from "+ DBInfo.MYSQL_DATABASE_Items_table+" where ownerID =" +indexOfSeller;
        return Helper(s);
    }

    @Override
    public boolean addCategory(Category cat) {
        String s = "insert into "+DBInfo.MYSQL_DATABASE_Categories_table+" (categoryName) values ('"+cat.getName()+ "')";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Category cat) {
        String s = "delete from "+DBInfo.MYSQL_DATABASE_Categories_table+" where categoryName= '"+cat.getName()+"'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> ls = new ArrayList<Category>();
        String s = "select * from "+DBInfo.MYSQL_DATABASE_Categories_table;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getNewCategory(rs.getInt("categoryID"), rs.getString("categoryName")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }


    @Override
    public Comment getUserCommentByID(int id) {
        String s = "select * from "+ DBInfo.MYSQL_DATABASE_UsersComments_table+" where commentID ="+id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewComment(rs.getInt("ownerID"),rs.getInt("writerID"),
                            rs.getInt("commentID"),rs.getString("comm"),
                            new java.util.Date(rs.getDate("dateOfComment").getTime()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getUserCommentsByOwner(int userID) {
        List<Comment> ls = new ArrayList<Comment>();
        String s = "select * from "+DBInfo.MYSQL_DATABASE_UsersComments_table+" where ownerID ="+userID;
        commentHelper(ls,s);
        return ls;
    }

    @Override
    public boolean deleteUserComment(int id) {
        String s = "Delete from "+DBInfo.MYSQL_DATABASE_UsersComments_table+ " where commentID =" + id;
        return Helper(s);
    }


    @Override
    public boolean deleteAllCommentForUser(int userID) {
        String s = " Delete from "+DBInfo.MYSQL_DATABASE_UsersComments_table+" where ownerID ="+userID;
        return Helper(s);
    }

    private void commentHelper(List<Comment> ls, String s){
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getNewComment(rs.getInt("ownerID"),rs.getInt("writerID"),
                            rs.getInt("commentID"),rs.getString("comm"),
                            new java.util.Date(rs.getDate("dateOfComment").getTime())));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Comment> getUserCommentsByWriter(int userID) {
        List<Comment> ls = new ArrayList<Comment>();
        String s = "select * from "+DBInfo.MYSQL_DATABASE_UsersComments_table+" where writerID ="+userID;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public boolean updateUserComment(Comment c) {
        String s = "Update "+ DBInfo.MYSQL_DATABASE_UsersComments_table+" set comm ='"+c.getComment()+"', ownerID ="+
                c.getOwnerID()+", writerID="+c.getWriterID()+" ,dateOfComment ='"+new java.sql.Date(System.currentTimeMillis())+"'";
        return Helper(s);
    }

    @Override
    public boolean addCommentToUser(Comment c) {
        String s = "Insert into "+DBInfo.MYSQL_DATABASE_UsersComments_table+ " ( writerID, ownerID, comm , dateOfComment ) values ( "+
                c.getWriterID()+" ,"+c.getOwnerID()+" ,'"+c.getComment()+"', '"+new java.sql.Date(System.currentTimeMillis())+"' )";
       return Helper(s);

    }

    public Category getCategory(String name) {
        String s = "select * from "+DBInfo.MYSQL_DATABASE_Categories_table+ " where categoryName = '"+name+"'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if(rs.next()) {
                    return  ObjectFactory.getNewCategory(rs.getInt("categoryID"), rs.getString("categoryName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addHashTagToUser(int userID, String tag) {
        String s = "insert into "+ DBInfo.MYSQL_DATABASE__Tags_table + " (tagName, tagType, ownerID) values( '"+tag+"' ,'user',"+userID+")";
        return Helper(s);
    }

    @Override
    public boolean addHashTagToItem(int itemID, String tag) {
        String s = "insert into "+ DBInfo.MYSQL_DATABASE__Tags_table + " (tagName, tagType, ownerID) values ('"+tag+"', 'item',"+itemID+")";
        return Helper(s);
    }
}
