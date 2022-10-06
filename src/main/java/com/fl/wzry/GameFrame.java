package com.fl.wzry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    //游戏窗口长宽
    private int windowWidth = 800;
    private int windowHeight = 600;

    //游戏背景（地图）
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
     * 防御塔
     */
    Turret turret = new Turret(this);

    /**
     * 游戏元素列表
     */
    List<GameObject> objList = new ArrayList<>();
    List<GameObject> redList = new ArrayList<>();
    List<GameObject> blueList = new ArrayList<>();
    List<GameObject> removeList = new ArrayList<>();

    //双缓冲用，防止闪烁
    private Image offScreenImage = null;

    //攻击键
    private Image attBtnImg = Toolkit.getDefaultToolkit().getImage("src/main/resources/com/fl/wzry/static/attBtn.jpg");


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
        objList.addAll(turret.turretList);
        for (int i = 0; i < 4; i++) {
            redList.add(turret.turretList.get(i));
        }
        JButton attBtn = new JButton();
        attBtn.setSize(66,60);
        attBtn.setLocation(720,500 );
        attBtn.addActionListener(e -> {
            player.attack(redList);
            //System.out.println("sdfa");
        });
            this.add(attBtn);
        while (true) {
            //minionBlue.createMinion(this,blueList);
            //红色同理
            minionRed.createMinion(this,redList);
            repaint();
            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void paint(Graphics g){
        System.out.println("玩家位置："+player.getX() + ","+ player.getY());
        if (offScreenImage == null){
            //加载的图片大小
            offScreenImage = this.createImage(1600,1200);
        }
        Graphics gImage = offScreenImage.getGraphics();

        for (int i = 0; i < objList.size(); i++) {
            objList.get(i).pantSelf(gImage);
        }
        objList.removeAll(removeList);
        //绘制攻击键,固定在窗口右下角
        gImage.drawImage(attBtnImg, player.getX() + getWidth()/2 -70, player.getY() + getHeight() / 2 -70, null);
        //英雄固定在屏幕中间
        g.drawImage(offScreenImage,-player.getX() + getWidth()/2,-player.getY() + getHeight() / 2,null);
        this.requestFocus();
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
