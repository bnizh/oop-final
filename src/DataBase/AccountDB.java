package DataBase;

import Objects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Managers.SiteConstants.*;


public class AccountDB implements DBQueries {
    private Connection con;

    public AccountDB(Connection con) {
        this.con = con;
    }

    private Message getMessageFromBase(PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return ObjectFactory.getNewMessage(rs.getBoolean("isRead"), rs.getInt("writerID"), rs.getInt("receiverID"),
                        rs.getString("message"), rs.getTimestamp("dateOfMessage"), rs.getInt("messageID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getSeveralMessageFromBase(List<Message> list, PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(ObjectFactory.getNewMessage(rs.getBoolean("isRead"), rs.getInt("writerID"), rs.getInt("receiverID"),
                        rs.getString("message"), rs.getTimestamp("dateOfMessage"), rs.getInt("messageID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Seller getSellerFromBase(PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return ObjectFactory.getNewSeller(rs.getString("username"), rs.getString("password"), rs.getString("email")
                        , rs.getString("name"), rs.getInt("rating"), rs.getInt("voters"), rs.getString("mobileNumber"), rs.getString("imageUrl"), rs.getInt("userID"), rs.getBoolean("confirmed"), rs.getBoolean("banned"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Admin getAdminFromBase(PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return ObjectFactory.getNewAdmin(rs.getInt("userID"), rs.getString("userName"), rs.getString("name"), rs.getString("email"), rs.getString("password")
                        , rs.getString("imageUrl"), rs.getString("mobileNumber"), rs.getInt("typeOfUser"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getSeveralAdminsFromBase(List<Admin> list, PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(ObjectFactory.getNewAdmin(rs.getInt("userID"), rs.getString("userName"), rs.getString("name"), rs.getString("email"), rs.getString("password")
                        , rs.getString("imageUrl"), rs.getString("mobileNumber"), rs.getInt("typeOfUser")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message getMessageById(int ID) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Message_table + " where messageID = " + ID)) {
            return getMessageFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getMessageByWriterID(int ID,int messageType) {
        ArrayList<Message> list = new ArrayList<Message>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Message_table + " where writerID =" + ID+" AND messageType="+messageType+" order by dateOfMessage desc";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralMessageFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Message> getMessageByReceiverId(int ID,int messageType) {
        ArrayList<Message> list = new ArrayList<Message>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Message_table + " where (receiverID =" + ID+" OR receiverID=0)"+" AND messageType="+messageType+" order by dateOfMessage desc" ;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralMessageFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;    }

    @Override
    public List<Message> getAllAdminMessage() {
        ArrayList<Message> list = new ArrayList<Message>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Message_table + " where  messageType="+MESSAGE_USER_TO_ADMIN+" order by dateOfMessage desc";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralMessageFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;    }



    @Override
    public boolean addMessage(Message message,int messageType) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Message_table + " (writerID, receiverID, message, messageType) " +
                "values(" + message.getWriterID()  + "," + message.getReceiverID() +"," + "\"" + message.getMessageContent() + "\"" +
                ", "+messageType+")";
        return Helper(s);    }

    @Override
    public boolean deleteMessage(int messageID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Message_table + " where messageID =" + messageID;
        return Helper(s);    }

    @Override
    public Admin getAdminByID(int ID) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Admin_table + " where userID = " + ID + " AND" +
                " typeOfUser = " + ADMIN_TYPE)) {
            return getAdminFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin getAdminByUsername(String userName) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Admin_table + " where username = \"" + userName + "\"")) {
            return getAdminFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Admin_table + " where email = \"" + email + "\" ")) {
            return getAdminFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNewAdmin(Admin admin) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Admin_table + " (password, userName, name, typeOfUser, mobileNumber, imageUrl, email) values(" + "\"" + admin.getPassword() + "\"" + "," +
                "\"" + admin.getUserName() + "\"" + "," + "\"" + admin.getName() + "\"" + "," + ADMIN_TYPE + "," + "\"" + admin.getMobileNumber() + "\"" + "," + "\"" + admin.getImageURL() + "\"" + "," + "\"" + admin.getEmail() + "\"" + ")";
        return Helper(s);
    }

    @Override
    public List<Admin> getAllAdmin() {
        ArrayList<Admin> list = new ArrayList<Admin>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Admin_table + " where typeOfUser =" + ADMIN_TYPE;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralAdminsFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteAdmin(int ID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Admin_table + " where userID =" + ID;
        return Helper(s);
    }

    @Override
    public boolean updateAdminWithoutImage(Admin admin) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Admin_table + " set userName =" + '\"' + admin.getUserName() + '\"' + ", password =" + '\"' + admin.getPassword() + '\"'
                + ", name =" + '\"' + admin.getName() + '\"' + ",email =" + '\"' + admin.getEmail() + '\"' + ", mobileNumber=" + '\"' +
                admin.getMobileNumber() + '\"' + " where userID =" + admin.getId();
        return Helper(s);
    }

    @Override
    public boolean updateAdminImage(int adminID, String path) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Admin_table + " set imageUrl ='" + path + "' where userID =" + adminID;
        return Helper(s);
    }

    @Override
    public Seller getSellerByUsername(String username) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where username = \"" + username + "\" AND typeOfUser=" + SellerType)) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Seller getSellerByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where email = \"" + email + "\" AND typeOfUser=" + SellerType)) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seller> getSellerByName(String name) {
        ArrayList<Seller> list = new ArrayList<Seller>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Users_table + " where typeOfUser =" + SellerType + " and name like '%"  + name + "%'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralSellersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        s = "Select ownerID from " + DBInfo.MYSQL_DATABASE__Tags_table + " where tagType ='" + USER + "' and tagName like '%"  + name + "%'";

        List<Integer> ls = getIDsByTag(s);
        for (Integer l : ls) {
            Seller sel = getSellerByID(l);
            if (sel != null) {
                if (!list.contains(sel)) {
                    list.add(sel);
                }
            }
        }
        return list;
    }

    private List<Integer> getIDsByTag(String s) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
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
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where userID = " + ID + " AND" +
                " typeOfUser = 1")) {
            return getSellerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNewSeller(Seller seller) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Users_table + " (password, userName, name, typeOfUser, rating, voters, mobileNumber, imageUrl, email) values(" + "\"" + seller.getPassword() + "\"" + "," +
                "\"" + seller.getUserName() + "\"" + "," + "\"" + seller.getName() + "\"" + "," + SellerType + "," + seller.getRating()
                + "," + seller.getVoters() + "," + "\"" + seller.getMobileNumber() + "\"" + "," + "\"" + seller.getImage() + "\"" + "," + "\"" + seller.getEmail() + "\"" + ")";
        return Helper(s);
    }

    private void getSeveralSellersFromBase(List<Seller> list, PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(ObjectFactory.getNewSeller(rs.getString("username"), rs.getString("password"), rs.getString("email")
                        , rs.getString("name"), rs.getInt("rating"), rs.getInt("voters"), rs.getString("mobileNumber"), rs.getString("imageUrl"),
                        rs.getInt("userID"), rs.getBoolean("confirmed"), rs.getBoolean("banned")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        ArrayList<Seller> list = new ArrayList<Seller>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Users_table + " where typeOfUser =" + SellerType;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralSellersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public boolean updateSellerWithoutImage(Seller seller) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Users_table + " set userName =" + '\"' + seller.getUserName() + '\"' + ", password =" + '\"' + seller.getPassword() + '\"'
                + ", name =" + '\"' + seller.getName() + '\"' + ",email =" + '\"' + seller.getEmail() + '\"' + ", mobileNumber=" + '\"' +
                seller.getMobileNumber() + '\"' + ",rating =" + '\"' + seller.getRating() + '\"' + ",voters =" + '\"' + seller.getVoters() + '\"' + ", confirmed = " + seller.isConfirmed() + ", banned = " + seller.isBanned() + " where userID =" + seller.getID();
        return Helper(s);
    }

    @Override
    public boolean updateSellerImage(int sellerID, String path) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Users_table + " set imageUrl ='" + path + "' where userID =" + sellerID;
        return Helper(s);
    }

    @Override
    public boolean updateBuyerImage(int buyerID, String path) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Users_table + " set imageUrl ='" + path + "' where userID =" + buyerID;
        return Helper(s);
    }

    @Override
    public boolean updateItemImage(int itemID, String path) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Items_table + " set itemImageUrl  ='" + path + "' where itemID =" + itemID;
        return Helper(s);
    }

    @Override
    public boolean deleteSeller(int sellerID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where userID =" + sellerID;
        return Helper(s);
    }

    @Override
    public boolean deleteBuyer(int buyerID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where userID =" + buyerID;
        return Helper(s);
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where username = '" + userName + "' AND typeOfUser=" + BuyerType)) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Buyer getBuyerFromBase(PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {

                return ObjectFactory.getNewBuyer(rs.getString("username"), rs.getString("password"), rs.getString("email")
                        , rs.getString("name"), rs.getInt("rating"), rs.getInt("voters"), rs.getString("mobileNumber"),
                        rs.getString("imageUrl"), rs.getInt("userID"), rs.getBoolean("confirmed"), rs.getBoolean("banned"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer getBuyerByEmail(String email) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where email = \"" + email + "\" AND typeOfUser=" + BuyerType)) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer getBuyerByID(int ID) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM " + DBInfo.MYSQL_DATABASE_Users_table + " where userID = " + ID + " AND typeOfUser=" + BuyerType)) {
            return getBuyerFromBase(stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Buyer> getBuyerByName(String name) {
        ArrayList<Buyer> list = new ArrayList<>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Users_table + " where typeOfUser =" + BuyerType + " and name like '%" +  name + "%'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralBuyersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        s = "Select ownerID from " + DBInfo.MYSQL_DATABASE__Tags_table + " where tagType ='user'  and tagName like '%" +  name + "%'";
        List<Integer> ls = getIDsByTag(s);
        for (Integer l : ls) {
            Buyer b = getBuyerByID(l);
            if (b != null) {
                if (!list.contains(b)) {
                    list.add(b);
                }
            }
        }

        return list;
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Users_table + " (password, userName, name, typeOfUser, rating, voters, mobileNumber, imageUrl, email" +
                ") values(" + "\"" + buyer.getPassword() + "\"" + "," +
                "\"" + buyer.getUserName() + "\"" + "," + "\"" + buyer.getName() + "\"" + "," + BuyerType + "," + buyer.getRating()
                + "," + buyer.getVoters() + "," + "\"" + buyer.getMobileNumber() + "\"" + "," + "\"" + buyer.getImage() + "\"" + "," + "\"" + buyer.getEmail() + "\"" + ")";
        return Helper(s);
    }

    @Override
    public boolean updateBuyerWithoutImage(Buyer buyer) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Users_table + " set userName =" + '\"' + buyer.getUserName() + '\"' + ", password =" + '\"' + buyer.getPassword() + '\"'
                + ", name =" + '\"' + buyer.getName() + '\"' + ",email =" + '\"' + buyer.getEmail() + '\"' + ", mobileNumber=" + '\"' +
                buyer.getMobileNumber() + '\"' + ",rating =" + '\"' + buyer.getRating() + '\"' + ",voters =" + '\"' + buyer.getVoters() + '\"' + ", confirmed = " + buyer.isConfirmed() + ", banned = " + buyer.isBanned() + " where userID =" + buyer.getID();
        return Helper(s);
    }

    private void getSeveralBuyersFromBase(List<Buyer> list, PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(ObjectFactory.getNewBuyer(rs.getString("username"), rs.getString("password"), rs.getString("email")
                        , rs.getString("name"), rs.getInt("rating"), rs.getInt("voters"), rs.getString("mobileNumber"), rs.getString("imageUrl"),
                        rs.getInt("userID"), rs.getBoolean("confirmed"), rs.getBoolean("banned")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Buyer> getAllBuyer() {
        ArrayList<Buyer> list = new ArrayList<>();
        String s = "Select * from " + DBInfo.MYSQL_DATABASE_Users_table + " where typeOfUser =" + BuyerType;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            getSeveralBuyersFromBase(list, stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item addItem(Item it) {
        String s = "Insert into " + DBInfo.MYSQL_DATABASE_Items_table + " (itemName, itemImageUrl,categoryID,ownerID,price,rating, voters, description) values ('" + it.getName() + "','" + it.getImage() + "',"
                + it.getCategoryID() + "," + it.getOwnerID() + "," + it.getPrice() + "," + it.getRating() + "," + it.getVoters() + ", '" + it.getDescription() + "' )";
        Helper(s);
        Item item = null;
        s = "select * from " + DBInfo.MYSQL_DATABASE_Items_table + " where ownerID =" + it.getOwnerID() + " and ItemName ='" + it.getName() + "'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    item = ObjectFactory.getNewItem(rs.getString("ItemName"), rs.getInt("itemID"), rs.getInt("ownerID"),
                            rs.getString("itemImageUrl"), rs.getDouble("price"), rs.getInt("categoryID"), rs.getInt("rating"),
                            rs.getInt("voters"), rs.getString("description"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean deleteItem(int id) {
        String s = "Delete from " + DBInfo.MYSQL_DATABASE_Items_table + " where itemID =" + id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void getItems(List<Item> ls, String s) {
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getNewItem(rs.getString("ItemName"), rs.getInt("itemID"), rs.getInt("ownerID"),
                            rs.getString("itemImageUrl"), rs.getDouble("price"), rs.getInt("categoryID"), rs.getInt("rating"),
                            rs.getInt("voters"), rs.getString("description")));
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
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Items_table + " where itemID =" + id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewItem(rs.getString("ItemName"), rs.getInt("itemID"), rs.getInt("ownerID"),
                            rs.getString("itemImageUrl"), rs.getDouble("price"), rs.getInt("categoryID"), rs.getInt("rating"),
                            rs.getInt("voters"), rs.getString("description"));
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
    public List<Item> getItemsByCategoryId(int categoryId, int numberOfItems, int offset) {
        List<Item> ls = new ArrayList<Item>();
        String s = "select *, (rating / voters) as avg from " + DBInfo.MYSQL_DATABASE_Items_table + " where categoryID =" + categoryId + " order by avg desc" + " LIMIT " + numberOfItems + "" +
                " OFFSET " + offset;
        getItems(ls, s);
        return ls;
    }

    @Override
    public List<Item> getItemsBySeller(int sellerID) {
        List<Item> ls = new ArrayList<Item>();
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Items_table + " where ownerID =" + sellerID;
        getItems(ls, s);
        return ls;
    }

    @Override
    public List<Item> getItemsByName(String name) {
        List<Item> ls = new ArrayList<Item>();
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Items_table + " where ItemName like '%" +  name + "%'";
        getItems(ls, s);
        s = "Select ownerID from " + DBInfo.MYSQL_DATABASE__Tags_table + " where tagType ='" + ITEM + "'"+" and tagName like '%" + name + "%'";
        List<Integer> list = getIDsByTag(s);
        for (Integer aList : list) {
            Item it = getItemById(aList);
            if (it != null) {
                if (!ls.contains(it)) {
                    ls.add(it);
                }
            }
        }
        return ls;
    }

    private boolean Helper(String s) {
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> getTopItems(int numberOfItems, int offset) {
        List<Item> ls = new ArrayList<Item>();
        String s = "Select *, (rating / voters) as avg from " + DBInfo.MYSQL_DATABASE_Items_table + " order by avg desc limit " + numberOfItems + " offset " + offset;
        getItems(ls, s);
        return ls;
    }

    @Override
    public boolean updateItemWithoutImage(Item it) {
        String s = "update " + DBInfo.MYSQL_DATABASE_Items_table + " set itemName ='" + it.getName() +
                "',categoryID =" + it.getCategoryID() + ",rating =" + '\"' + it.getRating() + '\"' + ",voters =" + '\"' + it.getVoters() + '\"' + ", ownerID=" + it.getOwnerID() + ", price=" + it.getPrice() + ", description ='" + it.getDescription() + "' where itemID=" + it.getID();
        return Helper(s);
    }

    @Override
    public boolean deleteAllItemsForSeller(int indexOfSeller) {
        String s = "delete from " + DBInfo.MYSQL_DATABASE_Items_table + " where ownerID =" + indexOfSeller;
        return Helper(s);
    }

    @Override
    public boolean addCategory(Category cat) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Categories_table + " (categoryName) values ('" + cat.getName() + "')";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCategory(Category cat) {
        String s = "Update " + DBInfo.MYSQL_DATABASE_Categories_table + " set categoryName ='" + cat.getName() + "'" + " where categoryID =" + cat.getID();
        return Helper(s);
    }

    @Override
    public boolean deleteCategory(int id) {
        String s = "delete from " + DBInfo.MYSQL_DATABASE_Categories_table + " where categoryID= " + id + "";
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
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Categories_table;
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
        String s = "select * from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where commentID =" + id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewComment(rs.getInt("ownerID"), rs.getInt("writerID"),
                            rs.getInt("commentID"), rs.getString("comm"),
                            new java.util.Date(rs.getTimestamp("dateOfComment").getTime()));
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
        String s = "select * from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where ownerID =" + userID;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public List<Comment> getUserCommentsByOwner(int ownerID, int numberOfItems, int offset) {
        List<Comment> ls = new ArrayList<>();
        String s = "select * from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where ownerID =" + ownerID + " order by dateOfComment desc" + " LIMIT " + numberOfItems + "" +
                " OFFSET " + offset;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public List<Comment> getItemCommentsByOwner(int ownerID, int numberOfItems, int offset) {
        List<Comment> ls = new ArrayList<>();
        String s = "select * from " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " where ownerID =" + ownerID + " order by dateOfComment desc" + " LIMIT " + numberOfItems + "" +
                " OFFSET " + offset;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public boolean deleteUserComment(int id) {
        String s = "Delete from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where commentID =" + id;
        return Helper(s);
    }

    @Override
    public boolean addCommentToItem(Comment c) {
        String s = "Insert into " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " ( writerID, ownerID, comm  ) values ( " +
                c.getWriterID() + " ," + c.getOwnerID() + " ,'" + c.getComment() + "' )";
        return Helper(s);
    }

    @Override
    public Comment getItemCommentByID(int id) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " where commentID =" + id;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewComment(rs.getInt("ownerID"), rs.getInt("writerID"),
                            rs.getInt("commentID"), rs.getString("comm"),
                            new java.util.Date(rs.getTimestamp("dateOfComment").getTime()));
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
    public List<Comment> getItemCommentsByOwner(int itemID) {
        List<Comment> ls = new ArrayList<Comment>();
        String s = "select * from " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " where ownerID =" + itemID;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public boolean updateItemComment(Comment c) {
        String s = "Update " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " set comm ='" + c.getComment() + "', ownerID =" +
                c.getOwnerID() + ", writerID=" + c.getWriterID();
        return Helper(s);
    }

    @Override
    public boolean deleteItemComment(int id) {
        String s = "Delete from " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " where commentID =" + id;
        return Helper(s);
    }

    public boolean deleteAllCommentForItem(int itemID) {
        String s = " Delete from " + DBInfo.MYSQL_DATABASE_ItemsComments_table + " where ownerID =" + itemID;
        return Helper(s);
    }

    @Override
    public boolean deleteAllCommentForUser(int itemID) {
        String s = " Delete from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where ownerID =" + itemID;
        return Helper(s);
    }

    private void commentHelper(List<Comment> ls, String s) {
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getNewComment(rs.getInt("ownerID"), rs.getInt("writerID"),
                            rs.getInt("commentID"), rs.getString("comm"),
                            new java.util.Date(rs.getTimestamp("dateOfComment").getTime())));
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
        String s = "select * from " + DBInfo.MYSQL_DATABASE_UsersComments_table + " where writerID =" + userID;
        commentHelper(ls, s);
        return ls;
    }

    @Override
    public boolean updateUserComment(Comment c) {
        String s = "Update " + DBInfo.MYSQL_DATABASE_UsersComments_table + " set comm ='" + c.getComment() + "', ownerID =" +
                c.getOwnerID() + ", writerID=" + c.getWriterID() ;
        return Helper(s);
    }

    @Override
    public boolean addCommentToUser(Comment c) {
        String s = "Insert into " + DBInfo.MYSQL_DATABASE_UsersComments_table + " ( writerID, ownerID, comm  ) values ( " +
                c.getWriterID() + " ," + c.getOwnerID() + " ,'" + c.getComment() + "' )";
        return Helper(s);

    }

    public Category getCategory(String name) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Categories_table + " where categoryName = '" + name + "'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewCategory(rs.getInt("categoryID"), rs.getString("categoryName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category getCategory(int id) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Categories_table + " where categoryID = '" + id + "'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewCategory(rs.getInt("categoryID"), rs.getString("categoryName"));
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
        String s = "insert into " + DBInfo.MYSQL_DATABASE__Tags_table + " (tagName, tagType, ownerID) values( '" + tag + "' , '" + USER + "' ," + userID + ")";
        return Helper(s);
    }

    @Override
    public boolean addHashTagToItem(int itemID, String tag) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE__Tags_table + " (tagName, tagType, ownerID) values ('" + tag + "', '" + ITEM + "' ," + itemID + ")";
        return Helper(s);
    }

    @Override
    public boolean addWrittenRatingToBase(Rating r) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Rating_table + " (writerID, ownerID, rating, ownerType) values (" + r.getWriterID() + "," + r.getOwnerID() + "," + r.getValue() + ", '" + r.getOwnerType() + "' )";
        return Helper(s);
    }

    @Override
    public Rating getRating(int ownerID, int writerID, String ownerType) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Rating_table + " where ownerID =" + ownerID + " and writerID =" + writerID + " and ownerType ='" + ownerType + "'";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getNewRating(rs.getInt("ID"), rs.getInt("ownerID"), rs.getInt("writerID"), rs.getInt("rating"), rs.getString("ownerType"));
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
    public boolean deleteRating(int id) {
        String s = " Delete from " + DBInfo.MYSQL_DATABASE_Rating_table + " where ID =" + id;
        return Helper(s);
    }

    @Override
    public boolean updateRating(Rating r) {
        Rating rat = getRating(r.getOwnerID(), r.getWriterID(), r.getOwnerType());
        int value = r.getValue() - rat.getValue();
        String s = "update " + DBInfo.MYSQL_DATABASE_Rating_table + " set rating  = " + value + " where ID =" + r.getID();
        return Helper(s);
    }

    @Override
    public boolean deleteTransactionByItem(int itemID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Transaction_table + " where itemID =" + itemID;
        return Helper(s);
    }

    @Override
    public boolean deleteTransactionByID(String ID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Transaction_table + " where ID ='" + ID + "'";
        return Helper(s);
    }

    @Override
    public boolean deleteTransactionBySeller(int sellerID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Transaction_table + " where sellerID =" + sellerID;
        return Helper(s);
    }

    @Override
    public boolean deleteTransactionByBuyer(int buyerID) {
        String s = "DELETE FROM " + DBInfo.MYSQL_DATABASE_Transaction_table + " where buyerID =" + buyerID;
        return Helper(s);
    }

    @Override
    public List<Transaction> getTransactionByBuyer(int buyerID) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Transaction_table + " where buyerID = " + buyerID;
        return getTransactionByListHellper(s, true);
    }

    @Override
    public List<Transaction> getTransactionBySeller(int sellerID) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Transaction_table + " where sellerID = " + sellerID;
        return getTransactionByListHellper(s, true);
    }

    @Override
    public List<Transaction> getUnresolvedTransactionByBuyer(int buyerID) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Transaction_table + " where buyerID = " + buyerID;
        return getTransactionByListHellper(s, false);
    }

    @Override
    public List<Transaction> getUnresolvedTransactionBySeller(int sellerID) {
        String s = "select * from " + DBInfo.MYSQL_DATABASE_Transaction_table + " where sellerID = " + sellerID;
        return getTransactionByListHellper(s, false);
    }

    @Override
    public List<Statistic> getTopSoldItems(int sellerID) {
        List<Statistic>  ls= new ArrayList<Statistic>();
        String s = "select sum(amount) as overall, itemID, sellerID," +
                "  COUNT(DISTINCT CAST(date AS DATE)) as difDays FROM  "+ DBInfo.MYSQL_DATABASE_Transaction_table+ " where sellerID ="+sellerID+" and resolved = true GROUP BY itemID, itemID ORDER BY overall DESC limit 5";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getStatistic(rs.getInt("overall"), rs.getInt("itemID"), rs.getInt("sellerID"), rs.getInt("difDays")));
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
    public Statistic getStatistic(int itemID) {
        String s = "select sum(amount) as overall, itemID, sellerID," +
                "  COUNT(DISTINCT CAST(date AS DATE)) as difDays FROM  "+ DBInfo.MYSQL_DATABASE_Transaction_table+ " where itemID ="+itemID+" and resolved = true GROUP BY itemID, itemID ORDER BY overall ";
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return ObjectFactory.getStatistic(rs.getInt("overall"), rs.getInt("itemID"), rs.getInt("sellerID"), rs.getInt("difDays"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public List<Transaction> getTransactionByListHellper(String s, boolean resolved) {
        List<Transaction> ls = new ArrayList<Transaction>();
        s += " and resolved = " + resolved;
        transHelper(s, ls);
        return ls;
    }

    private void transHelper(String s, List<Transaction> ls) {
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ls.add(ObjectFactory.getTransaction(rs.getString("ID"), rs.getInt("amount"), rs.getInt("itemID"), rs.getInt("sellerID"), rs.getInt("buyerID")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addTransaction(Transaction tr) {
        String s = "insert into " + DBInfo.MYSQL_DATABASE_Transaction_table + " ( buyerID, sellerID, itemID, amount, id ) values (" + tr.getBuyerID() + " , "
                + tr.getSellerID() + " , " + tr.getItemID() + " , " + tr.getAmount() + ",'" + tr.getId() + "' )";
        return Helper(s);
    }

    @Override
    public boolean resolveTransaction(String id) {
        String s = "Update " + DBInfo.MYSQL_DATABASE_Transaction_table + " set resolved = true where ID = '" +id +"'" ;
        return Helper(s);
    }

    @Override
    public Transaction getTransaction(String id) {
        return getTransactionHelper(id);
    }


    private Transaction getTransactionHelper(String id) {
        String s = "SELECT * FROM " + DBInfo.MYSQL_DATABASE_Transaction_table + " where ID = '" +id+ "'" ;
        try (PreparedStatement stm = con.prepareStatement(s)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return (ObjectFactory.getTransaction(rs.getString("ID"), rs.getInt("amount"), rs.getInt("itemID"), rs.getInt("sellerID"), rs.getInt("buyerID")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
