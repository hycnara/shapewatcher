package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 08.11.2017.
 */

public class Result {
    String userLogin;
    int userScore;

    public Result(){}

    public Result(String userLogin, int userScore){
        this.userLogin = userLogin;
        this.userScore = userScore;
    }

}
