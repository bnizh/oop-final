package DataBase;

import Objects.Buyer;
import Objects.Seller;
import Objects.User;

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

    @Override
    public Seller getSellerByUsername(String username) {
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM "+ DBInfo.MYSQL_DATABASE_Users_table)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Seller getSellerByEmail(String email) {
        return null;
    }

    @Override
    public List<Seller> getSellerByName(String name) {
        return null;
    }

    @Override
    public boolean addNewSeller(Seller seller) {
    return false;
    }

    @Override
    public List getAllSeller() {
        return null;
    }

    @Override
    public User getBuyerByUsername(String userName) {
        return null;
    }

    @Override
    public User getBuyerByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getBuyerByName(String name) {
        return null;
    }

    @Override
    public boolean addNewBuyer(Buyer buyer) {
        return false;
    }

    @Override
    public List getAllBuyer() {
        return null;
    }
}
