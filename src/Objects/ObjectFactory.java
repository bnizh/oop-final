package Objects;

import java.util.Date;

public class ObjectFactory {
    //Returns new User
    /*seller with id*/
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, int voters, String mobileNumber, String image , int ID) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image, ID);
    }
    /* seller without ID*/
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, String mobileNumber, int voters,  String image) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image);
    }
    /*buyer with id*/
    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, int voters, String mobileNumber, String image, int ID) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image,ID);
    }
    /*buyer without id*/
    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, String mobileNumber, int voters,  String image) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image);
    }

    /**
     * make new category with id
     */
    public static Category getNewCategory(int ID, String name){
        return new Category(ID, name);
    }

    /**
     * make new category without id
     */
    public static Category getNewCategory(String name){
        return new Category(name);
    }


    /**
     * make new item with id
     */
    public static Item getNewItem(String name, int ID, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc){
        return new Item(name, ID,ownerID,image,price, categoryID,rating, voters, desc);
    }
    /**
     * make new item without id
     */
    public static Item getNewItem(String name, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc){
        return new Item(name,ownerID,image,price, categoryID,rating, voters,desc);
    }

    /**
     * make new CommentServlet with id
     */
    public static Comment getNewComment(int ownerID, int writerID, int commentID, String comment, Date dateOfWrite){
        return new Comment(ownerID,writerID,commentID,comment,dateOfWrite);

    } /**
     * make new item without id and date
     */
    public static Comment getNewComment(int ownerID, int writerID,  String comment){
        return new Comment(ownerID,writerID,comment);

    }

    public static Rating getNewRating(int ID, int ownerID, int writerID, int value, String ownerType){
        return new Rating(ID,ownerID,writerID,value,ownerType);
    }

    public static Rating getNewRating(int ownerID, int writerID, int value, String ownerType){
        return new Rating(ownerID,writerID,value,ownerType);
    }
    public static MyMap getMap(){
        return MyMap.getInstance();
    }
}
