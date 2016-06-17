package Objects;

public class Item {
    String name;
    int ID;
    int rating;
    int voters;
    int ownerID;
    String image;
    double price;
    String description;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVoters() {
        return voters;
    }

    public void setVoters(int voters) {
        this.voters = voters;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    int categoryID;

    public Item(String name, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc) {
        this.name = name;
        this.ownerID = ownerID;
        this.image = image;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
        this.rating = rating;
        this.voters = voters;
        this.description = desc;
    }

    public Item(String name, int ID, int ownerID, String image, double price, int categoryID, int rating, int voters, String desc) {
        this.name = name;
        this.ownerID = ownerID;
        this.image = image;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
        this.rating = rating;
        this.voters = voters;
        this.ID = ID;
        this.description = desc;
    }

    public void increaseRating(int r){
        this.setRating(this.getRating()+r);
        this.setVoters(this.getVoters()+1);

    }

    public boolean equals(Item it) {
       return (this.ID == it.getID() && this.categoryID == it.getCategoryID()&&
               this.image.equals(it.getImage())&&this.name.equals(it.getName())&&
               this.ownerID == it.getOwnerID()&&this.price == it.getPrice()&&
               this.rating == it.getRating()&&this.voters == it.getVoters());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
