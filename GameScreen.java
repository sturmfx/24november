package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public class GameScreen extends JFrame implements KeyListener
{
    public static int cwidth = 500;
    public static int cheight = 500;
    public static boolean up = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean left = false;

    public static int speed = 1;
    public static Color color = Color.RED;
    public static int tick = 1;

    public static int x = 250;
    public static int y = 250;
    public static double x1 = 250.0;
    public static double y1 = 250.0;
    public static int width = 50;
    public static int height = 50;

    public static long temp_time = System.currentTimeMillis();
    public static Canvas p = new Canvas();

    BufferStrategy bufferStrategy;
    Graphics g;

    public GameScreen(String s)
    {
        super(s);

        p.setSize(cwidth, cheight);
        p.setVisible(true);
        p.setFocusable(false);

        add(p);
        addKeyListener(this);
        setSize(cwidth, cheight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p.createBufferStrategy(4);
        p.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {

            }
            @Override
            public void mousePressed(MouseEvent e)
            {

            }
        });
        loop();

    }

    @Override
    public void keyTyped(KeyEvent keyEvent)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            down = false;
        }
    }

    public void loop()
    {
        while(true)
        {


            if(System.currentTimeMillis() - temp_time > tick)
            {
                bufferStrategy = p.getBufferStrategy();
                g = bufferStrategy.getDrawGraphics();
                g.clearRect(0, 0, 500, 500);
                g.fillOval(x, y, width, height);
                bufferStrategy.show();
                g.dispose();

                temp_time = System.currentTimeMillis();

                if(up&&!down&&!right&&!left) y--;
                if(!up&&down&&!right&&!left) y++;
                if(!up&&!down&&right&&!left) x++;
                if(!up&&!down&&!right&&left) x--;

            }

        }
    }


}
