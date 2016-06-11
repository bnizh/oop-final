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
    public static Category getNewCategory(int ID, String name){
        return new Category(ID, name);
    }

    public static Item getNewItem(String name, int ID, int ownerID, String image, int price, int categoryID, int rating, int voters){
        return new Item(name, ID,ownerID,image,price, categoryID,rating, voters);
    }
    public static Item getNewItemWithout(String name, int ownerID, String image, int price, int categoryID, int rating, int voters){
        return new Item(name,ownerID,image,price, categoryID,rating, voters);
    }

    public static Comment getNewComment(int ownerID, int writerID, int commentID, String comment, Date dateOfWrite){
        return new Comment(ownerID,writerID,commentID,comment,dateOfWrite);

    }


}
