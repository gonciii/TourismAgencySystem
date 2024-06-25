package entity;

public class User {
    private int id;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userFullName;

    public User() {

    }

    public User(int id, String userName, String userPassword, String userRole, String userFullName) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userFullName = userFullName;
    }

    public User(String text, String text1, String text2, String text3) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
