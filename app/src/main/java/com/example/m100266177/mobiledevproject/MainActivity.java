package com.example.m100266177.mobiledevproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private GLSurfaceView glSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AnimatedBackground animated = new AnimatedBackground(this);
        animated.setColour(250,250,250);
        //Set the GLSurface as View to this Activity
        setContentView(R.layout.activity_main);
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
    public void browseButtonOnClick (View v) {
        Button button = (Button) v;
        startActivity( new Intent(getApplicationContext(), BrowseEvents.class));
    }
    public void createButtonOnClick (View v) {
        Button button = (Button) v;
        startActivity( new Intent(getApplicationContext(), CreateEvent.class));
    }
}
