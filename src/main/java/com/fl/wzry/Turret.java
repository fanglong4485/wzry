package com.fl.wzry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Turret extends GameObject{

   List<Turret> turretList = new ArrayList<Turret>();
    public Turret turretRed1;
    public Turret turretRed2;
    public Turret turretRed3;
    public Turret turretRedBase;
    //public Turret turretBlue5;
    //public Turret turretBlue6;
    //public Turret turretBlue7;
    //public Turret turretBlueBase;
    public Turret(GameFrame gameFrame) {
        super(gameFrame);
        //setImage("src/main/resources/com/fl/wzry/static/turret.jpg");
        turretList.add(turretRed1 = new TurretRed(gameFrame,1375,775));
        turretList.add(turretRed2 = new TurretRed(gameFrame,900,500));
        turretList.add(turretRed3 = new TurretRed(gameFrame,525,200));
        turretList.add(turretRedBase = new TurretRed(gameFrame,1300,250));
        //蓝色同理
        //turretList.add(turretRed1 = new TurretRed(gameFrame,1050,1000));
        //turretList.add(turretRed2 = new TurretRed(gameFrame,650,675));
        //turretList.add(turretRed3 = new TurretRed(gameFrame,225,400));
        //turretList.add(turretRedBase = new TurretRed(gameFrame,300,925));

    }

    public Turret(GameFrame gameFrame, int x, int y) {
        super(gameFrame, x, y);
        setHp(1000);
        setCurrentHp(getHp());
        setAttackCoolDownTime(1000);
        setAttackDistance(300);

    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX() -50,getY() -100,100,180);
    }

    @Override
    public void pantSelf(Graphics g) {

        if (this instanceof TurretRed){
            this.addHp(g,50,130,100,20,Color.RED);
            attack(gameFrame.blueList);
        } else {
            this.addHp(g,50,130,100,20,Color.GREEN);
            attack(gameFrame.redList);
        }
        g.drawImage(getImage(),getX() -50,getY()-100,null);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX() -50,getY() -100,100,180);
        g.drawOval(getX() - 300,getY()-300,600,600);
    }
}
