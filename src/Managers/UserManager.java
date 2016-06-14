package Managers;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Buyer;
import Objects.ObjectFactory;
import Objects.Seller;
import Objects.User;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    DBConnection db = DBFactory.getDBConnection();

    public Seller checkSellerLoginValidation(String userName, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashCreator.getHash(password);
        Seller seller = db.getSellerByEmail(userName);
        if (seller != null) {
            if (seller.getPassword().equals(hashedPassword)) return seller;
        }
        seller = db.getSellerByUsername(userName);
        if (seller != null) {
            if (seller.getPassword().equals(hashedPassword)) return seller;
        }
        return null;
    }
    public Buyer checkBuyerLoginValidation(String userName, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashCreator.getHash(password);
        Buyer buyer = db.getBuyerByUsername(userName);
        if (buyer != null) {
            if (buyer.getPassword().equals(hashedPassword)) return buyer;
        }
        buyer = db.getBuyerByEmail(userName);
        if (buyer != null) {
            if (buyer.getPassword().equals(hashedPassword)) return buyer;
        }

        return null;
    }

    public boolean checkUsernameVacancy(String userName) {
        Seller seller = db.getSellerByUsername(userName);
        Buyer buyer = db.getBuyerByUsername(userName);
        return seller == null && buyer == null;
    }
    private boolean sendEmailExistCheck(String email) throws MessagingException {
        ManagerFactory.getSendMail().sendEmail(email,"mkvdari adga agar dadgao");
        return true;
    }

    private ErrorStatus validateUser(User user) {
        if (!checkUsernameVacancy(user.getUserName())) return ErrorStatus.USED_USERNAME;
        else if (!rightEmailStructure(user.getEmail())) return ErrorStatus.INCORRECT_EMAIL_STRUCTURE;
        else if (!isEmailVacant(user.getEmail())) return ErrorStatus.USED_EMAIL;
        else if (!checkPassword(user.getPassword())) return ErrorStatus.WEAK_PASSWORD;
        return ErrorStatus.CORRECT;
    }

    public ErrorStatus createNewUser(String username, String password, String email,
                                     String name, String mobileNumber, Part filePart, String type) throws NoSuchAlgorithmException, IOException, ServletException, MessagingException {
        User user = getNewUser(username, password, email, name, mobileNumber, type);
        ErrorStatus result = validateUser(user);
        sendEmailExistCheck(email);
        if (result == ErrorStatus.CORRECT) {
            initUser(user, getImageUrl(username, filePart), hash(password));
            result = saveUser(user);
        }
        return result;
    }

    private ErrorStatus saveUser(User user) {
        if (user instanceof Buyer) {
            return saveBuyerToBase((Buyer) user) ? ErrorStatus.CORRECT : ErrorStatus.DATABASE_ERROR;
        } else {
            return saveSellerToBase((Seller) user) ? ErrorStatus.CORRECT : ErrorStatus.DATABASE_ERROR;
        }
    }

    private User getNewUser(String username, String password, String email, String name, String mobileNumber, String type) {
        if (type.equals("buyer")) {
            return ObjectFactory.getNewBuyer(username, password, email, name, 0, mobileNumber, 0, "");
        } else {
            return ObjectFactory.getNewSeller(username, password, email, name, 0, mobileNumber, 0, "");
        }
    }


    private void initUser(User user, String imageUrl, String password) {
        user.setImage(imageUrl);
        user.setPassword(password);
    }

    private String hash(String password) throws NoSuchAlgorithmException {
        return HashCreator.getHash(password);
    }

    private String getImageUrl(String username, Part filePart) throws IOException, ServletException {
        if(filePart.getSize()==0){
            return "D:\\\\prog\\\\Final-Project\\\\oop-final\\\\web\\\\defaultProfile.png";
        }
        FileManager fm = ManagerFactory.getFileManager();
        return fm.saveFile(username, filePart);
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

    public boolean isEmailVacant(String email) {
        Seller seller = db.getSellerByEmail(email);
        Buyer buyer = db.getBuyerByEmail(email);
        return seller == null && buyer == null;
    }

    private static boolean checkRegEx(String patternString, String text) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        return enoughLength(password, 6);
    }

    private static boolean enoughLength(String text, int length) {
        String patternString = "(?=.{" + length + ",}).*";
        return checkRegEx(patternString, text);
    }
}
