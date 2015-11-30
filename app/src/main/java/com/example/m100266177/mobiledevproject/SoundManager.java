package com.example.m100266177.mobiledevproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 100266177 on 30/11/2015.
 */
public class SoundManager {
    private static SoundManager ourInstance;
    
    public static SoundManager getInstance() {
        return ourInstance;
    }

    public ArrayList<Integer> soundIds = new ArrayList<>();

    SoundPool soundPool;

    @SuppressLint("UseSparseArrays")
    private SoundManager(Context context) {

        //Check version of android
        SoundPool.Builder sp21;
        if((android.os.Build.VERSION.SDK_INT) <= 21){
            sp21 = new SoundPool.Builder();
            sp21.setMaxStreams(5);
            sp21.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());
            soundPool = sp21.build();
        }
        else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }


        loadSounds(context);
    }

    public static SoundManager getInstance(Context context){
        if(ourInstance == null){
            ourInstance = new SoundManager(context);
            Log.d("SPARTA", "Instanciation");
        }
        return ourInstance;
    }

    private void loadSounds(Context context){
        //TODO fix this up
          int strID = soundPool.load(context, R.raw.misc_menu, 1);
          int strID2 = soundPool.load(context, R.raw.negative, 1);
          soundIds.add(strID);
          soundIds.add(strID2);

    }

    public void play(int index){
        int id = soundPool.play(soundIds.get(index), 1f, 1f, 0, 0, 1f);
    }
}
