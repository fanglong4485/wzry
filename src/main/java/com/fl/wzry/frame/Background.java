package com.fl.wzry.frame;

import com.fl.wzry.elements.GameObject;

import java.awt.*;

public class Background extends GameObject {
    Image bg = Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/wzry.jpg");

    public Background(GameFrame gameFrame){
        super(gameFrame);
    }


    @Override
    public Rectangle getRec() {
        return null;
    }

    @Override
    public void pantSelf(Graphics g) {
        g.drawImage(bg,0,0,null);
    }
}
