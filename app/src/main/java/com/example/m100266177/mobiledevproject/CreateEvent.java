package com.example.m100266177.mobiledevproject;
/**
 * Created by 100486790
 * This java class/activity is used to create the events. It is utilized as an activity
 * on its own for users to add their own events to the database. And as a class in the MainActivity
 * to also add events to the database. Using it in the MainActivity is useful because we can
 * initialize test events to ensure that our application functions properly.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    // Sends it back to the MainActivity after adding the current entry to the database.
    public void submitButtonOnClick (View v) {
        EventDatabaseHelper dbHelper = new EventDatabaseHelper(this);
        // Once the submit entry button is pressed the results are submitted to the list array.
        Button button = (Button) v;
        EditText eventTitle = (EditText)findViewById(R.id.eventNameInput);
        EditText eventDescription = (EditText)findViewById(R.id.eventDescriptionInput);
        EditText eventAddress = (EditText)findViewById(R.id.eventAddressInput);
        EditText eventDate = (EditText)findViewById(R.id.eventDateInput);

        String title = eventTitle.getText().toString();
        String date = eventDate.getText().toString();
        String address = eventAddress.getText().toString();
        String description = eventDescription.getText().toString();
        dbHelper.createEvent(title, date, address, description);
        setResult(RESULT_OK);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    // sends it back to the MainActivity without adding an event because the use canceled their transaction
    public void cancelButtonOnClick (View v) {
        // Once the cancel button is clicked the screen goes back to the MainActivity
        Button button = (Button) v;
        setResult(RESULT_CANCELED);
    }
    // enables the events to be added into the database
    public void createEvent(View view){
        Intent intent = new Intent(this, BrowseEvents.class);
        EditText eventTitle = (EditText)findViewById(R.id.eventNameInput);
        intent.putExtra("title", eventTitle.getText().toString());
        EditText eventDescription = (EditText)findViewById(R.id.eventDescriptionInput);
        intent.putExtra("description", eventDescription.getText().toString());
        EditText eventAddress = (EditText)findViewById(R.id.eventAddressInput);
        intent.putExtra("address",eventAddress.getText().toString());
        EditText eventDate = (EditText)findViewById(R.id.eventDateInput);
        intent.putExtra("date",eventDate.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
