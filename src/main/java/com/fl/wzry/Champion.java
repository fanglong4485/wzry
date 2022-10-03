package com.fl.wzry;

import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.event.KeyEvent;

@EqualsAndHashCode(callSuper = true)
public class Champion extends GameObject{

    public boolean up,down,left,right;

    static String[]  imgs = new String[8];
    int moveCount = 1;

    static {
        for (int i = 1; i < 2; i++) {
            imgs[i] = "src/main/resources/com/fl/wzry/static/move/" + i + ".jpg";
        }
    }


public Champion(GameFrame gameFrame) {
    super(gameFrame);
    setImage("src/main/resources/com/fl/wzry/static/champion.jpg");
    setX(80);
    setY(80);
    setSpd(25);
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
        return new Rectangle(getX(),getY(),0,0);
    }

    @Override
    public void pantSelf(Graphics g) {
        g.drawImage(getImage(),getX()-32,getY()-50,null);
        g.setColor(Color.GREEN);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX()-23,getY()-50,60,120);
        move();
    }


}
