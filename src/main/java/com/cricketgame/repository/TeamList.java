package com.cricketgame.repository;

import com.cricketgame.pojo.Player;
import com.cricketgame.pojo.Team;
import java.util.ArrayList;
import java.util.Arrays;

public class TeamList {
    public static final ArrayList<Team> teams = new ArrayList<Team>(Arrays.asList(
            new Team(
                    new ArrayList<Player>(Arrays.asList(Players.VIRAT_KOHLI,Players.ROHIT_SHARMA,Players.SHIKHAR_DHAWAN, Players.KL_RAHUL,Players.HARDIK_PANDYA,Players.SHREYAS_IYER,Players.JASPRIT_BUMRAH,Players.YUZVENDRA_CHAHAL,Players.BHUVNESHWAR_KUMAR, Players.MOHAMMAD_SHAMI,Players.KULDEEP_YADAV)),
                    new ArrayList<Player>(Arrays.asList(Players.JASPRIT_BUMRAH,Players.YUZVENDRA_CHAHAL,Players.BHUVNESHWAR_KUMAR,Players.MOHAMMAD_SHAMI,Players.KULDEEP_YADAV)),
                    "INDIA"
            ),
            new Team(
                    new ArrayList<Player>(Arrays.asList(Players.BABAR_AZAM,Players.FAKHAR_ZAMAN,Players.IMAM_UL_HAQ,Players.HARIS_SOHAIL,Players.IMAD_WASIM,Players.SARFARAZ_AHMED,Players.SHAHEEN_AFRIDI,Players.SHADAB_KHAN,Players.IMAD_WASIM_B,Players.HASAN_ALI,Players.WAHAB_RIAZ)),
                    new ArrayList<Player>(Arrays.asList(Players.SHAHEEN_AFRIDI,Players.SHADAB_KHAN,Players.IMAD_WASIM_B,Players.HASAN_ALI,Players.WAHAB_RIAZ)),
                    "Pakistan"
            )
    ));
}
