package com.example.m100266177.mobiledevproject;

/**
 * Created by 100266177 on 25/11/2015.
 */
class Vector2d{
    float x;
    float y;
};

public class Particle {
    int life;
    int lifeSpan;           // -1 for infinite
    public int x,y;         //position
    public int v_x, v_y;   //velocity
    public int R,G,B;      //colour in 256 RGB
    public int size;

    Particle(int x, int y, int vx, int vy, int life, int lifeSpan, int R, int G, int B, int size){
        this.x = x;
        this.y = y;
        this.life = life;
        this.lifeSpan = lifeSpan;
        v_x = vx; v_y = vy;
        this.R = R; this.G = G; this.B = B;
        this.size = size;
    }

    public void update(){
        x += v_x;
        y += v_y;

        if(lifeSpan != -1)
            life -= 1;
    }



    boolean isDead() {

        if (life == 0) {
            return true;
        }
        return false;
    }


}


