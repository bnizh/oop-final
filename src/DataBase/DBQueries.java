package DataBase;

import Accounts.Buyer;
import Accounts.Seller;
import Accounts.User;

import java.util.List;

public interface DBQueries {
    public Seller getSellerByUsername(String companyName);
    public Seller getSellerByEmail(String email);
    public List<Seller> getSellerByName(String name);
    public boolean addNewSeller(Seller seller);
    public List<Seller> getAllSeller();


    public User getBuyerByUsername(String userName);
    public User getBuyerByEmail(String email);
    public List<User> getBuyerByName(String name);
    public boolean addNewBuyer(Buyer buyer);
    public List<User> getAllBuyer();

}
