package cricket;

import java.util.HashMap;

class Bowler extends Player{
    private static final HashMap<String,Bowler> bowlerCareerStats = new HashMap<String,Bowler>();
    private int noOfOversBalled;
    private int noOfMaidenOversBalled;
    private int noOfWicketsTaken;
    private int runsGiven;

    public static class BowlerBuilder {
        private String playerName;
        private double playerRating;

        public BowlerBuilder setPlayerName() {
            String playerName = MatchUtil.getStringInput();
            this.playerName = playerName.toUpperCase();
            return this;
        }

        public BowlerBuilder setPlayerRating() {
            double playerRating = MatchUtil.getDecimalInput();
            this.playerRating = playerRating;
            return this;
        }

        public Bowler build() {
            return new Bowler(playerRating,playerName);
        }
    }

    private Bowler(double playerRating,String playerName) {
        super(playerRating,playerName);
    }

    public void increamentNumWicketsTaken() {
        noOfWicketsTaken++;
    }

    public void increamentNumOversBalled() {
        noOfOversBalled++;
    }

    public void increamentRunsGiven(int runs) {
        runsGiven += runs;
    }

    public void increamentMaidenOversBowled() {
        noOfMaidenOversBalled++;
    }

    @Override public void updatePlayerCareerStats() {
        String bowlerName = playerName.toUpperCase();
        if ( ! bowlerCareerStats.containsKey(bowlerName) ) {
            bowlerCareerStats.put(bowlerName,this);
            return;
        }
        Bowler bowler = bowlerCareerStats.get(bowlerName);
        bowler.noOfOversBalled += noOfOversBalled;
        bowler.noOfMaidenOversBalled += noOfMaidenOversBalled;
        bowler.noOfWicketsTaken += noOfWicketsTaken;
        bowler.runsGiven += runsGiven;
        bowler.noOfBallsPlayed += noOfBallsPlayed;
        bowler.noOfBoundaries += noOfBoundaries;
        bowler.noOfRunsScored += noOfRunsScored;
    }

    @Override public void showStatsOfPlayer() {
        System.out.println(this.noOfOversBalled + " " + this.noOfWicketsTaken + " " + this.runsGiven + " " + this.noOfMaidenOversBalled);
        System.out.println("Player " + this.playerName + " " + this.playerRating + " " + this.noOfRunsScored + " " + this.noOfBallsPlayed + " " + this.noOfBoundaries);
    }
}
