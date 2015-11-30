package com.example.m100266177.mobiledevproject;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 100266177 on 25/11/2015.
 */
public class ParticleManager {

    int x, y; //position
    Random rand;

    ArrayList<Particle> particles = new ArrayList<>();

    ParticleManager(){
        rand = new Random();
        x = 500;
        y = 500;
        for(int index = 0; index < 10; index++){
            createParticle();
        }
    }

    public void update(){
        for(int index = 0; index < particles.size(); index++){
            particles.get(index).update();
            if(particles.get(index).isDead()){
                particles.remove(index);
                index--;
                createParticle();
            }
        }
    }

    public void draw(Canvas canvas){
        for(int index = 0; index < particles.size(); index++){
            Particle temp = particles.get(index);

            int alpha = 255;
            float currentLife = (float)particles.get(index).life;
            if(currentLife < 10){
                alpha = (int)SimpleMath.LERP(0f, 255f, currentLife/10f);
            }

            Paint paint = new Paint();
            paint.setARGB(alpha, temp.R, temp.G, temp.B);
            paint.setStyle(Paint.Style.FILL);

            // draw a filled circle
            canvas.drawCircle(temp.x, temp.y, temp.size, paint);

        }
    }

    public void createParticle(){
        int vx = rand.nextInt(20) - 10;
        int vy = rand.nextInt(20) - 10;
        int life = rand.nextInt(30) + 60;
        int colour = rand.nextInt(20) + 200;
        Particle p = new Particle(x, y, vx, vy, life, 0, colour, colour, colour, 15);
        particles.add(p);
    }
}
