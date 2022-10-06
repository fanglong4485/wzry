package com.fl.wzry;

import java.awt.*;

public class ChampionDaji extends Champion{
    public ChampionDaji(GameFrame gameFrame) {
        super(gameFrame);
        setAbilityOne(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityOne.jpg"));
        setAbilityTwo(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityTwo.jpg"));
        setAbilityThree(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityThree.jpg"));
    }
}
