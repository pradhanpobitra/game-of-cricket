package cricket;

import java.util.HashMap;
import java.util.Locale;

class Batsman extends Player {
    private static final HashMap<String,Batsman> batsmanCareerStats = new HashMap<String,Batsman>();

    public static class BatsmanBuilder {
        private double playerRating;
        private String playerName;

        public BatsmanBuilder setPlayerRating() {
            double playerRating = MatchUtil.getDecimalInput();
            this.playerRating = playerRating;
            return this;
        }

        public BatsmanBuilder setPlayerName() {
            String playerName = MatchUtil.getStringInput();
            this.playerName = playerName.toUpperCase();
            return this;
        }

        public Batsman build() {
            return new Batsman(playerRating,playerName);
        }
    }

    private Batsman(double playerRating,String playerName) {
        super(playerRating,playerName);
    }

    @Override public void updatePlayerCareerStats() {
        String batsmanName = playerName.toUpperCase();
        if ( ! batsmanCareerStats.containsKey(batsmanName) ) {
            batsmanCareerStats.put(batsmanName,this);
            return;
        }
        Batsman batsman = batsmanCareerStats.get(batsmanName);
        batsman.noOfBallsPlayed += noOfBallsPlayed;
        batsman.noOfBoundaries += noOfBoundaries;
        batsman.noOfRunsScored += noOfRunsScored;
    }

    @Override public void showStatsOfPlayer() {
        System.out.println("Player " + this.playerName + " " + this.playerRating + " " + this.noOfRunsScored + " " + this.noOfBallsPlayed + " " + this.noOfBoundaries);
    }
}
