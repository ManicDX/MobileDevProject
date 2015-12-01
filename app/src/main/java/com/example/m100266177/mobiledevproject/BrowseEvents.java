package com.example.m100266177.mobiledevproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BrowseEvents extends Activity {

    private ArrayList<Events> events = null;
    private int EventIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);

        // Creates an instance of the database helper which we will need
        EventDatabaseHelper databaseHelper = new EventDatabaseHelper(this);

        // cleans out the databaseHelper incase there was stuff in it from a previous use
        //databaseHelper.deleteAllEvents();

        // Create Test Products
        Events assignment1 = databaseHelper.createEvent("Computer Networks Due", "November 15th 2015", "200 Simcoe Street Oshawa ON", "A hard project");
        Events assignment2 = databaseHelper.createEvent("Mobile Devices Due","November 30th 2015","200 Simcoe Street Oshawa ON","A Full Application is due");
        Events assignment3 = databaseHelper.createEvent("User Interfaces","November 30th 2015","200 Simcoe Street Oshawa ON","Final Project is due");

        this.events = databaseHelper.getAllEvents();
        this.EventIndex = -1;
        nextEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Implement the data as a new Product
        String eventName = data.getExtras().getString("eventName");
        String eventDate = data.getExtras().getString("eventDate");
        String eventAddress = data.getExtras().getString("eventAddress");
        String eventDesc = data.getExtras().getString("eventDescription");
        EventDatabaseHelper dbHelper = new EventDatabaseHelper(this);
        dbHelper.createEvent(eventName, eventDate, eventAddress, eventDesc);
        this.events = dbHelper.getAllEvents();
    }

    // once the directions button has been clicked it sends the user to the maps portion of the app.
    public void directionsButtonOnClick (View v) {
        SoundManager.getInstance(this).play(0);
        // Once the next button is clicked the next product is displayed
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }
    // displays the next event in the directory when clicked
    public void nextButtonOnClick (View v) {
        SoundManager.getInstance(this).play(0);
        // Once the next button is clicked the next product is displayed
        Button button = (Button) v;
        nextEvent();
    }
    // displays the previous event in the directory when clicked
    public void previousButtonOnClick (View v) {
        SoundManager.getInstance(this).play(0);
        // Once the previous button is clicked the previous product is displayed
        Button button = (Button) v;
        prevEvent();
    }
    // deletes the currently viewed event once its clicked
    public void deleteButtonOnClick (View v) {
        SoundManager.getInstance(this).play(1);
        // once this button is pressed the current entry will be deleted
        Button button = (Button) v;
        EventDatabaseHelper dbHelper = new EventDatabaseHelper(this);
        if(this.events.size() > 1) {
            dbHelper.deleteEvent(this.events.get(this.EventIndex).getId());
            this.events = dbHelper.getAllEvents();
            if (this.EventIndex >= this.events.size()) {
                this.EventIndex--;
            } else {
                this.EventIndex++;
            }
            showEvent(this.events.get(this.EventIndex));
        }else{
            Toast.makeText(getApplicationContext(),
                    "The Database Cannot Be Empty, This Action Was Not Completed",
                    Toast.LENGTH_SHORT).show();
        }
    }
    // changes the cursor position in the array to the position of the next event
    private void nextEvent() {
        this.EventIndex++;

        if (this.EventIndex >= this.events.size()) {
            this.EventIndex--;
        }

        showEvent(this.events.get(this.EventIndex));
    }
    // changes the cursor to the location in the array of the previous event
    private void prevEvent(){
        this.EventIndex--;

        if (this.EventIndex < 0) {
            this.EventIndex++;
        }

        showEvent(this.events.get(this.EventIndex));
    }
    public void deleteEvent(View view){
        EventDatabaseHelper dbHelper = new EventDatabaseHelper(this);
        if(this.events.size() > 1) {
            dbHelper.deleteEvent(this.events.get(this.EventIndex).getId());
            this.events = dbHelper.getAllEvents();
            if (this.EventIndex >= this.events.size()) {
                this.EventIndex--;
            } else {
                this.EventIndex++;
            }
            showEvent(this.events.get(this.EventIndex));
        }else{
            Toast.makeText(getApplicationContext(),
                    "Cannot delete, Database must have at least one value",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // sets up the view for the user in order to view products in the database
    private void showEvent(Events event) {
        TextView eventNameField = (TextView)findViewById(R.id.eventNameOutput);
        TextView eventAddressField = (TextView)findViewById(R.id.eventAddressOutput);
        TextView eventDateField = (TextView)findViewById(R.id.eventDateOutput);
        TextView eventDescriptionField = (TextView)findViewById(R.id.eventDescriptionOuput);

        eventNameField.setText(event.getEventName());
        eventDateField.setText(event.getEventDate());
        eventAddressField.setText(event.getEventAddress());
        eventDescriptionField.setText(event.getEventDescription());
    }
}
