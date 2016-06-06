package DataBase;

import Objects.Buyer;
import Objects.Seller;

import java.util.List;

public interface DBQueries {
    public Seller getSellerByUsername(String companyName);

    public Seller getSellerByEmail(String email);

    public List<Seller> getSellerByName(String name);

    public Seller getSellerByID(int ID);

    public boolean addNewSeller(Seller seller);

    public List<Seller> getAllSeller();

    public boolean updateSeller(Seller seller);

    public boolean deleteSeller(int sellerID);

    public boolean deleteBuyer(int buyerID);

    public Buyer getBuyerByUsername(String userName);

    public Buyer getBuyerByEmail(String email);

    public Buyer getBuyerByID(int ID);

    public List<Buyer> getBuyerByName(String name);

    public boolean addNewBuyer(Buyer buyer);

    public boolean updateBuyer(Buyer buyer);

    public List<Buyer> getAllBuyer();
}