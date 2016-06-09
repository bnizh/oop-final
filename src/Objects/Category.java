package Objects;

public class Category {
    String name;

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

    int ID;

    public Category(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
