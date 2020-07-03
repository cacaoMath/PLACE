package com.example.place;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AREASQLiteOpenHelper extends SQLiteOpenHelper {
    //AREAは先代アプリ名リスペクト
    protected static final String TAG = AREASQLiteOpenHelper.class.getSimpleName();

    //データベースのバージョン
    private static final int DATABASE_VERSION = 3;

    //データベース情報を変数に格納
    private static final String DATABASE_NAME = "AREA_DB.db";
    private static final String TABLE_NAME1 = "Device_Source";
    private static final String _ID = "_id";
    private static final String TIME = "Time";
    private static final String USER_ID = "User_id";
    private static final String COLUMN_ACTIVITY = "Activity";
    private static final String COLUMN_CONFIDENCE = "Confidence";
    private static final String COLUMN_SCREEN = "Screen_type";
    private static final String COLUMN_FOREGROUND_APP = "Foreground_app";
    private static final String COLUMN_BATTERY = "Battery";
    private static final String COLUMN_CALENDAR = "Calendar";
    private static final String SQL_CREATE_ENTRIES1 = "CREATE TABLE " + TABLE_NAME1 + " (" + _ID + " INTEGER PRIMARY KEY," + USER_ID + " TEXT," + COLUMN_CALENDAR + " TEXT,"+ TIME + " INTEGER, "+ COLUMN_ACTIVITY + " TEXT," + COLUMN_FOREGROUND_APP + " TEXT," +  COLUMN_SCREEN + " TEXT," + COLUMN_BATTERY + " TEXT)";
    private static final String SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;
    private static final String TABLE_NAME2 = "Activity_Type";
    private static final String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + TABLE_NAME2 + " (" + _ID + " INTEGER PRIMARY KEY," + COLUMN_CALENDAR + " TEXT,"   + COLUMN_ACTIVITY + " TEXT," + COLUMN_CONFIDENCE + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
    private static final String USER_NAME = "User_name";
    private static final String PASSWORD = "Password";
    private static final String OBJ_ID = "Obj_id";
    private static final String TABLE_NAME3 = "User_Info";
    private static final String SQL_CREATE_ENTRIES3 = "CREATE TABLE " + TABLE_NAME3 + " (" + OBJ_ID + " TEXT," + USER_NAME + " TEXT,"   + PASSWORD + " TEXT)";
    private static final String SQL_DELETE_ENTRIES3 = "DROP TABLE IF EXISTS " + TABLE_NAME3;

    AREASQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                SQL_CREATE_ENTRIES1
        );
        db.execSQL(
                SQL_CREATE_ENTRIES2
        );
        db.execSQL(
                SQL_CREATE_ENTRIES3
        );
    }

    // 参考：https://sankame.github.io/blog/2017-09-05-android_sqlite_db_upgrade/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                SQL_DELETE_ENTRIES1
        );
        db.execSQL(
                SQL_DELETE_ENTRIES2
        );
        db.execSQL(
                SQL_DELETE_ENTRIES3
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //指定したデータベースのテーブルを全削除するメソッド
    public static void dbdelete(SQLiteDatabase db, String DBName){
        db.delete(DBName, null, null);
    }

    public void makeTable(SQLiteDatabase db){
        db.execSQL(
                SQL_DELETE_ENTRIES3
        );
        db.execSQL(
                SQL_CREATE_ENTRIES3
        );
    }

}
