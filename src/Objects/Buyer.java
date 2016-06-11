package Objects;

public class Buyer extends User {


    public Buyer(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image, int buyerID) {
            super(username, password, email,name,rating,mobileNumber,voters,image, buyerID);
    }
    public Buyer(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image) {
        super(username, password, email,name,rating,mobileNumber,voters,image);
    }

    public boolean equals(Buyer u){
        return (this.getID() == u.getID() && this.getUserName().equals(u.getUserName()) && this.getEmail().equals(u.getEmail())
                && this.getImage().equals( u.getImage()) && this.getMobileNumber().equals(u.getMobileNumber())
                && this.getName().equals(u.getName()) && this.getPassword().equals(u.getPassword())
                && this.getRating() == u.getRating() && this.getVoters() == u.getVoters());

    }

}
