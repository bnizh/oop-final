package Objects;


import java.util.List;

public class Seller extends User {
    private List<String> gallery;

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public Seller(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image, int sellerID) {
        super(username, password, email,name,rating,mobileNumber,voters,image, sellerID);
    }
    public boolean equals(Seller u){
        return (this.getID() == u.getID() && this.getUserName().equals(u.getUserName()) && this.getEmail().equals(u.getEmail())
                && this.getImage().equals( u.getImage()) && this.getMobileNumber().equals(u.getMobileNumber())
                && this.getName().equals(u.getName()) && this.getPassword().equals(u.getPassword())
                && this.getRating() == u.getRating() && this.getVoters() == u.getVoters());

    }

}
