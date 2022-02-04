package cricket;

class Match {
    Team teamZero , teamOne;

    public Match(int numOvers) {
        teamZero = new Team();
        teamOne = new Team();
        MatchController controller = new MatchController(teamZero,teamOne,numOvers);
        controller.control();
    }
}
