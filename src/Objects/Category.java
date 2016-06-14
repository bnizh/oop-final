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

    public Category(String name) {
        this.name = name;
    }

    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }


    public boolean equals(Category c) {
       return (this.name.equals(c.getName()));
    }
}
