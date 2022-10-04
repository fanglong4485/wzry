package com.fl.wzry;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public abstract class GameObject {

    private int x;
    private int y;
    private int spd;
    private int hp;
    private int currentHp;
    private GameObject target;
    private boolean hasTarget = false;
    private int attackDistance;
    private int attackCoolDownTime;
    private boolean attackCoolDown = true;

    Image image;

    GameFrame gameFrame;

    public GameObject(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public GameObject(GameFrame gameFrame,int x,int y){
        this.gameFrame = gameFrame;
        this.x = x;
        this.y = y;
    }

    public void setImage(String image) {
        this.image = Toolkit.getDefaultToolkit().getImage(image);
    }

    public abstract Rectangle getRec();

    public abstract void pantSelf(Graphics g);

    public void  addHp(Graphics g,int difX,int difY,int width,int height,Color color)  {
        g.setColor(Color.BLACK);
        g.drawRect(getX() - difX,getY() - difY,width,height);
        g.setColor(color);
        g.fillRect(getX() - difX,getY() - difY, width * getCurrentHp()/getHp(),height);
    }

    public double getDistance(int x1,int y1,int x2,int y2){
        return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
    }

    /**
     * 矩形碰撞检测
     * @param r1
     * @param r2
     * @return
     */
    public boolean recIntersectsRec(Rectangle r1,Rectangle r2){
        return r1.intersects(r2);
    }

    /**
     * 矩形圆形碰撞检测
     */
    public boolean recIntersectsCir(Rectangle r, int x, int y,int distance) {
        return getDistance(x, y, r.x, r.y) < distance
                || getDistance(x, y, r.x + r.width, r.y) < distance
                || getDistance(x, y, r.x, r.y + r.height) < distance
                || getDistance(x, y, r.x + r.width, r.y + r.height) < distance;
    }

    public void attack(List<GameObject> targetList){
        if (hasTarget){
            if (isAttackCoolDown()){
                Bullet bullet = null;
                if (Turret.class.isAssignableFrom(getClass())){
                    bullet = new Bullet(gameFrame,this,getTarget(),50,5);
                    gameFrame.objList.add(bullet);
                }
                new AttackCD().start();
            }

        }else {
            for (GameObject gameObject : targetList) {
                if (recIntersectsCir(gameObject.getRec(),getX(),getY(),getAttackDistance())){
                    target = gameFrame.player;
                    hasTarget = true;
                    break;
                }
            }

            if (!hasTarget && targetList == gameFrame.blueList) {
                if (recIntersectsCir(gameFrame.player.getRec(),getX(),getY(),getAttackDistance())){
                    target = gameFrame.player;
                    hasTarget = true;
                }

            }
        }
    }

    class AttackCD extends Thread{
        public void run(){
            setAttackCoolDown(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setAttackCoolDown(true);
            AttackCD.interrupted();
        }
    }
}
