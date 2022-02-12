package cricket;

public class CricketGame {
    public static void main(String[] args) {
        System.out.println("Enter the number of overs in the match");
        int numOvers = (int) MatchUtil.getDecimalInput();

        Match newMatch = new Match(numOvers);
        newMatch.playAndShowResults();
    }
}