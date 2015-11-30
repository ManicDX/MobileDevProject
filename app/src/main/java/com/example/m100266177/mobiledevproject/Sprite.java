package com.example.m100266177.mobiledevproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


    public ArrayList<Integer> row = new ArrayList<>();
    public ArrayList<Integer> col = new ArrayList<>();

    //Sprite size on sprite sheet
    public int SPRITE_WIDTH;
    public int SPRITE_HEIGHT;

    Sprite(Bitmap sheet, int frameWidth, int frameHeight){
        SPRITE_WIDTH = frameWidth;
        SPRITE_HEIGHT = frameHeight;

        x = y = 0;

        // load the image, offset to android standards
        spriteSheet = sheet;
        //TODO, remove hardcoded 3*
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, SPRITE_WIDTH * 3, SPRITE_HEIGHT * 3, false);
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
        blackFill.setARGB(0, 0, 0, 0);
        blackFill.setStyle(Paint.Style.FILL);

        // determine the source and destination boundaries
        int left = row.get(currentFrame);
        int top = col.get(currentFrame);
        Rect source = new Rect(left, top, left + SPRITE_WIDTH, top + SPRITE_HEIGHT);
        //TODO replace hardcoded 200 with position vector of sprite, x,y
        RectF dest = new RectF(x, y, x + SPRITE_WIDTH, y + SPRITE_HEIGHT);

        // draw the current frame
        canvas.drawBitmap(spriteSheet, source, dest, null);
    }

}
