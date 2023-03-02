/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreakergame;


import javax.swing.JFrame;
/**
 *
 * @author Mohd Imran
 */
public class BrickBreakerGame {
       public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Brick Breaker");
        frame.setBounds(10,10,700, 600);
//        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        GamePlay gameplay = new GamePlay();
        frame.add(gameplay);
    }
}