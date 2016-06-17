package Managers;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Item;
import Objects.ObjectFactory;

import javax.servlet.http.Part;
import java.io.IOException;

public class ItemManager {
    DBConnection db;
    FileManager fm;

    public ItemManager() {
        db = DBFactory.getDBConnection();
        fm = ManagerFactory.getFileManager();
    }

    public boolean addNewItem(String userName, String name, int price, String desc, int ownerID, Part img, int categoryID) throws IOException {
        String imgUrl = fm.addItemImage(userName, name, img);
        Item item = ObjectFactory.getNewItem(name, ownerID, imgUrl, price, categoryID, 0, 0, desc);
        return true;
    }
    public void editItemImage(int itemID,String userName,String itemName,String curImage,Part image) throws IOException {
        String newUrl=fm.editItemImage(userName,itemName,curImage,image);
        db.updateItemImage(itemID,newUrl);
    }
    public void editItemPrice(Item it,int price){
        it.setPrice(price);
        db.updateItemWithoutImage(it);
    }
    public void editItemDesc(String desc, Item it){
        it.setDescription(desc);
        db.updateItemWithoutImage(it);
    }
    public void editItemName(String name, Item it){
        it.setName(name);
        db.updateItemWithoutImage(it);
    }
}
