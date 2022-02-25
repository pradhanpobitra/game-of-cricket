package com.tekion.game.cricket.beans.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "team_stats_1")
@Setting(settingPath = "static/es-settings.json")
public class TeamTournamentStats {

    @Id
    @Field(type = FieldType.Text, name = "id")
    private String id;

    @Field(type = FieldType.Text, name = "teamName")
    private String teamName;

    @Field(type = FieldType.Integer, name = "winsCount")
    private Integer winsCount;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    public Integer getWinsCount() {
        return winsCount;
    }

    public void setWinsCount(final Integer winsCount) {
        this.winsCount = winsCount;
    }
}
