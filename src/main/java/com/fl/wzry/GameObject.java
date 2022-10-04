package com.fl.wzry;

import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {

    private int x;
    private int y;
    private int spd;
    private int hp;
    private int currentHp;

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

}
