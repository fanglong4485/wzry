package com.fl.wzry;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;


public class Bullet extends GameObject{
    @Getter
    @Setter
    GameObject attacker;
    @Getter
    @Setter
    GameObject target;
    @Getter
    @Setter
    private int ad;

    public Bullet(GameFrame gameFrame) {
        super(gameFrame);
    }

    public Bullet(GameFrame gameFrame, GameObject attacker,GameObject target,int ad, int spd ) {
        super(gameFrame, attacker.getX(), attacker.getY());
        this.attacker = attacker;
        this.target = target;
        setAd(ad);
        setSpd(spd);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX()-5,getY()-5,10,10);
    }

    @Override
    public void pantSelf(Graphics g) {
        g.drawImage(getImage(),getX()-16,getY()-16,null);
        g.setColor(Color.BLACK);
        g.fillOval(getX()-5,getY()-5,10,10);
        //g.drawRect(getX()-5,getY()-5,10,10);
        move();
    }

    public void move(){
        if (recIntersectsRec(getRec(),target.getRec())) {
            target.setCurrentHp(target.getCurrentHp() - getAd());
            gameFrame.removeList.add(this);
        }
        int distance = (int)getDistance(getX(), getY(), target.getX(), target.getY());
        int xSpeed = getSpd() * ((target.getX() - getX()) / distance);
        int ySpeed = getSpd() * ((target.getY() - getY()) / distance);
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
    }
}
