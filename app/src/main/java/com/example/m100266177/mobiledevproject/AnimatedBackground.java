package com.example.m100266177.mobiledevproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;
import java.util.Vector;


/**
 * Created by 100266177 on 25/11/2015.
 */
public class AnimatedBackground extends View {

    private int[] RGBcolour = {250, 100, 100};

    private int SPEED = 30;

    private Handler handler;
    private Random rand;
    private float dt = 0.0f;

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
    }


    private Runnable animationThread = new Runnable() {

        @Override
        public void run() {
            // redraw
            invalidate();
        }
    };

    public void setColour(int R, int G, int B) {
        RGBcolour[0] = R;
        RGBcolour[1] = G;
        RGBcolour[2] = B;
    }

    public int tempLerp(int p0, int p1){
        float step1 = (float)p0 * (1 - dt);
        float step2 = (float)p1 * dt;
        return (int)(step1 + step2);
    }

    // draw a rectangle outline
    int top = 100, left = 200, width = 200, height = 150;
    int top2 = 800, left2 = 600;

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(0, 0, 0);


        //rand.nextInt(255)
        Paint paint = new Paint();
        paint.setARGB(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        paint.setStyle(Paint.Style.FILL); // outlines only

        int top_n = tempLerp(top, top2);
        int left_n = tempLerp(left, left2);
        RectF bounds = new RectF(left_n, top_n, left_n+width, top_n+height);
        canvas.drawRect(bounds, paint);

        //TODO, draw background Rect

        //TODO update particle positions

        //TODO, draw particles loop

        dt += 0.01f;
        if(dt > 1.0f)
            dt = 0.0f;

        handler.postDelayed(animationThread, SPEED);
    }
}

