package DataBase;

import Objects.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection implements  DBQueries{

    @Override
    public Seller getSellerByUsername(String companyName) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getDBQueries(con).getSellerByUsername(companyName);
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
            return  DBFactory.getDBQueries(con).getSellerByEmail(email);

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
            return  DBFactory.getDBQueries(con).getSellerByName(name);

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
            return  DBFactory.getDBQueries(con).getSellerByID(ID);

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
            return DBFactory.getDBQueries(con).addNewSeller(seller);

        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getDBQueries(con).getAllSeller();

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
    public boolean updateSeller(Seller seller) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getDBQueries(con).updateSeller(seller);

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
            return  DBFactory.getDBQueries(con).deleteSeller(sellerID);

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
            return  DBFactory.getDBQueries(con).deleteBuyer(buyerID);

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
            return  DBFactory.getDBQueries(con).getBuyerByUsername(userName);

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
            return  DBFactory.getDBQueries(con).getBuyerByEmail(email);

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
            return  DBFactory.getDBQueries(con).getBuyerByID(ID);

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
            return  DBFactory.getDBQueries(con).getBuyerByName(name);

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
            return  DBFactory.getDBQueries(con).addNewBuyer(buyer);

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
    public boolean updateBuyer(Buyer buyer) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getDBQueries(con).updateBuyer(buyer);

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
            return  DBFactory.getDBQueries(con).getAllBuyer();

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
            return  DBFactory.getDBQueries(con).addItem(it);

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
            return  DBFactory.getDBQueries(con).deleteItem(id);

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
            return  DBFactory.getDBQueries(con).getItemById(id);

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
            return  DBFactory.getDBQueries(con).getItemsBySeller(sellerID);

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
            return  DBFactory.getDBQueries(con).getItemsByName(name);

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
            return  DBFactory.getDBQueries(con).getTopItems(numberOfItems);

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
    public boolean updateItem(Item it) {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            return  DBFactory.getDBQueries(con).updateItem(it);

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
            return  DBFactory.getDBQueries(con).deleteAllItemsForSeller(indexOfSeller);

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
            return  DBFactory.getDBQueries(con).addCategory(cat);

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
            return DBFactory.getDBQueries(con).deleteCategory(cat);

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
            return  DBFactory.getDBQueries(con).getAllCategories();
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
            return  DBFactory.getDBQueries(con).addCommentToUser(c);
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
            return  DBFactory.getDBQueries(con).getUserCommentByID(id);
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
            return  DBFactory.getDBQueries(con).getUserCommentsByOwner(userID);
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
            return  DBFactory.getDBQueries(con).updateUserComment(c);
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
            return  DBFactory.getDBQueries(con).deleteUserComment(id);
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
            return  DBFactory.getDBQueries(con).deleteAllCommentForUser(userID);
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
            return  DBFactory.getDBQueries(con).getUserCommentsByWriter(userID);
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
            return  DBFactory.getDBQueries(con).getCategory(name);
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
