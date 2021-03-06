package Objects;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class ObjectFactory {
    public static Message getNewMessage(boolean read, int writerID, int receiverID, String messageContent, Date dateOfSend, int messageID) {
        return new Message(read, writerID, receiverID, messageContent, dateOfSend, messageID);
    }

    public static Message getNewMessage( int writerID, int receiverID, String messageContent) {
        return new Message( writerID, receiverID, messageContent);
    }

    //Returns new User
    /*seller with id*/
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, int voters, String mobileNumber, String image, int ID, boolean confirmed, boolean banned) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image, ID, confirmed, banned);
    }

    /* seller without ID*/
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, String mobileNumber, int voters, String image) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image);
    }

    /*buyer with id*/
    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, int voters, String mobileNumber,
                                    String image, int ID, boolean confirmed, boolean banned) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image, ID, confirmed, banned);
    }

    /*buyer without id*/
    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, String mobileNumber, int voters, String image) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image);
    }

    public static Admin getNewAdmin(String userName, String name, String email,
                                    String password, String imageURL, String mobileNumber, int typeOfAdmin) {
        return new Admin(userName, name, email, password, imageURL, mobileNumber, typeOfAdmin);
    }

    public static Admin getNewAdmin(int id, String userName, String name, String email,
                                    String password, String imageURL, String mobileNumber, int typeOfAdmin) {
        return new Admin(id, userName, name, email, password, imageURL, mobileNumber, typeOfAdmin);
    }

    /**
     * make new category with id
     */
    public static Category getNewCategory(int ID, String name) {
        return new Category(ID, name);
    }

    /**
     * make new category without id
     */
    public static Category getNewCategory(String name) {
        return new Category(name);
    }


    /**
     * make new item with id
     */
    public static Item getNewItem(String name, int ID, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc) {
        return new Item(name, ID, ownerID, image, price, categoryID, rating, voters, desc);
    }

    /**
     * make new item without id
     */
    public static Item getNewItem(String name, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc) {
        return new Item(name, ownerID, image, price, categoryID, rating, voters, desc);
    }

    /**
     * make new CommentServlet with id
     */
    public static Comment getNewComment(int ownerID, int writerID, int commentID, String comment, Date dateOfWrite) {
        return new Comment(ownerID, writerID, commentID, comment, dateOfWrite);

    }

    /**
     * make new item without id and date
     */
    public static Comment getNewComment(int ownerID, int writerID, String comment) {
        return new Comment(ownerID, writerID, comment);

    }

    public static Rating getNewRating(int ID, int ownerID, int writerID, int value, String ownerType) {
        return new Rating(ID, ownerID, writerID, value, ownerType);
    }

    public static Rating getNewRating(int ownerID, int writerID, int value, String ownerType) {
        return new Rating(ownerID, writerID, value, ownerType);
    }

    public static MyMap getMap() {
        return MyMap.getInstance();
    }

    public static unactivatedMap getUnactivatedMap() {
        return unactivatedMap.getInstance();
    }

    public static Transaction getTransaction(String id, int amount, int itemID, int sellerID, int buyerID) {
        return new Transaction(id, amount, itemID, sellerID, buyerID);
    }

    public static Transaction getTransaction(int sellerID, int buyerID, int itemID, int amount) {
        return new Transaction(sellerID, buyerID, itemID, amount);
    }

    public static MyRandom getRandom() {
        return MyRandom.getInstance();
    }

    public  static Statistic getStatistic (int overall, int itemID, int sellerID, int daysDiff){
        return new Statistic(overall, itemID, sellerID, daysDiff);
    }
}
