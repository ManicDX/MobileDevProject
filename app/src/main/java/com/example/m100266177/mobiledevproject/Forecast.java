package com.example.m100266177.mobiledevproject;

/**
 * Made by 100486790
 *
 * This activity is going to be the "MainActivity" for the internet resource. I am creating a seven day forecast
 * by using this api: http://api.wunderground.com/api/92041f548f185ac7/forecast/lang:EN/conditions/q/Canada/(insert_city_here).xml
 * The information will be gathered for the city of the users choice. The user will just have to enter in a major city name.
 * For simplicity sake I only included Canada, I didn't include more countries although it is an expansion that is possible.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Forecast extends Activity implements ForecastListener {

    public static int GET_NAME_REQUEST = 4100001;

    private String name = "android"; // for now
    // URL  needed to get the 7 day forecast.
    private String baseURL = "http://api.wunderground.com/api/92041f548f185ac7/forecast/lang:EN/conditions/q/Canada/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
    }

    public void getForecast(View v) {
        EditText txtName = (EditText)findViewById(R.id.cityName);
        String url = baseURL + txtName.getText().toString().toLowerCase() + ".xml";
        DownloadForecastTask downloadForecastTask = new DownloadForecastTask(this);
        Log.d("InternetResourcesSample", "running task: " + url);
        downloadForecastTask.execute(url);
    }

    // Displays the 7 day forecast in the window * might think about changing it to a listview with an arraylist instead *
    public void showOrigin(String forecast) {
        SoundManager.getInstance(this).play(0);
        EditText cityForecast = (EditText)findViewById(R.id.cityForecast);
        cityForecast.setText(forecast);
    }
}
