package com.tekion.game.cricket.beans.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import java.util.ArrayList;
import java.util.List;

@Document(indexName = "player_stats_6")
@Setting(settingPath = "static/es-settings.json")
public class PlayerTournamentStats {

    @Id
    @Field(type = FieldType.Text, name = "id")
    private String id;

    @Field(type = FieldType.Text, name = "playerName")
    private String playerName;

    @Field(type = FieldType.Integer, name = "runsScoredInTournament")
    private Integer runsScoredInTournament;

    @Field(type = FieldType.Auto, name = "matchesPlayerGotManOfTheMatch")
    private List<Integer> matchesPlayerGotManOfTheMatch;

    public String getPlayerName() {
        return playerName;
    }

    public PlayerTournamentStats setPlayerName(final String playerName) {
        this.playerName = playerName;
        return this;
    }

    public Integer getRunsScoredInTournament() {
        return runsScoredInTournament;
    }

    public PlayerTournamentStats setRunsScoredInTournament(final Integer runsScoredInTournament) {
        this.runsScoredInTournament = runsScoredInTournament;
        return this;
    }

    public List<Integer> getMatchesPlayerGotManOfTheMatch() {
        return matchesPlayerGotManOfTheMatch;
    }

    public PlayerTournamentStats setMatchesPlayerGotManOfTheMatch(final ArrayList<Integer> matchesPlayerGotManOfTheMatch) {
        this.matchesPlayerGotManOfTheMatch = matchesPlayerGotManOfTheMatch;
        return this;
    }
}
