package com.tekion.game.cricket.beans.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(indexName = "match_stats_9")
@Setting(settingPath = "static/es-settings.json")
public class MatchStats {

    @Id
    @Field(type = FieldType.Text, name = "id")
    private String id;

    @Field(type = FieldType.Integer, name = "matchId")
    private Integer matchId;

    @Field(type = FieldType.Object, name = "runsScoredByPlayer")
    private Map<String,Integer> runsScoredByPlayer;

    @Field(type = FieldType.Auto, name = "playingEleven")
    private List<String> playingEleven;

    @Field(type = FieldType.Text, name = "manOfTheMatch")
    private String manOfTheMatch;

    @Field(type = FieldType.Text, name = "team1name")
    private String team1name;

    @Field(type = FieldType.Text, name = "team2name")
    private String team2name;

    @Field(type = FieldType.Text, name = "winningTeamName")
    private String winningTeamName;

    public String getWinningTeamName() {
        return winningTeamName;
    }

    public void setWinningTeamName(final String winningTeamName) {
        this.winningTeamName = winningTeamName;
    }

    public String getTeam1name() {
        return team1name;
    }

    public void setTeam1name(final String team1name) {
        this.team1name = team1name;
    }

    public String getTeam2name() {
        return team2name;
    }

    public void setTeam2name(final String team2name) {
        this.team2name = team2name;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(final int matchId) {
        this.matchId = matchId;
    }

    public Map<String, Integer> getRunsScoredByPlayer() {
        return runsScoredByPlayer;
    }

    public void setRunsScoredByPlayer(final HashMap<String, Integer> runsScoredByPlayer) {
        this.runsScoredByPlayer = runsScoredByPlayer;
    }

    public List<String> getPlayingEleven() {
        return playingEleven;
    }

    public void setPlayingEleven(final ArrayList<String> playingEleven) {
        this.playingEleven = playingEleven;
    }

    public String getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(final String manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    @Override
    public String toString() {
        return "MatchStats{" +
                "matchId=" + matchId +
                ", manOfTheMatch='" + manOfTheMatch + '\'' +
                ", team1name='" + team1name + '\'' +
                ", team2name='" + team2name + '\'' +
                ", winningTeamName='" + winningTeamName + '\'' +
                '}';
    }
}
