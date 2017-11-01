package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 20.10.2017.
 */

public class User {
    String userLogin;
    String userSex;
    String userDegree;
    int userAge;

    public User(){}

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserDegree() {
        return userDegree;
    }

    public int getUserAge() {
        return userAge;
    }

    public User(String userLogin, String userSex, String userDegree, int userAge) {

        this.userLogin = userLogin;
        this.userSex = userSex;
        this.userDegree = userDegree;
        this.userAge = userAge;
    }
}
