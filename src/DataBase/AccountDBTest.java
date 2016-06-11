package DataBase;

import Objects.Buyer;
import Objects.ObjectFactory;
import Objects.Seller;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 07.06.2016.
 */

public class AccountDBTest extends TestCase {
    public void testGetSellerByUsername() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);

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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);

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
            AccountDB acc = new AccountDB(con);
            Seller s = ObjectFactory.getNewSeller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s = acc.getSellerByUsername("username");
            s.setUserName("us");
            acc.updateSeller(s);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
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
            AccountDB acc = new AccountDB(con);
            Buyer b = ObjectFactory.getNewBuyer("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewBuyer(b);
            b = acc.getBuyerByUsername("username");
            b.setUserName("us");
            acc.updateBuyer(b);
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
            AccountDB acc = new AccountDB(con);

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
            AccountDB acc = new AccountDB(con);
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