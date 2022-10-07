package com.fl.wzry.turret;

import com.fl.wzry.frame.GameFrame;

public class TurretRed extends Turret{


    public TurretRed(GameFrame gameFrame) {
        super(gameFrame);
    }

    public TurretRed(GameFrame gameFrame, int x, int y) {
        super(gameFrame, x, y);
        setImage("src/main/resources/com/fl/wzry/static/turret.jpg");
    }
}
