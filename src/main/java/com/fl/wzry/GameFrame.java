package com.fl.wzry;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private int windowWidth = 600;
    private int windowHeight = 400;

    Background bg = new Background(this);

    /**
     * 添加玩家
     */
    Champion player = new Champion(this);

    /**
     * 添加小兵
     */
    MinionBlue minionBlue = new MinionBlue(this);
    MinionRed minionRed = new MinionRed(this);

    /**
     * 游戏元素列表
     */
    List<GameObject> objList = new ArrayList<>();
    List<GameObject> redList = new ArrayList<>();
    List<GameObject> blueList = new ArrayList<>();

    //双缓冲用，防止闪烁
    private Image offScreenImage = null;



    private void launch(){
        setSize(windowWidth,windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(true);
        setTitle("方龙的王者");
        setVisible(true);

        this.addKeyListener(new GameFrame.KeyMonitor());

        //添加游戏元素
        objList.add(bg);
        objList.add(player);



        while (true) {
            minionBlue.createMinion(this,blueList);
            //红色同理
            //minionBlue.createMinion(this,blueList);
            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void paint(Graphics g){
        //System.out.println("玩家位置："+player.getX() + ","+ player.getY());
        if (offScreenImage == null){
            //加载的图片大小
            offScreenImage = this.createImage(1600,1200);
        }
        Graphics oImage = offScreenImage.getGraphics();

        for (GameObject gameObject : objList) {
            gameObject.pantSelf(oImage);
        }
        //bg.pantSelf(oImage);
        //player.pantSelf(oImage);
        //minionBlue.pantSelf(oImage);
        //minionRed.pantSelf(oImage);
        //是英雄固定在屏幕中间
        g.drawImage(offScreenImage,-player.getX() + 300,-player.getY() + 200,null);
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.launch();
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
           player.keyReleased(e);
        }
    }
}
