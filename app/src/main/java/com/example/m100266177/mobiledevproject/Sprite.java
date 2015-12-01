package com.example.m100266177.mobiledevproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by 100266177 on 29/11/2015.
 */
public class Sprite {
    public Bitmap spriteSheet;
    private int numFrames = -1;
    public int currentFrame = 0;

    //position
    private int x, y;

    private int scale;

    public ArrayList<Integer> row = new ArrayList<>();
    public ArrayList<Integer> col = new ArrayList<>();

    //Sprite size on sprite sheet
    public int SPRITE_WIDTH;
    public int SPRITE_HEIGHT;



    Sprite(Bitmap sheet, int frameWidth, int frameHeight, int scale){
        SPRITE_WIDTH = frameWidth;
        SPRITE_HEIGHT = frameHeight;
        this.scale = scale;

        //init position data
        x = y = 0;

        // load the image, offset to android standards
        spriteSheet = sheet;
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, SPRITE_WIDTH * scale, SPRITE_HEIGHT * scale, false);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }


    //Add an animation frame to the sprite
    public void addFrame(int _row, int _col){
        row.add(_row);
        col.add(_col);
        numFrames++;
    }

    public void nextFrame(){
        currentFrame += 1;
        if(currentFrame > numFrames)
            currentFrame = 0;
    }


    public void drawAnimationFrame(Canvas canvas) {
        // create a brush
        Paint blackFill = new Paint();
        blackFill.setARGB(255, 50, 50, 50);
        blackFill.setStyle(Paint.Style.FILL);

        // determine the source and destination boundaries
        int left = row.get(currentFrame);
        int top = col.get(currentFrame);
        Rect source = new Rect(left, top, left + SPRITE_WIDTH, top + SPRITE_HEIGHT);
        RectF dest = new RectF(x, y, x + SPRITE_WIDTH, y + SPRITE_HEIGHT);

        // draw the current frame
        canvas.drawBitmap(spriteSheet, source, dest, null);
    }

}
