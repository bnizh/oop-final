package Objects;

public class Category {
    String name;

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
