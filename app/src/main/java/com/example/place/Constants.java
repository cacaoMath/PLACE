package com.example.place;

//アクティビティ頻度間隔や信頼度の閾値を定義
public class Constants {
    public static final String BROADCAST_DETECTED_ACTIVITY = "activity_intent";
    static final long DETECTION_INTERVAL_IN_MILLISECONDS = 30 * 1000;
    public static final int CONFIDENCE = 70;
}
