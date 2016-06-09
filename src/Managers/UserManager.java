package Managers;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Buyer;
import Objects.ObjectFactory;
import Objects.Seller;
import Objects.User;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    DBConnection db = DBFactory.getDBConnection();

    public boolean checkUserValidation(String userName, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashCreator.getHash(password);
        Seller seller = db.getSellerByEmail(userName);
        if (seller != null) {
            if (seller.getPassword().equals(hashedPassword)) return true;
        }
        seller = db.getSellerByUsername(userName);
        if (seller != null) {
            if (seller.getPassword().equals(hashedPassword)) return true;
        }
        Buyer buyer = db.getBuyerByUsername(userName);
        if (buyer != null) {
            if (buyer.getPassword().equals(hashedPassword)) return true;
        }
        buyer = db.getBuyerByEmail(userName);
        if (buyer != null) {
            if (buyer.getPassword().equals(hashedPassword)) return true;
        }

        return false;
    }

    public boolean checkUsernameVacancy(String userName) {
        Seller seller = db.getSellerByUsername(userName);
        System.out.println("zaza");
        Buyer buyer = db.getBuyerByUsername(userName);
        System.out.println("zaza");
        if (seller == null && buyer == null) return true;
        return false;
    }

    private int validateUser(){
        return 0;
    }
    public boolean createNewUser(String username, String password, String email,
                                 String name,  String mobileNumber,  String image, String type) {
        if (type.equals("buyer")) {
            Buyer buyer = ObjectFactory.getNewBuyer(username, password, email, name, 0, mobileNumber, 0, image);
            return saveBuyerToBase(buyer);
        } else {
            Seller seller=ObjectFactory.getNewSeller(username, password, email, name, 0, mobileNumber, 0, image);
            return saveSellerToBase(seller);
        }
    }

        private boolean saveSellerToBase(Seller seller) {
        return db.addNewSeller(seller);
    }

    private boolean saveBuyerToBase(Buyer buyer) {
        return db.addNewBuyer(buyer);

    }
    private boolean rightEmailStructure(String email) {
        String patternString = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
        return checkRegEx(patternString, email);
    }
    private static boolean checkRegEx(String patternString, String text) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }
}
