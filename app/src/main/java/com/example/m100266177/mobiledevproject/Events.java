package com.example.m100266177.mobiledevproject;

/**
 * Created by 100486790 on 28/11/2015.
 * This class will be used to return information about the event
 */
public class Events {
    private long id; // Variable for the Events ID
    private String eventName; // Variable for the events name
    private String eventDescription; // Variable for the event description
    private String eventDate; // Variable for the events date
    private String eventAddress; // variable for the events address

    public Events (String eventName, String eventDate, String eventAddress, String eventDescription){
        this.id = -1;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventAddress = eventAddress;
    }

    // these 5 methods are used to access the products information in order
    // to display them
    public long getId(){return id; }

    public void setId(long id) {this.id = id; }

    public String getEventName() {return eventName; }

    public String getEventDate() {return eventDate; }

    public String getEventAddress() {return eventAddress; }

    public String getEventDescription() {return eventDescription; }


    // These 4 public voids are needed for usage when the user adds an item
    public void setEventDescription (String eventDescription) {this.eventDescription = eventDescription; }

    public void setEventAddress (String eventAddress) {this.eventAddress = eventAddress; }

    public void setEventName(String eventName) {this.eventName = eventName; }

    public void setEventDate (String eventDate) {this.eventDate = eventDate; }
}

