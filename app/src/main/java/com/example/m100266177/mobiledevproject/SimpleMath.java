package com.example.m100266177.mobiledevproject;

/**
 * Created by 100266177 on 26/11/2015.
 */

//This class is for simple animation math, translations and rotation functions, animations
public class SimpleMath {

    //linear interpolation
    public static float LERP(float p0, float p1, float dt){
        return p0*(1-dt) + p1*dt;
    }

    //simple 2D rotation, f is angle of rotation
    public static float RotateX(float x, float y, float f){
        return x * (float)Math.cos(f) - y * (float)Math.sin(f);
    }
    public static float RotateY(float x, float y, float f){
        return y * (float)Math.cos(f) - x * (float)Math.sin(f);
    }
}
