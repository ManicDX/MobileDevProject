package com.example.m100266177.mobiledevproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;


/**
 * Created by 100266177 on 25/11/2015.
 */
public class AnimatedBackground extends View {

    private int[] RGBcolour = {200, 200, 200};

    private int SPEED = 30;

    private Handler handler;
    private Random rand;
    private float dt = 0.0f;

    public Sprite background;
    public Sprite title;

    public Sprite sunSprite;
    public Sprite rainSprite;
    public Sprite snowSprite;

    public ParticleManager pManager;

    public AnimatedBackground(Context context) {
        super(context);
        init();
    }

    public AnimatedBackground(Context context, AttributeSet attribs) {
        super(context, attribs);
        init();
    }

    private void init(){
        handler = new Handler();
        rand    = new Random();
        Bitmap sunSpriteheet = BitmapFactory.decodeResource(getResources(), R.drawable.spritest);
        Bitmap BGsheet = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Bitmap titleSheet = BitmapFactory.decodeResource(getResources(), R.drawable.title);

        pManager = new ParticleManager();


        background = new Sprite(BGsheet, 500* 2, 325 * 5, 1);
        background.addFrame(0, 0);
        background.setPosition(0, 0);

        title = new Sprite(titleSheet, 500, 200, 1);
        title.addFrame(0, 0);
        title.setPosition(250, 0);


        //TODO move these to weather forecast
        sunSprite = new Sprite(sunSpriteheet, 100, 100, 3);
        //TODO remove hardcoded frame stuff, replace with array of sprites
        sunSprite.addFrame(0, 0);  sunSprite.addFrame(100, 0);  sunSprite.addFrame(200, 0);
        sunSprite.setPosition(300, 400);
        //sunSprite.setScale(3);

        rainSprite = new Sprite(sunSpriteheet, 100, 100, 3);

        rainSprite.addFrame(0,100);  rainSprite.addFrame(100, 100);  rainSprite.addFrame(200, 100);
        rainSprite.setPosition(400, 400);
        //rainSprite.setScale(3);

        snowSprite = new Sprite(sunSpriteheet, 100, 100, 3);

        snowSprite.addFrame(0,200);  snowSprite.addFrame(100,200);  snowSprite.addFrame(200, 200);
        snowSprite.setPosition(500, 400);
        //rainSprite.setScale(3);
    }

    //Seperate thread for invalidating/redraw
    private Runnable animationThread = new Runnable() {

        @Override
        public void run() {
            // redraw
            invalidate();
        }
    };

    //Set background colour
    public void setColour(int R, int G, int B) {
        RGBcolour[0] = R;
        RGBcolour[1] = G;
        RGBcolour[2] = B;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(RGBcolour[0], RGBcolour[1], RGBcolour[2]);



        //background
        background.drawAnimationFrame(canvas);
        title.drawAnimationFrame(canvas);

        //draw Particles
        pManager.update();
        pManager.draw(canvas);

        //draw sprites
        //TODO replace with loop?
        sunSprite.drawAnimationFrame(canvas);
        rainSprite.drawAnimationFrame(canvas);
        snowSprite.drawAnimationFrame(canvas);

        //increment delta time, nextFrame
        dt += 0.05f;
        if(dt > 1.0f) {
            dt = 0.0f;
            sunSprite.nextFrame();
            rainSprite.nextFrame();
            snowSprite.nextFrame();
        }



        handler.postDelayed(animationThread, SPEED);
    }
}

