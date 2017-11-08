package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 08.11.2017.
 */

public class Round {
    String userLogin;
    int round;
    int score;
    String biggerFigureColor;

    public Round(){}

    public Round(String userLogin, int round, int score, String biggerFigureColor) {
        this.userLogin = userLogin;
        this.round = round;
        this.score = score;
        this.biggerFigureColor = biggerFigureColor;
    }
}
