package com.example.m100266177.mobiledevproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.util.Log;

import java.util.ArrayList;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

/**
 * Created by 100486790 on 28/11/2015.
 * This will be used for the database of events that the user will create
 * or store.
 */

public class EventDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILENAME = "event.db";
    public static final String TABLE_NAME = "event";

    // don't forget to use the column name '_id' for your primary key
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "(" +
            "  _id integer primary key autoincrement, " +
            "  eventName text not null," +
            "  eventDate text not null," +
            "  eventAddress text not null," +
            " eventDescription text not null" +
            ")";
    public static final String DROP_STATEMENT = "DROP TABLE " + TABLE_NAME;

    public EventDatabaseHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // the implementation below is adequate for the first version
        // however, if we change our table at all, we'd need to execute code to move the data
        // to the new table structure, then delete the old tables (renaming the new ones)

        // the current version destroys all existing data
        database.execSQL(DROP_STATEMENT);
        database.execSQL(CREATE_STATEMENT);
    }

    public Events createEvent(String eventName, String eventDate, String eventAddress, String eventDescription) {
        // create the object
        Events event = new Events(eventName, eventDate, eventAddress, eventDescription);

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // insert the data into the database
        ContentValues values = new ContentValues();
        values.put("eventName", event.getEventName());
        values.put("eventDate", event.getEventDate());
        values.put("eventAddress", event.getEventAddress());
        values.put("eventDescription", event.getEventDescription());
        long id = database.insert(TABLE_NAME, null, values);

        // assign the Id of the new database row as the Id of the object
        event.setId(id);

        return event;
    }

    public Events getEvent(long id) {
        Events event = null;

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the event from the database
        String[] columns = new String[] { "_id", "eventName", "eventDate", "eventAddress", "eventDescription" };
        Cursor cursor = database.query(TABLE_NAME, columns, "_id = ?", new String[] { "" + id }, "", "", "", "");
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            String eventName = cursor.getString(0);
            String eventDate = cursor.getString(1);
            String eventAddress = cursor.getString(2);
            String eventDescription = cursor.getString(3);
            event = new Events(eventName, eventDate, eventAddress, eventDescription);
            event.setId(id);
        }

        Log.i("DatabaseAccess", "getEvent(" + id + "):  Event: " + event);
        cursor.close();
        return event;
    }

    public ArrayList<Events> getAllEvents() {
        ArrayList<Events> events = new ArrayList<>();

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the product from the database
        String[] columns = new String[] { "_id", "eventName", "eventDate", "eventAddress", "eventDescription" };
        Cursor cursor = database.query(TABLE_NAME, columns, "", new String[]{}, "", "", "", "");
        cursor.moveToFirst();
        do {
            // collect the product data, and place it into a product object
            long id = Long.parseLong(cursor.getString(0));
            String eventName = cursor.getString(1);
            String eventDate = cursor.getString(2);
            String eventAddress = cursor.getString(3);
            String eventDescription = cursor.getString(4);
            Events event = new Events(eventName, eventDate, eventAddress, eventDescription);
            event.setId(id);

            // add the current product to the list
            events.add(event);

            // advance to the next row in the results
            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("DatabaseAccess", "getAllEvents():  num: " + events.size());
        cursor.close();
        return events;
    }
    public boolean updateEvent(Events event) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // update the data in the database
        ContentValues values = new ContentValues();
        values.put("eventName", event.getEventName());
        values.put("eventDescription", event.getEventDescription());
        values.put("eventDate", event.getEventDate());
        values.put("eventAddress", event.getEventAddress());
        int numRowsAffected = database.update(TABLE_NAME, values, "_id = ?", new String[] { "" + event.getId() });

        Log.i("DatabaseAccess", "updateEvent(" + event + "):  numRowsAffected: " + numRowsAffected);

        // verify that the event was updated successfully
        return (numRowsAffected == 1);
    }

    public boolean deleteEvent(long id) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the product
        int numRowsAffected = database.delete(TABLE_NAME, "_id = ?", new String[] { "" + id });

        Log.i("DatabaseAccess", "deleteEvent(" + id + "):  numRowsAffected: " + numRowsAffected);

        // verify that the product was deleted successfully
        return (numRowsAffected == 1);
    }

    public void deleteAllEvents() {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the products
        int numRowsAffected = database.delete(TABLE_NAME, "", new String[] {});

        Log.i("DatabaseAccess", "deleteAllEvents():  numRowsAffected: " + numRowsAffected);
    }
}

