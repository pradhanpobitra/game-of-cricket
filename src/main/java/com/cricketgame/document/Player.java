package com.cricketgame.document;

import com.cricketgame.repository.HashmapsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import java.util.ArrayList;
import java.util.Arrays;

@Document(indexName = "player")
@Setting(settingPath = "static/es-settings.json")
public abstract class Player {

    @Autowired
    private HashmapsRepo hashmapsRepo;

    @Id
    @Field(type = FieldType.Keyword)
    protected final String playerName;
    protected final double playerRating;
    protected int noOfBallsPlayed;
    protected int noOfBoundaries;
    protected int noOfRunsScored;
    private final ArrayList<Match> matchesPlayerWasMOTM = new ArrayList<>();

    protected Player(double playerRating,String playerName) {
        this.playerRating = playerRating;
        this.playerName = playerName;
    }

    public void addMatchToMOTMList(Match match) {
        matchesPlayerWasMOTM.add(match);
    }

    public ArrayList<String> getMatchesPlayerWasMOTM() {
        if(matchesPlayerWasMOTM.size() == 0) {
            return (ArrayList<String>) Arrays.asList("Player wasn't MOTM even a single time");
        }
        ArrayList<String> list = new ArrayList<>();
        for(Match match : matchesPlayerWasMOTM) {
            list.add(match.toString());
        }
        return list;
    }

    public void increamentNumRunsScored(int runs) {
        noOfRunsScored += runs;
    }

    public void increamentNumBallsPlayed() {
        noOfBallsPlayed++;
    }

    public void increamentNumBoundaries() {
        noOfBoundaries++;
    }

    public double getPlayerRating() {
        return playerRating;
    }

    public int getNoOfBallsPlayed() {
        return noOfBallsPlayed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getNoOfRunsScored() {
        return noOfRunsScored;
    }

    public int getNoOfBoundaries() {
        return noOfBoundaries;
    }

    public void setCareerNumBallsPlayed(int balls) {
        noOfBallsPlayed += balls;
    }

    public void setCareerNumBoundaries(int boundaries) {
        noOfBoundaries += boundaries;
    }

    public void setCareerNumRunsScored(int runs) {
        noOfRunsScored += runs;
    }

    public void updatePlayerCareerStats() {
        hashmapsRepo.findById("1").get().updatePlayerTournamentStats(playerName,this);
    }
}