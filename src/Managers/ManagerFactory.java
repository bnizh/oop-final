package Managers;

public class ManagerFactory {
    public static FileManager getFileManager() {
        return new FileManager();
    }

    public static UserManager getUserManager() {
        return new UserManager();
    }

    public static ItemManager getItemManager() {
        return new ItemManager();
    }

    public static SendMail getSendMail() {
        return new SendMail();
    }
}
