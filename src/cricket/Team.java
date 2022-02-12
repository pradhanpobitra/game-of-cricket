package cricket;

class Team {
    private int teamScore;
    private int teamWicketsLost;
    private int currentBatsmanIndex;
    private int currentBowlerIndex;
    private final Bowler[] bowlers;
    private final Player[] player;
    private final String teamName;

    public static class TeamBuilder {
        private final Player[] player;
        private final Bowler[] bowlers;
        private String teamName;

        public TeamBuilder() {
            player = new Player[12];
            bowlers = new Bowler[5];
        }

        public TeamBuilder setTeamName() {
            System.out.println("Enter the name of team: ");
            teamName = MatchUtil.getStringInput();
            return this;
        }

        public TeamBuilder selectSixBatsmen() {
            for(int num = 1; num <= 6; num++) {
                System.out.println("Enter Batsman Number " + num + "'s name and rating: ");
                player[num] = new Batsman.BatsmanBuilder().setPlayerName().setPlayerRating().build();
            }
            return this;
        }

        public TeamBuilder selectFiveBowlers() {
            for(int num = 1; num <= 5; num++) {
                System.out.println("Enter Bowler Number " + num + "'s name and rating: ");
                player[num + 6] = new Bowler.BowlerBuilder().setPlayerName().setPlayerRating().build();
                bowlers[num - 1] = (Bowler) player[num + 6];
            }
            return this;
        }

        public Team build() {
            return new Team(player,bowlers,teamName);
        }
    }

    private Team(Player[] player,Bowler[] bowlers,String teamName) {
        this.player = player;
        this.bowlers = bowlers;
        this.teamName = teamName;
        this.currentBatsmanIndex = 1;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getNumOfWicketsLost() {
        return teamWicketsLost;
    }

    public String getTeamName() {
        return teamName;
    }

    public Player getCurrentBatsman() {
        return player[currentBatsmanIndex];
    }

    public Bowler getCurrentBowler() {
        int currBowlerIndex = currentBowlerIndex++;
        currentBowlerIndex %= 5;
        return bowlers[currBowlerIndex];
    }

    public void setNextBatsmanOnStrike() {
        currentBatsmanIndex++;
    }

    public void increamentTeamScore(int runs) {
        teamScore += runs;
    }

    public void increamentWicketsLost() {
        teamWicketsLost++;
    }

    public void updateTeamPlayersStats() {
        for(int num = 1; num <= MatchUtil.NUM_PLAYERS; num++) {
            player[num].updatePlayerCareerStats();
        }
    }

    public void showTeamScoreBoard() {
        System.out.println("Team - " + teamName + " stats :");
        System.out.println(teamScore + "/" + teamWicketsLost);
        for(int  num = 1; num <= MatchUtil.NUM_PLAYERS ; num++) {
            player[num].showStatsOfPlayer();
        }
    }
}
