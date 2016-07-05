package Objects;

/**
 * Created by Boris on 05.07.2016.
 */
public class Statistic {
    public Statistic(int overall, int itemID, int sellerID, int difDays) {
        this.overall = overall;
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.difDays = difDays;
    }

    public int getOverall() {
        return overall;
    }

    public int getItemID() {
        return itemID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public int getDifDays() {
        return difDays;
    }

    int overall;
    int itemID;
    int sellerID;
    int difDays;
}
