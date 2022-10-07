package com.fl.wzry.minion;

import com.fl.wzry.frame.GameFrame;
import com.fl.wzry.elements.GameObject;

import java.util.ArrayList;
import java.util.List;

public class MinionRed extends Minion {
    public MinionRed(GameFrame gameFrame) {
        super(gameFrame);
        setImage("src/main/resources/com/fl/wzry/static/bing-red.jpg");
        //出生点
        setX(1325);
        setY(225);
        setSpd(5);
    }

    @Override
    public void move(List<GameObject> blueList) {
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
                    attack(blueList);
                }
                else {
                    setFindTarget(false);
                }
            }
        }
        else {
            findTarget((ArrayList<GameObject>) blueList);
            if (getY()< 875 ){
                setY(getY() + getSpd());
            }
            else if (getY() >= 875 && getY() < 1000){
                setSpd(2);
                setX(getX() - getSpd());
                setY(getY() + getSpd());
            }
            else if (getY() >= 1000){
                if (getX() >= 300){
                    setSpd(2);
                    setX(getX() - getSpd());
                }
            }
        }

    }

}
