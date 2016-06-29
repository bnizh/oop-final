package Objects;


import Managers.HashCreator;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Seller extends User {

    public Seller(String username, String password, String email, String name, int rating, String mobileNumber, int voters,
                  String image, int sellerID, boolean confirmed, boolean banned) {
        super(username, password, email, name, rating, mobileNumber, voters, image, sellerID, confirmed, banned);
    }

    public Seller(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image) {
        super(username, password, email, name, rating, mobileNumber, voters, image);
    }

    @Override
    public boolean equals(Object u) {
        if (u.getClass() != this.getClass())
            return false;
        return (this.getID() == ((Seller) u).getID() && this.getUserName().equals(((Seller) u).getUserName()) && this.getEmail().equals(((Seller) u).getEmail())
                && this.getImage().equals(((Seller) u).getImage()) && this.getMobileNumber().equals(((Seller) u).getMobileNumber())
                && this.getName().equals(((Seller) u).getName()) && this.getPassword().equals(((Seller) u).getPassword())
                && this.getRating() == ((Seller) u).getRating() && this.getVoters() == ((Seller) u).getVoters());

    }


    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.getUserName().length(); i++) {
            hash = hash * 31 + this.getUserName().charAt(i);
        }
        return hash;

    }
}
