package Accounts;

public abstract class User {
    private String name;
    private int rating;
    private int voters;
    private String mobileNumber;
    private String userName;
    private String image;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getVoters() {
        return voters;
    }

    public void setVoters(int voters) {
        this.voters = voters;
    }

    private String password;

    public void inceaseRating(int rating) {
        this.rating += rating;
        this.voters++;
    }
    public User (String username, String password, String email, String name, int rating, String mobileNumber, int voters, String image) {
        this.userName = username;
        this.password= password;
        this.email= email;
        this.name = name;
        this.rating = rating;
        this.mobileNumber = mobileNumber;
        this.voters = voters;
        this.image = image;
    }
}
