package com.fl.wzry;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    private int windowWidth = 1600;
    private int windowHeight = 1200;

    Background bg = new Background(this);

    Champion player = new Champion(this);

    private Image offScreenImage = null;



    private void launch(){
        setSize(windowWidth,windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        setTitle("方龙的王者");
        setVisible(true);

        this.addKeyListener(new GameFrame.KeyMonitor());
        while (true) {
            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void paint(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(1600,1200);
        }
        Graphics oImage = offScreenImage.getGraphics();
        bg.pantSelf(oImage);
        player.pantSelf(oImage);

        g.drawImage(offScreenImage,0,0,null);
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
