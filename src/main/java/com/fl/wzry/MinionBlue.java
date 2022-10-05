package com.fl.wzry;

public class MinionBlue extends Minion {
    public MinionBlue(GameFrame gameFrame) {
        super(gameFrame);
        setImage("src/main/resources/com/fl/wzry/static/bing-blue.jpg");
        //出生点
        setX(300);
        setY(1000);
        setSpd(5);
    }

    @Override
    public void move() {
        //325.1025  1200.1025  1375.950 1375.350
        if (getX()< 1200 ){
            //setSpd(5);
            setX(getX() + getSpd());
        }
        else if (getX() >= 1200 && getX() < 1350){
            setSpd(2);
            setX(getX() + getSpd());
            setY(getY() - getSpd());
        }
        else if (getX() >= 1350){
            setSpd(2);
            setY(getY() - getSpd());
        }
    }
}
