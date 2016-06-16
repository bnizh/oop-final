package DataBase;

import Objects.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBConnection implements DBQueries{

    @Override
    public Seller getSellerByUsername(String companyName) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getAccountDB(con).getSellerByUsername(companyName);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getSellerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getSellerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getSellerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getAllSeller();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateSellerWithoutImage(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateSellerImage(sellerID, path);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateBuyerImage(buyerID, path);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateItemImage(itemID, path);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteSeller(sellerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteBuyer(buyerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getBuyerByUsername(userName);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getBuyerByEmail(email);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getBuyerByID(ID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getBuyerByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).addNewBuyer(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateBuyerWithoutImage(buyer);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getAllBuyer();

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean addItem(Item it) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getAccountDB(con).addItem(it);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteItem(id);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getItemById(id);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getItemsBySeller(sellerID);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getItemsByName(name);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Item> getTopItems(int numberOfItems) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getAccountDB(con).getTopItems(numberOfItems);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateItemWithoutImage(it);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteAllItemsForSeller(indexOfSeller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).addCategory(cat);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteCategory(Category cat) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return DBFactory.getAccountDB(con).deleteCategory(cat);

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
            return  DBFactory.getAccountDB(con).getAllCategories();
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).addCommentToUser(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getUserCommentByID(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getUserCommentsByOwner(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).updateUserComment(c);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteUserComment(id);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).deleteAllCommentForUser(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getUserCommentsByWriter(userID);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).getCategory(name);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).addHashTagToUser(userID,tag);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
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
            return  DBFactory.getAccountDB(con).addHashTagToItem(itemID, tag);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
