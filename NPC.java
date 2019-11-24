package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC
{
    public static double player_x = 0.0;
    public static double player_y = 0.0;
    public static int npc_count = 0;
    public static int width = 800;
    public static int height = 800;
    public static ArrayList<NPC> list = new ArrayList<NPC>();
    public static long temp_time;
    public static Random r = new Random();

    public int id;
    public boolean alive = true;
    public boolean can_fire = false;
    int radius;
    Color color;
    int damage;
    int hp;
    int speed;
    int rate_of_fire;// milliseconds
    double x;
    double y;
    double dx;
    double dy;
    double temp_dx;
    double temp_dy;
    int dir_x;
    int dir_y;
    double k;

    public NPC(int radius1, Color color1, int damage1, int hp1, int speed1, int rate_of_fire1)
    {
        id = npc_count;
        npc_count++;
        radius = radius1;
        color = color1;
        damage = damage1;
        hp = hp1;
        speed = speed1;
        rate_of_fire = rate_of_fire1;

        if(r.nextInt(4) == 0)
        {
            x = (double) r.nextInt(width);
            dir_x = r.nextInt(width);
            y  = 0.0;
            dir_y = height;
            calculatedxdy();
        }

        if(r.nextInt(4) == 1)
        {
            x = (double) width;
            dir_x = 0;
            y = (double) r.nextInt(height);
            dir_y = r.nextInt(height);
            calculatedxdy();
        }

        if(r.nextInt(4) == 2)
        {
            x = (double) r.nextInt(width);
            dir_x = r.nextInt(width);
            y = (double) height;
            dir_y = 0;
            calculatedxdy();
        }

        if(r.nextInt(4) == 3)
        {
            x = 0.0;
            dir_x = width;
            y = (double) r.nextInt(height);
            dir_y =  r.nextInt(height);
            calculatedxdy();
        }

    }

    public static void setXY(Double px, Double py)
    {
        player_x = px;
        player_y = py;
    }

    public static void add()
    {
        list.add(new NPC(30, Color.RED, 10, 100, 100,1000));
    }

    public static void updateallNPC()
    {
        for(NPC n : list)
        {
            if(n.isAlive())
            {
                n.update();
            }
        }
    }

    public void calculatedxdy()
    {
        temp_dx = (double) dir_x - x;
        temp_dy = (double) dir_y - y;
        k = (double) speed / (Math.sqrt(temp_dx * temp_dx + temp_dy * temp_dy));
        dx = k * temp_dx;
        dy = k * temp_dy;
    }

    public void update()
    {
        x = x + dx;
        y = y + dy;
    }

    public boolean isAlive()
    {
        boolean result = true;
        if(alive)
        {
            if(hp > 0)
            {
                if(((int) x > -1)&&((int) x < width + 1)&&((int) y > -1)&&((int) x < width + 1))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
            }
            else
            {
                result = false;
            }
        }
        else
        {
            result = false;
        }

        return result;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius*2);
    }
}
