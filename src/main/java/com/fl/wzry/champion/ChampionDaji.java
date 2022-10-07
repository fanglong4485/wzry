package com.fl.wzry.champion;

import com.fl.wzry.elements.Bullet;
import com.fl.wzry.frame.GameFrame;
import com.fl.wzry.elements.GameObject;
import com.fl.wzry.minion.MinionRed;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChampionDaji extends ChampionBase {

    /** 鼠标监视器 */
    MouseMonitor m;

    /** 一技能多边形*/
    Polygon p;

    /**技能是否处于释放状态*/
    boolean ifAbilityOne = false;

    double cos;
    double sin;


    /** 技能冷却时间*/
    @Getter
    @Setter
    private int coolDownTimeOne;
    @Getter
    @Setter
    private int coolDownTimeTwo;
    @Getter
    @Setter
    private int coolDownTimeThree;

    /** 技能冷却状态*/
    @Getter
    @Setter
    private boolean coolDownOne = true;
    @Getter
    @Setter
    private boolean coolDownTwo = true;
    @Getter
    @Setter
    private boolean coolDownThree = true;

    /**一技能移动步数*/
    int steps;

    public void abilityOneMove(){
        p.translate((int)(10*cos),-(int)(10*sin));
        for (GameObject redObject : gameFrame.redList) {
            if (redObject instanceof MinionRed && p.intersects(redObject.getRec()) && attacked.indexOf(redObject) == -1){
                //小兵扣血，添加到以攻击列表
                redObject.setCurrentHp(getCurrentHp() - 99);
                attacked.add(redObject);
            }
        }
    }

    //攻击过的目标
    ArrayList<GameObject> attacked ;

    @Override
    public void abilityOne() {
        if (isCoolDownOne()){
            m = new MouseMonitor();
            p = new Polygon();
            gameFrame.addMouseListener(m);
            attacked = new ArrayList<GameObject>();
        }
    }

    @Override
    public void abilityTwo() {
        if (coolDownTwo){
            boolean find  = false;
            for (GameObject redObject : gameFrame.redList) {
                if (redObject instanceof MinionRed && recIntersectsCir(redObject.getRec(),getX(),getY(),200) && redObject.isAlive()){
                    Bullet abilityTwobullet = new Bullet(gameFrame, this, redObject, 60, 15, "src/main/resources/com/fl/wzry/static/daji/abilityTwoImg.jpg");
                    gameFrame.objList.add(abilityTwobullet);
                    find = true;
                    break;
                }
                if (find) {
                    new AbilityTwoCD().start();
                    find = false;
                }
            }
        }
    }

    @Override
    public void abilityThree() {

    }

    @Override
    public void abilityEffect(Graphics g) {
        if (ifAbilityOne){
            g.setColor(Color.PINK);
            g.fillPolygon(p);
            abilityOneMove();
            steps ++;
            if (steps == 25){
                steps = 0;
                ifAbilityOne = false;
            }
        }
    }

    public ChampionDaji(GameFrame gameFrame) {
        super(gameFrame);
        setAbilityOne(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityOne.jpg"));
        setAbilityTwo(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityTwo.jpg"));
        setAbilityThree(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/daji/abilityThree.jpg"));
        setCoolDownTimeOne(5);
        setCoolDownTimeTwo(600);
        setCoolDownTimeThree(800);
    }

    private class MouseMonitor extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX(),mouseY = e.getY(),
                    //playerX=400,playerY = 300;
                playerX=gameFrame.player.getX(),playerY = gameFrame.player.getY();
            double distance = getDistance(mouseX, mouseY, playerX, playerY);
            cos = (mouseX - playerX) / distance;
            sin = -(mouseY - playerY) / distance;
            //坐标差
            int difX = (int) (60 * sin);
            int difY = (int) (60 * cos);
            p.addPoint(getX() - difX,getY() - difY);
            p.addPoint(getX() + difX,getY() + difY);
            p.addPoint(getX() + difX + (int) (20 * cos),getY() + difY -(int) (20 * sin));
            p.addPoint(getX() - difX + (int) (20 * cos),getY() - difY -(int) (20 * sin));
            exit();
            new AbilityOneCD().start();
            ifAbilityOne = true;
        }
    }

    public void exit(){
        this.gameFrame.removeMouseListener(m);
    }



    class AbilityOneCD extends Thread{
        public void run(){
            setCoolDownOne(false);
            try {
                Thread.sleep(coolDownTimeOne);

                int one = getCoolDownTimeOne();
                while (one > 0) {
                    Thread.sleep(one);
                    System.out.println("one coolDown time ==="+one );
                    one -= 1000;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setCoolDownOne(true);
            this.interrupted();
        }
    }

    class AbilityTwoCD extends Thread{
        public void run(){
            setCoolDownOne(false);
            try {
                Thread.sleep(coolDownTimeTwo);

                int two = getCoolDownTimeTwo();
                while (two > 0) {
                    Thread.sleep(two);
                    System.out.println("two coolDown time ==="+two );
                    two -= 1000;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setCoolDownTwo(true);
            this.interrupted();
        }
    }
}
