package Managers;

public class ManagerFactory {
    public static FileManager getFileManager() {
        return new FileManager();
    }

    public static UserManager getUserManager() {
        return new UserManager();
    }
    public static SendMail getSendMail(){
        return new SendMail();
    }
}
