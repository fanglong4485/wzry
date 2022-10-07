package com.fl.wzry.champion;

import com.fl.wzry.frame.GameFrame;
import com.fl.wzry.elements.GameObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

@EqualsAndHashCode(callSuper = true)
public abstract class ChampionBase extends GameObject {

    public boolean up,down,left,right;

    /** 移动图集*/
    static String[]  imgs = new String[8];
    int moveCount = 1;

    /** 技能图片*/
    @Getter
    @Setter
    private Image abilityOne;
    @Getter
    @Setter
    private Image abilityTwo;
    @Getter
    @Setter
    private Image abilityThree;

    static {
        for (int i = 1; i < 2; i++) {
            imgs[i] = "src/main/resources/com/fl/wzry/static/move/" + i + ".jpg";
        }
    }

    /**技能方法 */
    public abstract void abilityOne();
    public abstract void abilityTwo();
    public abstract void abilityThree();
    public abstract void abilityEffect(Graphics g);


public ChampionBase(GameFrame gameFrame) {
    super(gameFrame);
    setImage("src/main/resources/com/fl/wzry/static/champion.jpg");
    setX(175);
    setY(1025);
    setSpd(25);
    setHp(1000);
    setCurrentHp(1000);
    setAttackDistance(200);
    setAttackCoolDownTime(1);
}

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W){
            up = true;
        }
        if (keyCode == KeyEvent.VK_A){
            left = true;
        }
        if (keyCode == KeyEvent.VK_S){
            down = true;
        }
        if (keyCode == KeyEvent.VK_D){
            right = true;
        }
    }

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W){
            up = false;
        }
        if (keyCode == KeyEvent.VK_A){
            left = false;
        }
        if (keyCode == KeyEvent.VK_S){
            down = false;
        }
        if (keyCode == KeyEvent.VK_D){
            right = false;
        }
    }

    public void move(){
        if (up){
            setY(getY() - getSpd());
        }
        if (down){
            setY(getY() + getSpd());
        }
        if (left){
            setX(getX() - getSpd());
        }
        if (right){
            setX(getX() + getSpd());
        }
        if (up || down || left || right){
            setImage(imgs[moveCount]);
            moveCount ++;
            if (moveCount == 2){
                moveCount = 0;
            }
        }
         else {
             setImage("src/main/resources/com/fl/wzry/static/champion.jpg");
         }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX()-32,getY()-50,75,105);
    }

    @Override
    public void pantSelf(Graphics g) {
        if (getCurrentHp() <= 0){
            setAlive(false);
            gameFrame.removeList.add(this);

        } else {
            addHp(g,30,80,80,20,Color.GREEN);
            g.drawImage(getImage(),getX()-32,getY()-50,null);
            g.setColor(Color.GREEN);
            g.fillOval(getX(),getY(),10,10);
            g.drawRect(getX()-32,getY()-50,75,105);
            //绘制一技能图片
            g.drawImage(abilityOne,gameFrame.player.getX() + gameFrame.getWidth()/2 -150, gameFrame.player.getY() + gameFrame.getHeight() / 2 -70, null);
            g.drawImage(abilityTwo,gameFrame.player.getX() + gameFrame.getWidth()/2 -150, gameFrame.player.getY() + gameFrame.getHeight() / 2 -150, null);
            g.drawImage(abilityThree,gameFrame.player.getX() + gameFrame.getWidth()/2 -70, gameFrame.player.getY() + gameFrame.getHeight() / 2 -150, null);
            move();
            abilityEffect(g);
        }
    }

    /**
     * 添加技能按钮
     */
    public void addBtn(){
        JButton btn1 = new JButton();
        btn1.setSize(76,74);
        btn1.setLocation(640,500);
        btn1.addActionListener(e->{
            abilityOne();
        });
        gameFrame.add(btn1);

        JButton btn2 = new JButton();
        btn2.setSize(76,74);
        btn2.setLocation(640,420);
        btn2.addActionListener(e->{
            abilityTwo();
        });
        gameFrame.add(btn2);

        JButton btn3 = new JButton();
        btn3.setSize(76,74);
        btn3.setLocation(720,420);
        btn3.addActionListener(e->{
           abilityThree();
        });
        gameFrame.add(btn3);
    }


}
