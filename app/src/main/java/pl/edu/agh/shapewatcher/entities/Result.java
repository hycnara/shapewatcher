package pl.edu.agh.shapewatcher.entities;

import android.support.annotation.NonNull;

/**
 * Created by acer on 08.11.2017.
 */

public class Result implements Comparable {
    String userLogin;
    int userScore;

    public Result(){}

    public String getUserLogin() {
        return userLogin;
    }

    public int getUserScore() {
        return userScore;
    }

    public Result(String userLogin, int userScore){
        this.userLogin = userLogin;
        this.userScore = userScore;
    }

    @Override
    public int compareTo(Object r) {
        int compareResult = ((Result) r).getUserScore();
        return compareResult-this.userScore;
    }

}
