package cricket;

class MatchUtil {
    static final int NUM_PLAYERS = 11;

    static String getBallOutcome() {
        String[] strArray = {"0","1","2","3","4","5","6","W"};
        int randomIndex = (int) Math.floor(Math.random() * 8);
        return strArray[randomIndex];
    }
}
