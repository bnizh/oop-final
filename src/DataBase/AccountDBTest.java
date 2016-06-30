package DataBase;

import Objects.*;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 07.06.2016.
 */

public class AccountDBTest extends TestCase {
    public void testDeleteBuyer() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);DBFactory.getAccountDB(con);
            Buyer s = ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewBuyer(s);
            s = acc.getBuyerByUsername("username");
            acc.deleteBuyer(s.getID());
            s = acc.getBuyerByID(s.getID());
            assertTrue(s == null);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testAddItem() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            assertTrue(acc.addItem(ObjectFactory.getNewItem("item",sel.getID(),"image",5.6,c.getID(),0,0,"desc"))!=null);
            acc.deleteItem(acc.getItemsBySeller(sel.getID()).get(0).getID());
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteItem() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            acc.addItem(ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc"));
            assertTrue(acc.deleteItem(acc.getItemsBySeller(sel.getID()).get(0).getID()));
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetItemById() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            Item it =ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc");
            acc.addItem(it);
            it = (acc.getItemsBySeller(sel.getID()).get(0));
            Item it1 =acc.getItemById(it.getID());
            assertTrue(it.equals(it1));
            acc.deleteItem(acc.getItemsBySeller(sel.getID()).get(0).getID());
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetItemsBySeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            for (int i =1 ; i<=num; i++) {
                Item it =ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc");
                acc.addItem(it);
            }
            List<Item> ls  = acc.getItemsBySeller(sel.getID());
            assertTrue(ls.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteItem(ls.get(0).getID());
                ls.remove(0);
            }
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetItemsByName() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            for (int i =1 ; i<=num; i++) {
                Item it = ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc");
                acc.addItem(it);
            }
            List<Item> ls  = acc.getItemsByName("item");
            assertTrue(ls.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteItem(ls.get(0).getID());
                ls.remove(0);
            }
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetTopItems() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            List<Item> list = new ArrayList<Item>();
            for (int i =1 ; i<=num; i++) {
                Item it = ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),i,1,"desc");
                acc.addItem(it);
                list.add(it);
            }
            int k = 2;
            list = acc.getItemsBySeller(sel.getID());
            List<Item> ls = acc.getTopItems(k,0);
            for (int i = 0; i<k; i++){
                assertTrue(ls.get(i).equals(list.get(list.size()-1-i)));
            }
            for (int i =1 ; i<=num; i++) {
                acc.deleteItem(list.get(0).getID());
                list.remove(0);
            }
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testUpdateItem() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            Item it = ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc");
            acc.addItem(it);
            it = acc.getItemsBySeller(sel.getID()).get(0);
            it.setName("item1");
            it.setImage("image1");
            it.setPrice((double) 4);
            acc.updateItemWithoutImage(it);
            acc.updateItemImage(it.getID(), it.getImage());
            assertTrue(it.equals(acc.getItemById(it.getID())));
            acc.deleteItem(it.getID());
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteAllItemsForSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            c = acc.getCategory(c.getName());
            Seller sel = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(sel);
            sel = acc.getSellerByUsername(sel.getUserName());
            for (int i =1 ; i<=num; i++) {
                Item it =ObjectFactory.getNewItem("item",sel.getID(),"image",0,c.getID(),0,0,"desc");
                acc.addItem(it);
            }
            acc.deleteAllItemsForSeller(sel.getID());
            List<Item> ls  = acc.getItemsBySeller(sel.getID());
            assertTrue(ls.size() == 0);
            acc.deleteCategory(c.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testAddCategory() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            assertTrue(acc.addCategory(c));
            acc.deleteCategory(c.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteCategory() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Category c = ObjectFactory.getNewCategory("category");
            acc.addCategory(c);
            assertTrue(acc.deleteCategory(c.getID()));
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetAllCategories() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            for (int i =1 ; i<=num; i++) {
               Category c = ObjectFactory.getNewCategory("category"+i);
                acc.addCategory(c);
            }
            List<Category> list = acc.getAllCategories();
            assertTrue(list.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteCategory(list.get(0).getID());
                list.remove(0);
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetUserCommentByID() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            Seller sel = ObjectFactory.getNewSeller("username1", "password1", "email1", "name1", 0, "112", 0, "image1");
            acc.addNewSeller(s);
            acc.addNewSeller(sel);
            s = acc.getSellerByUsername("username");
            sel = acc.getSellerByUsername("username1");
            Comment c = ObjectFactory.getNewComment(s.getID(),sel.getID(),"bla");
            acc.addCommentToUser(c);
            c = acc.getUserCommentsByWriter(sel.getID()).get(0);
            assertTrue(c.equals(acc.getUserCommentByID(c.getCommentID())));
            acc.deleteUserComment(c.getCommentID());
            acc.deleteSeller(s.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetUserCommentsByOwner() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            Seller sel = ObjectFactory.getNewSeller("username1", "password1", "email1", "name1", 0, "112", 0, "image1");
            acc.addNewSeller(s);
            acc.addNewSeller(sel);
            s = acc.getSellerByUsername("username");
            sel = acc.getSellerByUsername("username1");
            Comment c = ObjectFactory.getNewComment(s.getID(),sel.getID(),"bla");
            acc.addCommentToUser(c);
            Comment c1 = acc.getUserCommentsByOwner(s.getID()).get(0);
            c.setCommentID(c1.getCommentID());
            c.setDateOfWrite(c1.getDateOfWrite());
            assertTrue(c.equals(c1));
            acc.deleteUserComment(c.getCommentID());
            acc.deleteSeller(s.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteUserComment() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            Comment c = ObjectFactory.getNewComment(s.getID(),s.getID(),"bla");
            acc.addCommentToUser(c);;
            c = acc.getUserCommentsByOwner(s.getID()).get(0);
            assertTrue(acc.deleteUserComment(c.getCommentID()));
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteAllCommentForUser() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            Seller sel = ObjectFactory.getNewSeller("username1", "password1", "email1", "name1", 0, "112", 0, "image1");
            acc.addNewSeller(s);
            acc.addNewSeller(sel);
            s = acc.getSellerByUsername("username");
            sel = acc.getSellerByUsername("username1");
            for(int i = 0; i < 10; i++){
                Comment c = ObjectFactory.getNewComment(s.getID(),sel.getID(),"bla"+i);
                acc.addCommentToUser(c);
            }
            assertTrue(acc.deleteAllCommentForUser(s.getID()));
            acc.deleteSeller(s.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetUserCommentsByWriter() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            Seller sel = ObjectFactory.getNewSeller("username1", "password1", "email1", "name1", 0, "112", 0, "image1");
            acc.addNewSeller(s);
            acc.addNewSeller(sel);
            s = acc.getSellerByUsername("username");
            sel = acc.getSellerByUsername("username1");
            Comment c = ObjectFactory.getNewComment(s.getID(),sel.getID(),"bla");
            acc.addCommentToUser(c);
            Comment c1 = acc.getUserCommentsByWriter(sel.getID()).get(0);
            c.setCommentID(c1.getCommentID());
            c.setDateOfWrite(c1.getDateOfWrite());
            assertTrue(c.equals(c1));
            acc.deleteUserComment(c.getCommentID());
            acc.deleteSeller(s.getID());
            acc.deleteSeller(sel.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testUpdateUserComment() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            Comment c = ObjectFactory.getNewComment(s.getID(),s.getID(),"bla");
            acc.addCommentToUser(c);
            c.setComment("CommentServlet");
            acc.updateUserComment(c);
            Comment c1 = acc.getUserCommentsByOwner(s.getID()).get(0);
            c.setCommentID(c1.getCommentID());
            c.setDateOfWrite(c1.getDateOfWrite());
            assertTrue(c.equals(c1));
            acc.deleteUserComment(c.getCommentID());
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testAddCommentToUser() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            Comment c = ObjectFactory.getNewComment(s.getID(),s.getID(),"bla");
            acc.addCommentToUser(c);
            c = acc.getUserCommentsByOwner(s.getID()).get(0);
            acc.deleteUserComment(c.getCommentID());
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetSellerByUsername() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s.setID(1);
            Seller sel = acc.getSellerByUsername("username");
            sel.setID(1);
            assertTrue(s.equals(sel));
            acc.deleteSeller(acc.getSellerByUsername("username").getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetSellerByEmail() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s.setID(1);
            Seller sel = acc.getSellerByEmail(s.getEmail());
            sel.setID(1);
            assertTrue(s.equals(sel));
            acc.deleteSeller(acc.getSellerByEmail("email").getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetSellerByName() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            for (int i =1 ; i<=num; i++) {
                Seller s = ObjectFactory.getNewSeller("username"+i, "password", "email"+i, "name", 0, "112", 0, "image");
                acc.addNewSeller(s);
            }
            List<Seller> list = acc.getSellerByName("name");
            assertTrue(list.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteSeller(list.get(0).getID());
                list.remove(0);
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetSellerByID() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            acc.addNewSeller(ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image"));
            Seller s = acc.getSellerByUsername("username");
            assertTrue(s.equals(acc.getSellerByID(s.getID())));
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testAddNewSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            acc.addNewSeller(ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image"));
            Seller s = acc.getSellerByUsername("username");
            assertTrue(s != null);
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetAllSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);

            int num = 10;
            for (int i =1 ; i<=num; i++) {
                Seller s = ObjectFactory.getNewSeller("username"+i, "password", "email"+i, "name", 0, "112", 0, "image");
                acc.addNewSeller(s);
            }
            List<Seller> list = acc.getAllSeller();
            assertTrue(list.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteSeller(list.get(0).getID());
                list.remove(0);
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testUpdateSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            s.setUserName("us");
            acc.updateSellerWithoutImage(s);
            assertEquals(s.getUserName(), acc.getSellerByEmail("email").getUserName());
            acc.deleteSeller(s.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetBuyerByUsername() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Buyer b = ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewBuyer(b);
            b.setID(1);
            Buyer buy = acc.getBuyerByUsername(b.getUserName());
            buy.setID(1);
            assertTrue(b.equals(buy));
            acc.deleteBuyer(acc.getBuyerByUsername("username").getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetBuyerByEmail() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Buyer b = ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewBuyer(b);
            b.setID(1);
            Buyer buy = acc.getBuyerByEmail(b.getEmail());
            buy.setID(1);
            assertTrue(b.equals(buy));
            acc.deleteBuyer(acc.getBuyerByEmail(b.getEmail()).getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetBuyerByID() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            acc.addNewBuyer(ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image"));
            Buyer b = acc.getBuyerByUsername("username");
            assertTrue(b.equals(acc.getBuyerByID(b.getID())));
            acc.deleteBuyer(b.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetBuyerByName() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            int num = 10;
            for (int i =1 ; i<=num; i++) {
                Buyer b = ObjectFactory.getNewBuyer("username"+i, "password", "email"+i, "name", 0, "112", 0, "image");
                acc.addNewBuyer(b);
            }
            List<Buyer> list = acc.getBuyerByName("name");
            assertTrue(list.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteBuyer(list.get(0).getID());
                list.remove(0);
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testAddNewBuyer() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            acc.addNewBuyer(ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image"));
            Buyer b = acc.getBuyerByUsername("username");
            assertTrue(b != null);
            acc.deleteBuyer(b.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testUpdateBuyer() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Buyer b = ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewBuyer(b);
            b = acc.getBuyerByUsername("username");
            b.setUserName("us");
            acc.updateBuyerWithoutImage(b);
            assertEquals(b.getUserName(), acc.getBuyerByEmail("email").getUserName());
            acc.deleteBuyer(b.getID());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetAllBuyer() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);

            int num = 10;
            for (int i =1 ; i<=num; i++) {
                Buyer b = ObjectFactory.getNewBuyer("username"+i, "password", "email"+i, "name", 0, "112", 0, "image");
                acc.addNewBuyer(b);
            }
            List<Buyer> list = acc.getAllBuyer();
            assertTrue(list.size() == num);
            for (int i =1 ; i<=num; i++) {
                acc.deleteBuyer(list.get(0).getID());
                list.remove(0);
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testDeleteSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = DBFactory.getAccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            acc.deleteSeller(s.getID());
            s = acc.getSellerByID(s.getID());
            assertTrue(s == null);
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }
}