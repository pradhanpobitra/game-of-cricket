package com.cricketgame.document;

import com.cricketgame.pojo.Batsman;
import com.cricketgame.pojo.Bowler;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import java.util.HashMap;

@Document(indexName = "hashmaps")
@Setting(settingPath = "static/es-settings.json")
public class Hashmaps {

    @Id
    @Field(type = FieldType.Keyword)
    private final String hashMapsId = "1";
    private HashMap<String,Integer> numTeamWins = new HashMap<>();
    private HashMap<String, Player> playerTournamentStats = new HashMap<>();

    public void increamentNumTeamWins(String teamName) {
        if( ! numTeamWins.containsKey(teamName) ) {
            numTeamWins.put(teamName , 1);
        }
        else {
            Integer numWins = numTeamWins.get(teamName);
            numTeamWins.put(teamName, numWins + 1);
        }
    }

    public String getNumTeamWins(String teamName) {
        return numTeamWins.get(teamName).toString();
    }

    public String getPlayerTournamentRuns(String playerName) {
        if( ! playerTournamentStats.containsKey(playerName) ) {
            return "No such player has played.";
        }
        return playerTournamentStats.get(playerName).getNoOfRunsScored().toString();
    }

    public void updatePlayerTournamentStats(String playerName, Player player) {
        if (player.getClass().toString() == "com.cricketgame.pojo.Bowler") {
            if (!playerTournamentStats.containsKey(playerName)) {
                playerTournamentStats.put(playerName, new Bowler(player.getPlayerRating(), player.getPlayerName()));
            }
            Bowler b = (Bowler) player;
            Bowler bowler = (Bowler) playerTournamentStats.get(playerName);
            bowler.setCareerNumOversBowled(b.getNoOfOversBalled());
            bowler.setCareerMaidenOversBalled(b.getNoOfMaidenOversBalled());
            bowler.setCareerRunsGiven(b.getRunsGiven());
            bowler.setCareerWicketsTaken(b.getNoOfWicketsTaken());
        }
        else {
            if( ! playerTournamentStats.containsKey(playerName) ) {
                playerTournamentStats.put(playerName, new Batsman(player.getPlayerRating(),playerName));
            }
        }
        Player p = playerTournamentStats.get(playerName);
        p.setCareerNumBallsPlayed(player.getNoOfBallsPlayed());
        p.setCareerNumBoundaries(player.getNoOfBoundaries());
        p.setCareerNumRunsScored(player.getNoOfRunsScored());
    }
}
