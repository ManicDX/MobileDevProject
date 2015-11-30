package com.example.m100266177.mobiledevproject;

/**
 * Created by 100486790 on 30/11/2015.
 */
import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DownloadForecastTask extends AsyncTask<String, Void, String> {
    private ForecastListener listener = null;
    private Exception exception = null;
    private String forecast = null; // variable for the forecast string
    int j = 1; // I used this variable so I could cap it at a 7 day forecast

    public DownloadForecastTask(ForecastListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // parse out the data
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Log.d("SevenDayForecast", "url: " + params[0]);
            URL url = new URL(params[0]);
            Document document = docBuilder.parse(url.openStream());
            document.getDocumentElement().normalize();

            // look for <fcttext> tags aka Weather forecast per day
            NodeList main = document.getElementsByTagName("forecastdays");
            if ((main.getLength() > 0) && (main.item(0).getNodeType() == Node.ELEMENT_NODE)) {
                Element sevenDayForecast = (Element)main.item(0);
                NodeList forecastTags = sevenDayForecast.getElementsByTagName("fcttext");
                forecast = "";
                for (int i = 0; i < forecastTags.getLength(); i++) {
                    if (j <= 7){
                        Node fc = forecastTags.item(i);
                        forecast += "Day " + j + ": " + fc.getTextContent() + "\n";
                        j++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }

        return forecast;
    }

    private String stripTags(String code) {
        return code; // for now
    }

    @Override
    protected void onPostExecute(String result) {
        if (exception != null) {
            exception.printStackTrace();
            return;
        }

        Log.d("SevenDayForecast", "setting forecast: " + forecast);
        listener.showOrigin(forecast);
    }
}

