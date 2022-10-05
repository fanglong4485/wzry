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
        this.ad = ad;
        setSpd(spd);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX()-5,getY()-5,10,10);
    }

    @Override
    public void pantSelf(Graphics g) {
        //g.drawImage(getImage(),getX()-16,getY()-16,null);
        g.setColor(Color.BLACK);
        g.fillOval(getX()-5,getY()-5,10,10);
        //g.drawRect(getX()-5,getY()-5,10,10);
        move();
    }

    public void move(){
        //子弹接触到目标，扣血并子弹消失
        if (recIntersectsRec(getRec(),target.getRec())) {
            target.setCurrentHp(target.getCurrentHp() - getAd());
            gameFrame.removeList.add(this);
        }
        double distance = getDistance(getX(), getY(), target.getX(), target.getY());
        double xSpeed = getSpd() * ((target.getX() - getX()) / distance);
        double ySpeed = getSpd() * ((target.getY() - getY()) / distance);
        setX((int) (getX() + xSpeed));
        setY((int) (getY() + ySpeed));
    }
}
