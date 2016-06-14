package Objects;

import java.util.List;

public class Item {
    String name;
    int ID;
    int rating;
    int voters;
    int ownerID;
    String image;
    int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int categoryID;

    public Item(String name, int ownerID, String image, int price, int categoryID, int rating, int voters) {
        this.name = name;
        this.ownerID = ownerID;
        this.image = image;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
        this.rating = rating;
        this.voters = voters;
    }

    public Item(String name, int ID, int ownerID, String image, int price, int categoryID, int rating, int voters) {
        this.name = name;
        this.ownerID = ownerID;
        this.image = image;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
        this.rating = rating;
        this.voters = voters;
        this.ID = ID;
    }



    public boolean equals(Item it) {
       return (this.ID == it.getID() && this.categoryID == it.getCategoryID()&&
               this.image.equals(it.getImage())&&this.name.equals(it.getName())&&
               this.ownerID == it.getOwnerID()&&this.price == it.getPrice()&&
               this.rating == it.getRating()&&this.voters == it.getVoters());
    }
}
