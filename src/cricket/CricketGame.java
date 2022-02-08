package cricket;

import java.util.Scanner;

public class CricketGame {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of overs in the match");

        int numOvers = sc.nextInt();
        Match newMatch = new Match(numOvers);
        newMatch.playAndShowResults();
    }
}
