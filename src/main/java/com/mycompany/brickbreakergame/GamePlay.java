/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreakergame;

/**
 *
 * @author Mohd Imran
 */

//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import javax.swing.JPanel;
//import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener, KeyListener{
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int ballposX = 120;
    private int ballposY = 350;
    private int balldirX = -1;
    private int balldirY = -2;
    private int playerX = 350;
    private MapGenerator map;
    
    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(delay, this);
        
        map = new MapGenerator(3, 7);
        timer.start();
    }
    public void paint(Graphics g) {
        // black canvas
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        map.draw((Graphics2D) g);
        // border
        g.setColor(Color.black);
        g.fillRect(0, 0, 3, 592);   // left
        g.fillRect(0, 0, 692, 3);   // up
        g.fillRect(691, 3, 3, 592); // right
        
        // paddle
        g.setColor(Color.white);
        g.fillRect(playerX, 550, 100, 8);
        
        // bricks
        
        // ball
        g.setColor(Color.WHITE);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        // score 
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD,20));
        g.drawString("Score : " + score, 590, 30);
        
        // gameover
        if(ballposY > 570) {
            play = false;
            balldirX = 0;
            balldirY = 0;
            
            g.setColor(Color.yellow);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GameOver !! Score : " + score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter To Restart", 190, 340);
        }
        
        // Winner
        if(totalBricks == 0) {
            play = false;
            balldirX = -1;
            balldirY = -2;
            
            g.setColor(Color.YELLOW);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won !! Score : " + score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter To Restart", 190, 340);
        }
        g.dispose();
    }
    
  

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                balldirY = -balldirY;
            }
            A :
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int bricksWidth = map.brickWidth;
                        int bricksHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBrick(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                balldirX = -balldirX;
                            } else {
                                balldirY = -balldirY;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX += balldirX;
            ballposY += balldirY;
            if (ballposX < 0) {
                balldirX = -balldirX;
            }
            if (ballposY < 0) {
                balldirY = -balldirY;
            }
            if (ballposX > 670) {
                balldirX = -balldirX;
            }
        }
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
     @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            }
            else moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            }
            else moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                score = 0;
                totalBricks = 21;
                ballposX = 120;
                ballposY = 350;
                balldirX = -1;
                balldirY = -2;
                playerX = 320;
                
                map = new MapGenerator(3, 7);
            }
        }
        repaint();
    }
      private void moveLeft() {
        play = true;
        playerX -= 20;
    }
    private void moveRight() {
        play = true;
        playerX += 20;
    }
}


