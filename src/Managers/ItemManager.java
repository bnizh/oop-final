package Managers;

import DataBase.DBConnection;
import DataBase.DBFactory;
import Objects.Item;
import Objects.ObjectFactory;

import javax.servlet.http.Part;
import java.io.IOException;

import static Managers.SiteConstants.DEFAULT_ITEM_IMAGE;

public class ItemManager {
    DBConnection db;
    FileManager fm;

    public ItemManager() {
        db = DBFactory.getDBConnection();
        fm = ManagerFactory.getFileManager();
    }

    public boolean addNewItem(String userName, String name, Double price, String desc, int ownerID, Part img, int categoryID) throws IOException {
        Item item = ObjectFactory.getNewItem(name, ownerID, "", price, categoryID, 0, 0, desc);
        System.out.println(item.getPrice()+"dabrunebamde");
        item = db.addItem(item);
        System.out.println(item.getPrice());
        String imgUrl = getImageUrl(img, userName, String.valueOf(item.getID()));
        db.updateItemImage(item.getID(),imgUrl);
        return true;
    }

    private String getImageUrl(Part img, String userName, String name) throws IOException {
        if (img.getSize()>0) {
            return fm.addItemImage(userName, name, img);
        }
        return DEFAULT_ITEM_IMAGE;
    }

    public void editItemImage(int itemID, String userName, String itemName, String curImage, Part image) throws IOException {
        String newUrl = fm.editItemImage(userName, itemName, curImage, image);
        db.updateItemImage(itemID, newUrl);
    }

    public void editItemPrice(Item it, Double price) {
        it.setPrice(price);
        db.updateItemWithoutImage(it);
    }

    public void editItemDesc(String desc, Item it) {
        it.setDescription(desc);
        db.updateItemWithoutImage(it);
    }

    public void editItemName(String name, Item it) {
        it.setName(name);
        db.updateItemWithoutImage(it);
    }
}
