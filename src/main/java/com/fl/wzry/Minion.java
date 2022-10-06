package com.fl.wzry;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Minion extends GameObject{

    private boolean nextMinion = true;
    private boolean nextLine = true;
    private int minionCount = 0;
    @Getter
    @Setter
    private boolean isFindTarget = false;

    public Minion(GameFrame gameFrame) {
        super(gameFrame);
        setHp(100);
        setCurrentHp(100);
        setAttackDistance(75);
        setAttackCoolDownTime(1000);
    }

    public abstract void move(List<GameObject> objList);

    public void moveToTarget(){
        //如果不在攻击范围内
        if (!recIntersectsCir(getTarget().getRec(),getX(),getY(),75)){
            double distance = getDistance(getX(), getY(), getTarget().getX(), getTarget().getY());
            double xSpeed = getSpd() * ((getTarget().getX() - getX()) / distance);
            double ySpeed = getSpd() * ((getTarget().getY() - getY()) / distance);
            setX((int) (getX() + xSpeed));
            setY((int) (getY() + ySpeed));
        }
        else {
            setHasTarget(true);
        }
    }

    class NextMinion extends Thread{
        public void run(){
            nextMinion = false;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextMinion = true;
            this.interrupt();
        }
    }

    class NextLine extends Thread{
        public void run(){
            nextLine = false;
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextLine = true;
            this.interrupt();
        }
    }

    public void createMinion(GameFrame gameFrame, List<GameObject> minionList){
        if (nextLine){
            if (nextMinion){
                if (minionList == gameFrame.blueList){
                    MinionBlue minionBlue = new MinionBlue(gameFrame);
                    gameFrame.objList.add(minionBlue);
                    minionList.add(minionBlue);
                }
                //红色小兵同理
                if (minionList == this.gameFrame.redList){
                    MinionRed minionRed = new MinionRed(gameFrame);
                    gameFrame.objList.add(minionRed);
                    minionList.add(minionRed);
                }
                minionCount ++;
                new NextMinion().start();
            }
            if (minionCount == 3){
                minionCount = 0;
                //下一队小兵进入冷却
                new NextLine().start();
            }
        }
    }

    public void findTarget(ArrayList<GameObject> objectList){
        for (GameObject obj : objectList) {
            if (recIntersectsCir(obj.getRec(),getX(),getY(),125)){
                setTarget(obj);
                this.isFindTarget = true;
            }
        }
        //if (recIntersectsCir(gameFrame.player.getRec(),getX(),getY(),125)){
        //    setTarget(gameFrame.player);
        //    this.isFindTarget = true;
        //}
    }

    @Override
    public Rectangle getRec() {
       return new Rectangle(getX()-16,getY()-16,45,45);
    }

    @Override
    public void pantSelf(Graphics g) {
        if (getCurrentHp() <= 0){
            setAlive(false);
            gameFrame.removeList.add(this);
            if (this instanceof MinionBlue){
                gameFrame.blueList.remove(this);
            } else {
                gameFrame.redList.remove(this);
            }
        }

        if (this instanceof MinionBlue){
            this.addHp(g,17,28,45,10,Color.GREEN);
            move(gameFrame.redList);
        } else {
            this.addHp(g,17,28,45,10,Color.red);
            move(gameFrame.blueList);
        }
        g.drawImage(getImage(),getX()-16,getY()-16,null);
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX()-16,getY()-16,45,45);
    }
}
