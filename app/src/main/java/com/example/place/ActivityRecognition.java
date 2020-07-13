package com.example.place;


import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecognition {
    protected static final String TAG = ActivityRecognition.class.getSimpleName();
    protected Context context;

    public ActivityRecognition(Context content){
        context = content;
    }

    public void startTracking() {
        Intent intent1 = new Intent(context, BackgroundDetectedActivitiesService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent1);
        }else{
            context.startService(intent1);
        }
    }

    public void stopTracking() {
        Intent intent = new Intent(context, BackgroundDetectedActivitiesService.class);
        context.stopService(intent);
    }


    public static String GetUserActivity(int type, int confidence) {
        String label = "UNKNOWN";

        switch (type) {
            case DetectedActivity.IN_VEHICLE: {
                label = "IN_VEHICLE";
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = "ON_BICYCLE";
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = "ON_FOOT";
                break;
            }
            case DetectedActivity.RUNNING: {
                label = "RUNNING";
                break;
            }
            case DetectedActivity.STILL: {
                label = "STILL";
                break;
            }
            case DetectedActivity.TILTING: {
                label = "TILTING";
                break;
            }
            case DetectedActivity.WALKING: {
                label = "WALKING";
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = "UNKNOWN";
                break;
            }
        }
        return label;
    }
}
