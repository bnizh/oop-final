package DataBase;

import Objects.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBConnection implements DBQueries {

    @Override
    public Message getMessageById(int ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getMessageById(ID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Message> getMessageByWriterID(int ID, int messageType) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getMessageByWriterID(ID,messageType);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Message> getMessageByReceiverId(int ID, int messageType) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getMessageByReceiverId(ID,messageType);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }    }

    @Override
    public boolean addMessage(Message message, int messageType) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addMessage(message, messageType);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteMessage(int messageID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteMessage(messageID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Admin getAdminByID(int ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAdminByID(ID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Admin getAdminByUsername(String userName) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAdminByUsername(userName);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Admin getAdminByEmail(String email) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAdminByEmail(email);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addNewAdmin(Admin admin) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addNewAdmin(admin);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Admin> getAllAdmin() {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAllAdmin();
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteAdmin(int ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteAdmin(ID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateAdminWithoutImage(Admin admin) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateAdminWithoutImage(admin);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateAdminImage(int adminID, String path) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateAdminImage(adminID, path);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Seller getSellerByUsername(String companyName) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getSellerByUsername(companyName);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Seller getSellerByEmail(String email) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getSellerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Seller> getSellerByName(String name) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getSellerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Seller getSellerByID(int ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getSellerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addNewSeller(Seller seller) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addNewSeller(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAllSeller();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateSellerWithoutImage(Seller seller) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateSellerWithoutImage(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateSellerImage(int sellerID, String path) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateSellerImage(sellerID, path);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateBuyerImage(int buyerID, String path) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateBuyerImage(buyerID, path);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateItemImage(int itemID, String path) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateItemImage(itemID, path);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteSeller(int sellerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteSeller(sellerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteBuyer(int buyerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteBuyer(buyerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getBuyerByUsername(userName);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Buyer getBuyerByEmail(String email) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getBuyerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Buyer getBuyerByID(int ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getBuyerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Buyer> getBuyerByName(String name) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getBuyerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addNewBuyer(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateBuyerWithoutImage(Buyer buyer) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateBuyerWithoutImage(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Buyer> getAllBuyer() {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAllBuyer();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Item addItem(Item it) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addItem(it);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteItem(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteItem(id);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Item getItemById(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemById(id);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getItemsBySeller(int sellerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemsBySeller(sellerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getItemsByName(String name) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemsByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getItemsByCategoryId(int ID, int numberOfItems, int offset) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemsByCategoryId(ID, numberOfItems, offset);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getTopItems(int numberOfItems, int offset) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getTopItems(numberOfItems, offset);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateItemWithoutImage(Item it) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateItemWithoutImage(it);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteAllItemsForSeller(int indexOfSeller) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteAllItemsForSeller(indexOfSeller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addCategory(Category cat) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addCategory(cat);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateCategory(Category cat) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateCategory(cat);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteCategory(id);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Category> getAllCategories() {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getAllCategories();
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addCommentToUser(Comment c) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addCommentToUser(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Comment getUserCommentByID(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUserCommentByID(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Comment> getUserCommentsByOwner(int userID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUserCommentsByOwner(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Comment> getItemCommentsByOwner(int userID, int offset, int numberOfItem) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemCommentsByOwner(userID, numberOfItem, offset);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Comment> getUserCommentsByOwner(int userID, int offset, int numberOfItem) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUserCommentsByOwner(userID, numberOfItem, offset);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateUserComment(Comment c) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateUserComment(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteUserComment(int id) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteUserComment(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addCommentToItem(Comment c) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addCommentToItem(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Comment getItemCommentByID(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemCommentByID(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Comment> getItemCommentsByOwner(int itemID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getItemCommentsByOwner(itemID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateItemComment(Comment c) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateItemComment(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteItemComment(int id) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteItemComment(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteAllCommentForItem(int itemID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteAllCommentForItem(itemID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteAllCommentForUser(int userID) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteAllCommentForUser(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Comment> getUserCommentsByWriter(int userID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUserCommentsByWriter(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Category getCategory(String name) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getCategory(name);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Category getCategory(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getCategory(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addHashTagToUser(int userID, String tag) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addHashTagToUser(userID, tag);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addHashTagToItem(int itemID, String tag) {

        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addHashTagToItem(itemID, tag);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addWrittenRatingToBase(Rating r) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addWrittenRatingToBase(r);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Rating getRating(int ownerID, int writerID, String ownerType) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getRating(ownerID, writerID, ownerType);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteRating(int id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteRating(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean updateRating(Rating r) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).updateRating(r);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addTransaction(Transaction tr) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).addTransaction(tr);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean resolveTransaction(String id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).resolveTransaction(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Transaction getTransaction(String id) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getTransaction(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteTransactionByItem(int itemID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteTransactionByItem(itemID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteTransactionByID(String ID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteTransactionByID(ID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteTransactionBySeller(int sellerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteTransactionBySeller(sellerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteTransactionByBuyer(int buyerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteTransactionByBuyer(buyerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Transaction> getTransactionByBuyer(int buyerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getTransactionByBuyer(buyerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Transaction> getTransactionBySeller(int sellerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getTransactionBySeller(sellerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Transaction> getUnresolvedTransactionByBuyer(int buyerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUnresolvedTransactionByBuyer(buyerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Transaction> getUnresolvedTransactionBySeller(int sellerID) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).getUnresolvedTransactionBySeller(sellerID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
