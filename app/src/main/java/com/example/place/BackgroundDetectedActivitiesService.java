package com.example.place;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class BackgroundDetectedActivitiesService extends Service {
    private static final String TAG = BackgroundDetectedActivitiesService.class.getSimpleName();
    private Intent mIntentService;
    private PendingIntent mPendingIntent;
    private ActivityRecognitionClient mActivityRecognitionClient;
    private String channelId = "AIservice";
    private String title = "AIService";

    IBinder mBinder = new BackgroundDetectedActivitiesService.LocalBinder();

    public class LocalBinder extends Binder {
        public BackgroundDetectedActivitiesService getServerInstance() {
            return BackgroundDetectedActivitiesService.this;
        }
    }
    //最初だけ呼ばれる初期化のみ
    @Override
    public void onCreate() {
        super.onCreate();
        mActivityRecognitionClient = new ActivityRecognitionClient(this);
        mIntentService = new Intent(this, DetectedActivitiesIntentService.class);
        mPendingIntent = PendingIntent.getService(this, 1, mIntentService, PendingIntent.FLAG_UPDATE_CURRENT);
        requestActivityUpdatesButtonHandler();
    }

    //bindで呼ぶことないから多分要らない(笑)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //サービスで行いたい動作を明記
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //通知設定
            android.app.NotificationManager notificationManager = (android.app.NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_LOW);
            channel.setSound(null, null);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                Notification notification = new Notification.Builder(getApplicationContext(), channelId)
                        .setContentTitle(title)
                        .setContentText("AI用データ取得中")
                        .build();

                startForeground(1, notification);
            }
        }
        return START_STICKY;
    }

    public void requestActivityUpdatesButtonHandler() {
        Task<Void> task = mActivityRecognitionClient.requestActivityUpdates(
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                mPendingIntent);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(getApplicationContext(),
                        "Successfully requested activity updates",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Requesting activity updates failed to start",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void removeActivityUpdatesButtonHandler() {
        Task<Void> task = mActivityRecognitionClient.removeActivityUpdates(
                mPendingIntent);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(getApplicationContext(),
                        "Removed activity updates successfully!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to remove activity updates!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeActivityUpdatesButtonHandler();
    }
}
