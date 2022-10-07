package com.fl.wzry.turret;

import com.fl.wzry.frame.GameFrame;

public class TurretBlue extends Turret {
    public TurretBlue(GameFrame gameFrame) {
        super(gameFrame);
    }

    public TurretBlue(GameFrame gameFrame, int x, int y) {
        super(gameFrame, x, y);
        setImage("src/main/resources/com/fl/wzry/static/turret-blue.jpg");
    }
}
