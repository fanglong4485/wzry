package com.fl.wzry;

import java.awt.*;
import java.util.List;

public abstract class Minion extends GameObject{

    private boolean nextMinion = true;
    private boolean nextLine = true;
    private int minionCount = 0;

    public Minion(GameFrame gameFrame) {
        super(gameFrame);
    }

    public abstract void move();

    class NextMinion extends Thread{
        public void run(){
            nextMinion = false;
            try {
                Thread.sleep(1500);
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
                    //MinionBlue minionBlue = new MinionBlue(gameFrame);
                    MinionBlue minionBlue = new MinionBlue(gameFrame);
                    gameFrame.objList.add(minionBlue);
                    minionList.add(minionBlue);
                }
                //红色小兵同理
                //if (minionList == this.gameFrame.objList){
                //    MinionBlue minionBlue = new MinionBlue(gameFrame);
                //    gameFrame.objList.add(minionBlue);
                //    minionList.add(minionBlue);
                //}
                minionCount ++;
                new NextMinion().start();
            }
            if (minionCount == 3){
                minionCount = 0;
                new NextLine().start();
            }
        }
    }

    @Override
    public Rectangle getRec() {
       return new Rectangle(getX()-16,getY()-16,45,45);
    }

    @Override
    public void pantSelf(Graphics g) {
        setHp(100);
        setCurrentHp(100);
        if (this instanceof MinionBlue){
            this.addHp(g,17,28,45,10,Color.GREEN);
        } else {
            this.addHp(g,17,28,45,10,Color.red);
        }
        g.drawImage(getImage(),getX()-16,getY()-16,null);
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX()-16,getY()-16,45,45);
        move();
    }
}
