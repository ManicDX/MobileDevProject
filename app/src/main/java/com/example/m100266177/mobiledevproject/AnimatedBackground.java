package com.example.m100266177.mobiledevproject;


import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by 100266177 on 25/11/2015.
 */
public class AnimatedBackground implements GLSurfaceView.Renderer {

    private float[] RGBcolour = {250, 100, 100};

    private int SPEED = 30;

    private Random rand;
    private float dt = 0.0f;

    private float vertices[] = {
            0.0f, 1.0f, 0.0f,   // top middle
            -1.0f, -1.0f, 0.0f, // bottom Left
            1.0f, -1.0f, 0.0f 	 // bottom Right
    };
    private FloatBuffer vertexBuffer;

    @Override
    public void onDrawFrame(GL10 gl) {
        // clear the screen and the depth buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // reset the model/view matrix
        gl.glLoadIdentity();

        // what follows actually draws the scene
        float temp = SimpleMath.LERP(0.0f, 1.2f, dt);
        // move the shape into view
        gl.glTranslatef(temp, -1.2f + temp, -6.0f);
        float angle = SimpleMath.LERP(0, 360, dt);
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);

        gl.glColor4f(temp, temp, 0.0f, 1.0f);

        dt += 0.01;
        if(dt > 1.0f)
            dt = 0.0f;

        // we will specify our face (triangle) in clockwise order
        gl.glFrontFace(GL10.GL_CCW);

        // upload our vertex information to OpenGL ES
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        // tell OpenGL ES it should interpret data as vertices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // draw the triangle, from the vertex array
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertices.length / 3);

        // disable vertex drawing mode
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float aspectRatio = 1.0f;
        if (height != 0) {
            aspectRatio = (float)width / (float)height;
        }

        // configure the viewport to use all available space
        gl.glViewport(0, 0, width, height);

        // initially, we want the identity projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        // configure the perspective projection
        // do we want perspective projection?
        float fieldOfViewAngleX = 45.0f;
        float nearZ = 0.1f;
        float farZ = 100.0f;
        GLU.gluPerspective(gl, fieldOfViewAngleX, aspectRatio, nearZ, farZ);

        // reset the model/view matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // we want smooth shading to be used (won't make much difference for flat shapes we use)
        gl.glShadeModel(GL10.GL_SMOOTH);

        // our background will be set to black
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        // we can clear anything that is farther away than 1.0 units
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        // this determines how OpenGL ES will perform perspective calculations
        // Do we need this?
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        // load our vertices into a float buffer (so they can be accessed from C code)
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void setColour(float R, float G, float B) {
        RGBcolour[0] = R;
        RGBcolour[1] = G;
        RGBcolour[2] = B;
    }



}

