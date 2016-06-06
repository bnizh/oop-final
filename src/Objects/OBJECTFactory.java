package Objects;

import java.util.Date;

public class OBJECTFactory {
    //Returns new User
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, int voters, String mobileNumber, String image) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image);
    }

    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, int voters, String mobileNumber, String image) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image);
    }
    public static Category getNewCategory(int ID, String name){
        return new Category(ID, name);
    }
    public static Item getNewItem(String name, int ID, int ownerID, String image, int price){
        return new Item(name, ID,ownerID,image,price);
    }
    public static Comment getNewComment(int ownerID, int writerID, int commentID, String comment, Date dateOfWrite){
        return new Comment(ownerID,writerID,commentID,comment,dateOfWrite);
    }
}
