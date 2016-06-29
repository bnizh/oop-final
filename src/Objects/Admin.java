package Objects;

public class Admin {
    private String UserName;
    private String name;
    private String email;
    private int id;
    private String imageURL;
    private int typeOfAdmin;

    private String mobileNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeOfAdmin() {
        return typeOfAdmin;
    }

    public void setTypeOfAdmin(int typeOfAdmin) {
        this.typeOfAdmin = typeOfAdmin;
    }

    public Admin(String userName, String name, String email, String password, String imageURL, String mobileNumber, int typeOfAdmin) {
        UserName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageURL = imageURL;
        this.mobileNumber = mobileNumber;
        this.typeOfAdmin = typeOfAdmin;
    }

    public Admin(int id, String userName, String name, String email, String password, String imageURL, String mobileNumber, int typeOfAdmin) {
        UserName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageURL = imageURL;
        this.mobileNumber = mobileNumber;
        this.id = id;
        this.typeOfAdmin = typeOfAdmin;
    }

    private String password;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


}
