package com.fl.wzry;

public class MinionBlue extends Minion {
    public MinionBlue(GameFrame gameFrame) {
        super(gameFrame);
        setImage("src/main/resources/com/fl/wzry/static/bing-blue.jpg");
        setX(235);
        setY(1000);
        setSpd(25);
    }

    @Override
    public void move() {
        if (getX()< 1200){
            setSpd(5);
            setX(getX() + getSpd());
        }
        else if (getX() >= 1200 && getX()< 1350){
            setSpd(2);
            setX(getX() + getSpd());
            setY(getY() - getSpd());
        }
        if (getY() >= 875 ){
            setSpd(5);
            setY(getY() - getSpd());
        }
    }
}
