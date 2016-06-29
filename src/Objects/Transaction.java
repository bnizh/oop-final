package Objects;

import java.util.Random;

/**
 * Created by Boris on 28.06.2016.
 */
public class Transaction {
    private String id;
    private int sellerID;
    private int buyerID;
    private int itemID;
    private int amount;

    public Transaction(String id, int amount, int itemID, int sellerID, int buyerID) {
        this.id = id;
        this.amount = amount;
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
    }

    public Transaction(int sellerID, int buyerID, int itemID, int amount) {
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.itemID = itemID;
        this.amount = amount;
        this.id = ObjectFactory.getRandom().getRandomString();
    }

    public String getId() {
        return id;
    }

    public int getSellerID() {
        return sellerID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public int getAmount() {
        return amount;
    }

    public int getItemID() {
        return itemID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}