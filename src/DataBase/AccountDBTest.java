package DataBase;

import Objects.Seller;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Boris on 07.06.2016.
 */

public class AccountDBTest extends TestCase {
    public void testGetSellerByUsername() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = new AccountDB(con);
            Seller s = new Seller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s.setID(1);
            Seller sel = acc.getSellerByUsername("username");
            sel.setID(1);
            assertTrue(s.equals(sel));
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetSellerByEmail() throws Exception {

    }

    public void testGetSellerByName() throws Exception {

    }

    public void testGetSellerByID() throws Exception {

    }

    public void testAddNewSeller() throws Exception {

    }

    public void testGetAllSeller() throws Exception {

    }

    public void testUpdateSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = new AccountDB(con);
            Seller s = new Seller("username", "password", "email", "name", 0, "112", 0, "image");
            acc.addNewSeller(s);
            s.setUserName("us");
            s.setID(1);
            acc.updateSeller(s);
            assertEquals(s.getUserName(), acc.getSellerByID(1).getUserName());
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        } finally {
            if (con != null)
                con.close();
        }
    }

    public void testGetBuyerByUsername() throws Exception {

    }

    public void testGetBuyerByEmail() throws Exception {

    }

    public void testGetBuyerByID() throws Exception {

    }

    public void testGetBuyerByName() throws Exception {

    }

    public void testAddNewBuyer() throws Exception {

    }

    public void testUpdateBuyer() throws Exception {

    }

    public void testGetAllBuyer() throws Exception {

    }

    public void testDeleteSeller() throws Exception {
        Connection con = null;
        try {
            con = DBFactory.getConnectionPool().getEventDataSource().getConnection();
            AccountDB acc = new AccountDB(con);
            Seller s = new Seller("username", "password", "email", "name", 0, "112", 0, "image");
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