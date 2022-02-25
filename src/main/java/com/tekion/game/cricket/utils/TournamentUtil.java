package com.tekion.game.cricket.utils;

import com.tekion.game.cricket.beans.pojo.Team;
import java.util.Arrays;
import java.util.List;

public class TournamentUtil {
    public static final List<Team> teamsList = (List<Team>) Arrays.asList(
            new Team(PlayerUtil.playerList.subList(0,11),PlayerUtil.playerList.subList(6,11), "INDIA"),
            new Team(PlayerUtil.playerList.subList(11,22),PlayerUtil.playerList.subList(17,22),"PAKISTAN"),
            new Team(PlayerUtil.playerList.subList(22,33),PlayerUtil.playerList.subList(28,33),"NEW ZEALAND"),
            new Team(PlayerUtil.playerList.subList(33,44),PlayerUtil.playerList.subList(39,44),"ENGLAND"),
            new Team(PlayerUtil.playerList.subList(44,55),PlayerUtil.playerList.subList(50,55),"AUSTRAILIA")
            );
}
