package DataBase;

import Objects.*;

import java.util.List;

public interface DBQueries {
    public Message getMessageById(int ID);

    public List<Message> getMessageByWriterID(int ID,int messageType);

    public List<Message> getMessageByReceiverId(int ID,int messageType);
    public List<Message> getAllAdminMessage();

    public boolean addMessage(Message message,int messageType);

    public boolean deleteMessage(int messageID);

    public Admin getAdminByID(int ID);

    public Admin getAdminByUsername(String userName);

    public Admin getAdminByEmail(String email);

    public boolean addNewAdmin(Admin admin);

    public List<Admin> getAllAdmin();

    public boolean deleteAdmin(int ID);

    public boolean updateAdminWithoutImage(Admin admin);

    public boolean updateAdminImage(int adminID, String path);

    public Seller getSellerByUsername(String companyName);

    public Seller getSellerByEmail(String email);

    public List<Seller> getSellerByName(String name);

    public Seller getSellerByID(int ID);

    public boolean addNewSeller(Seller seller);

    public List<Seller> getAllSeller();

    public boolean updateSellerWithoutImage(Seller seller);

    public boolean updateSellerImage(int sellerID, String path);

    public boolean updateBuyerImage(int buyerID, String path);

    public boolean updateItemImage(int itemID, String path);

    public boolean deleteSeller(int sellerID);

    public boolean deleteBuyer(int buyerID);

    public Buyer getBuyerByUsername(String userName);

    public Buyer getBuyerByEmail(String email);

    public Buyer getBuyerByID(int ID);

    public List<Buyer> getBuyerByName(String name);

    public boolean addNewBuyer(Buyer buyer);

    public boolean updateBuyerWithoutImage(Buyer buyer);

    public List<Buyer> getAllBuyer();

    public Item addItem(Item it);

    public boolean deleteItem(int id);

    public Item getItemById(int id);

    public List<Item> getItemsBySeller(int sellerID);

    public List<Item> getItemsByName(String name);

    public List<Item> getItemsByCategoryId(int ID, int numberOfItems, int offset);

    public List<Item> getTopItems(int numberOfItems, int offset);

    public boolean updateItemWithoutImage(Item it);

    public boolean deleteAllItemsForSeller(int idexOfSeller);

    public boolean addCategory(Category cat);

    public boolean updateCategory(Category cat);

    public boolean deleteCategory(int id);

    public List<Category> getAllCategories();

    public boolean addCommentToUser(Comment c);

    public Comment getUserCommentByID(int id);

    public List<Comment> getUserCommentsByOwner(int userID);

    public boolean updateUserComment(Comment c);

    public boolean deleteUserComment(int id);

    public boolean addCommentToItem(Comment c);

    public Comment getItemCommentByID(int id);

    public List<Comment> getItemCommentsByOwner(int userID);

    public boolean updateItemComment(Comment c);

    public boolean deleteItemComment(int id);

    /**
     * delete all CommentServlet for user(owner) get's ownerID (int)
     */
    public List<Comment> getUserCommentsByOwner(int categoryId, int numberOfItems, int offset);

    public List<Comment> getItemCommentsByOwner(int categoryId, int numberOfItems, int offset);

    public boolean deleteAllCommentForItem(int itemID);

    public boolean deleteAllCommentForUser(int userID);

    public List<Comment> getUserCommentsByWriter(int userID);

    public Category getCategory(String name);

    public boolean addHashTagToUser(int userID, String tag);

    public boolean addHashTagToItem(int itemID, String tag);

    public boolean addWrittenRatingToBase(Rating r);

    public Rating getRating(int ownerID, int writerID, String ownerType);

    public boolean deleteRating(int id);

    public boolean updateRating(Rating r);

    public boolean addTransaction(Transaction tr);

    public boolean resolveTransaction(String id);

    public Transaction getTransaction(String id);

    public boolean deleteTransactionByItem(int itemID);

    public boolean deleteTransactionByID(String ID);

    public boolean deleteTransactionBySeller(int sellerID);

    public boolean deleteTransactionByBuyer(int buyerID);

    public List<Transaction> getTransactionByBuyer(int buyerID);

    public List<Transaction> getTransactionBySeller(int sellerID);

    public List<Transaction> getUnresolvedTransactionByBuyer(int buyerID);

    public List<Transaction> getUnresolvedTransactionBySeller(int sellerID);

    public List<Statistic> getTopSoldItems(int sellerID);
    public Statistic getStatistic(int itemID);

}