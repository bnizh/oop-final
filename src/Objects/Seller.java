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

    public Seller(String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image) {
        super(username, password, email,name,rating,mobileNumber,voters,image);
    }


}
