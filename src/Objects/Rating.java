package Objects;

/**
 * Created by Boris on 17.06.2016.
 */
public class Rating
{
    int ID;
    int ownerID;
    int writerID;
    int value;
    String ownerType;

    public Rating(int ownerID, int writerID, int value, String ownerType) {
        this.ownerID = ownerID;
        this.writerID = writerID;
        this.value = value;
        this.ownerType = ownerType;
    }

    public Rating(int ID, int ownerID, int writerID, int value, String ownerType) {
        this.ID = ID;
        this.ownerID = ownerID;
        this.writerID = writerID;
        this.value = value;
        this.ownerType = ownerType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWriterID() {
        return writerID;
    }

    public void setWriterID(int writerID) {
        this.writerID = writerID;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
}
