/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bmi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author amirf
 */


public class SimpleAnimation extends JPanel implements Runnable {
    
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int DELAY = 30;
    private static final int RADIUS = 20;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SimpleAnimation());
        frame.pack();
        frame.setVisible(true);
    }
    private Thread animator;
    private boolean running = false;

    private Point ballPos = new Point(WIDTH / 2, HEIGHT / 2);
    private Point ballDelta = new Point(5, 3);
    private Color ballColor = Color.RED;

    public SimpleAnimation() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);
        g.fillOval(ballPos.x - RADIUS, ballPos.y - RADIUS, RADIUS * 2, RADIUS * 2);
    }

    public void run() {
        running = true;

        while (running) {
            ballPos.x += ballDelta.x;
            ballPos.y += ballDelta.y;

            if (ballPos.x - RADIUS < 0 || ballPos.x + RADIUS > WIDTH) {
                ballDelta.x *= -1;
            }

            if (ballPos.y - RADIUS < 0 || ballPos.y + RADIUS > HEIGHT) {
                ballDelta.y *= -1;
            }

            repaint();

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }

    
}
