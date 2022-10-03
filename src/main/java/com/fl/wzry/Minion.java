package com.fl.wzry;

import java.awt.*;

public abstract class Minion extends GameObject{
    public Minion(GameFrame gameFrame) {
        super(gameFrame);
    }

    public abstract void move();

    @Override
    public Rectangle getRec() {
       return new Rectangle(getX()-16,getY()-16,45,45);
    }

    @Override
    public void pantSelf(Graphics g) {
        g.drawImage(getImage(),getX()-16,getY()-16,null);
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX()-16,getY()-16,45,45);
        move();
    }
}
