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

    public ArrayList<Integer> row = new ArrayList<>();
    public ArrayList<Integer> col = new ArrayList<>();

    public int SPRITE_WIDTH;
    public int SPRITE_HEIGHT;

    Sprite(Bitmap sheet, int frameWidth, int frameHeight){
        SPRITE_WIDTH = frameWidth;
        SPRITE_HEIGHT = frameHeight;

        // load the image, offset to android standards
        spriteSheet = sheet;
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, SPRITE_WIDTH * 4, SPRITE_HEIGHT * 4, false);
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
        Rect source = new Rect(left, top, SPRITE_WIDTH, SPRITE_HEIGHT);
        RectF dest = new RectF(200, 200, 200 + SPRITE_WIDTH, 200 + SPRITE_HEIGHT);

        // draw the current frame
        canvas.drawBitmap(spriteSheet, source, dest, null);
    }

}
