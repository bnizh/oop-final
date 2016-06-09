package Managers;

public class ManagerFactory {
    public static FileManager getFileManager(){
        return new FileManager();
    }
    public static UserManager getUserManager(){
        return new UserManager();
    }
}
