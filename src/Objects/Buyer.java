package Objects;

public class Buyer extends User {


    public Buyer(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image, int buyerID) {
            super(username, password, email,name,rating,mobileNumber,voters,image, buyerID);
    }
    public Buyer(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image) {
        super(username, password, email,name,rating,mobileNumber,voters,image);
    }

    @Override
    public boolean equals(Object u) {
        if(u.getClass()!= this.getClass())
            return false;
        return (this.getID() ==((Buyer) u).getID() && this.getUserName().equals(((Buyer) u).getUserName()) && this.getEmail().equals(((Buyer) u).getEmail())
                && this.getImage().equals( ((Buyer) u).getImage()) && this.getMobileNumber().equals(((Buyer) u).getMobileNumber())
                && this.getName().equals(((Buyer) u).getName()) && this.getPassword().equals(((Buyer) u).getPassword())
                && this.getRating() == ((Buyer) u).getRating() && this.getVoters() == ((Buyer) u).getVoters());

    }


    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.getUserName().length(); i++) {
            hash = hash*31 + this.getUserName().charAt(i);
        }
        return hash;

    }

}
