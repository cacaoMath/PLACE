package com.example.place;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class DetectedActivitiesIntentService extends IntentService {
    protected static final String TAG = DetectedActivitiesIntentService.class.getSimpleName();

    public DetectedActivitiesIntentService() {
        // Use the TAG to name the worker thread.
        super(TAG);
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }



    @Override
    public void onHandleIntent(Intent intent){
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        //リスト型で各行動の確信度を取得
        List<DetectedActivity> detectedActivities = result.getProbableActivities();

        for(DetectedActivity activity: detectedActivities){
            //Log.e(TAG, "Detected activity: " + activity.getType() + ", " + activity.getConfidence());
            broadcastActivity(activity);
        }
    }

    private void broadcastActivity(DetectedActivity activity){
        Intent intent = new Intent(Constants.BROADCAST_DETECTED_ACTIVITY);
        intent.putExtra("type", activity.getType());
        intent.putExtra("confidence", activity.getConfidence());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
