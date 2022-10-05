package com.fl.wzry;

import java.util.ArrayList;
import java.util.List;

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
    public void move(List<GameObject> redList) {
        //325.1025  1200.1025  1375.950 1375.350

        if (isFindTarget()){
            //离开检测范围
            if(!recIntersectsCir(getTarget().getRec(),getX(),getY(),125)){
                setFindTarget(false);
            }
            //在检测范围内
            else {
                //没有攻击目标,向目标移动直至至标记攻击目标
                if (!isHasTarget()){
                    moveToTarget();
                } else if (getTarget().isAlive()){
                    attack(redList);
                }
                else {
                    setFindTarget(false);
                }
            }
        }
        else {
            findTarget((ArrayList<GameObject>) redList);
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
                if (getY() > 325){
                    setSpd(2);
                    setY(getY() - getSpd());
                }
            }
        }

    }
}
