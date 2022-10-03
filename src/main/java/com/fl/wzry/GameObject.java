package com.fl.wzry;

import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {

    private int x;
    private int y;
    private int spd;

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

}
