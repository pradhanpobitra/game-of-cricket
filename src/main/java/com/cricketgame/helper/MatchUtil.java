package com.cricketgame.helper;

public class MatchUtil {
    public static final int NUM_PLAYERS = 11;

    public static String getCurrentBallScore(String playerType,double batsmanRating,double bowlerRating) {
        String[] s = {"W", "0", "1", "2", "3", "4", "5", "6"};
        int possibleScoreIndex;
        if(playerType == "com.cricketgame.pojo.Batsman") {
            possibleScoreIndex = (int) Math.min(( batsmanRating * Math.floor(Math.random() * 8) ) / bowlerRating, 7.0);
        }
        else {
            possibleScoreIndex = (int) Math.min( Math.floor(Math.random() * 8) / (batsmanRating * bowlerRating), 7.0 );
        }
        return s[possibleScoreIndex];
    }
}
