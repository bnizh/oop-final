package DataBase;

import Objects.*;

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



    public boolean addItem(Item it);

    public boolean deleteItem(int id );

    public Item getItemById(int id);

    public List<Item> getItemsBySeller(int sellerID);

    public List<Item> getItemsByName(String name);

    public List<Item> getTopItems (int numberOfItems);

    public boolean updateItem(Item it);

    public boolean deleteAllItemsForSeller(int idexOfSeller);

    public boolean addCategory(Category cat);

    public boolean deleteCategory(Category cat);

    public List<Category> getAllCategories();

    public boolean addCommentToUser(Comment c);

    public Comment getUserCommentByID(int id);

    public List<Comment> getUserCommentsByOwner(int userID);

    public boolean updateUserComment(Comment c);

    public boolean deleteUserComment(int id);

    public boolean deleteAllCommentForUser(int userID);

    public List<Comment> getUserCommentsByWriter(int userID);

    public Category getCategory(String name) ;
}