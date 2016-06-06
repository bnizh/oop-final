package Objects;

import java.util.List;

public class Item {
    String name;
    int ID;
    int ownerID;
    String image;
    int price;
    List<Category> categories;

    public Item(String name, int ID, int ownerID, String image, int price) {
        this.name = name;
        this.ID = ID;
        this.ownerID = ownerID;
        this.image = image;
        this.price = price;
    }

    public boolean addCategory(int categoryID) {
        return false;
    }

    public boolean deleteCategory(int categoryID) {
        return false;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
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
}
