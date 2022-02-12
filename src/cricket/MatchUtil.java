package cricket;

import java.util.Scanner;

class MatchUtil {
    public static final int NUM_PLAYERS = 11;
    private static final Scanner s = new Scanner(System.in);

    public static double getDecimalInput() {
        final Scanner s = new Scanner(System.in);
        double d =  s.nextDouble();
        return d;
    }

    public static String getStringInput() {
        final Scanner s = new Scanner(System.in);
        String str =  s.nextLine();
        return str;
    }

    public static String getCurrentBallScore(String playerType,double batsmanRating,double bowlerRating) {
        String[] s = {"W", "0", "1", "2", "3", "4", "5", "6"};
        int possibleScoreIndex;
        if(playerType == "cricket.Batsman") {
            possibleScoreIndex = (int) Math.min(( batsmanRating * 7 ) / bowlerRating, 7.0);
        }
        else {
            possibleScoreIndex = (int) Math.min(7 / (batsmanRating * bowlerRating), 7.0);
        }
        return s[possibleScoreIndex];
    }
}
