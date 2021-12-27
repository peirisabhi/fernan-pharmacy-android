package com.chathra.fernanpharmacy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "fernan_pharmacy.DB";
    private static final int DB_VERSION = 1;

    public static final String TBL_USER = "user";


    //Cols Post
    public static final String ID = "id";
    public static final String TYPE = "type";



    private static final String USER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TBL_USER +
            "(" + ID + " TEXT NOT NULL, "
            + TYPE + " TEXT);";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
        onCreate(db);
    }

    public void dropTblUser(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
        onCreate(db);
    }
}
