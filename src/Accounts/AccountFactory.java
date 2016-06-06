package Accounts;

public class AccountFactory {
    //Returns new User
    public static Seller getNewSeller(String username, String password, String email,
                                      String name, int rating, int voters, String mobileNumber, String image) {
        return new Seller(username, password, email, name, rating, mobileNumber, voters, image);
    }

    public static Buyer getNewBuyer(String username, String password, String email,
                                    String name, int rating, int voters, String mobileNumber, String image) {
        return new Buyer(username, password, email, name, rating, mobileNumber, voters, image);
    }
}
