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

    public Sprite sunSprite;
    public Sprite rainSprite;

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

        //TODO replace sunSprite with sunny, rainy, snowy sunSprite from sheet
        sunSprite = new Sprite(sunSpriteheet, 100, 100);
        //TODO hardcoded frame stuff
        sunSprite.addFrame(0,0);  sunSprite.addFrame(100,0);  sunSprite.addFrame(200,0);
        sunSprite.setPosition(200, 200);

        rainSprite = new Sprite(sunSpriteheet, 100, 100);

        rainSprite.addFrame(0,100);  rainSprite.addFrame(100,100);  rainSprite.addFrame(200, 100);
        rainSprite.setPosition(400, 400);
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



        //TODO, draw background Rect

        //TODO update particle positions

        //TODO, draw particles loop

        //draw sunSprite
        sunSprite.drawAnimationFrame(canvas);
        rainSprite.drawAnimationFrame(canvas);

        //increment delta time, nextFrame
        dt += 0.05f;
        if(dt > 1.0f) {
            dt = 0.0f;
            sunSprite.nextFrame();
            rainSprite.nextFrame();
        }



        handler.postDelayed(animationThread, SPEED);
    }
}

