package com.example.m100266177.mobiledevproject;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    private GLSurfaceView glSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // our view will be a GLSurfaceView
        glSurfaceView = new GLSurfaceView(this);

        // specify the configuration (8 bits per pixel, 16 bit depth buffer)
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        //Set our own Renderer
        glSurfaceView.setRenderer(new AnimatedBackground());
        //Set the GLSurface as View to this Activity
        setContentView(glSurfaceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Test Button
    public void buttonHandle(View view){

    }
}
