package com.example.m100266177.mobiledevproject;

/**
 * Created by 100266177 on 25/11/2015.
 */
class Vector2d{
    float x;
    float y;
};

public abstract class Particle {
    int life;
    int lifeSpan;   // -1 for infinite
    Vector2d pos;   //position
    Vector2d vel;   //velocity
    int R,G,B;      //colour in 256 RGB
    float size;


    public void update(){
        pos.x += vel.x;
        pos.y += vel.y;

        if(lifeSpan != -1)
            life -= 1;
    }



    boolean isDead() {

        if (life < 0) {
            return true;
        }
        return false;
    }


}

class Triangle extends Particle {

}

