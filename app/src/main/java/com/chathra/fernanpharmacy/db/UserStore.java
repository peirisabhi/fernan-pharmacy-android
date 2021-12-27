package com.chathra.fernanpharmacy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.chathra.fernanpharmacy.model.User;

import java.util.ArrayList;


public class UserStore {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public UserStore(Context context) {
        this.context = context;
    }

    public UserStore open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        dbHelper.close();
    }

    public void drop() {
        dbHelper.dropTblUser(database);
    }


    public void insertUser(User user) {
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.ID, user.getId());
            contentValues.put(DBHelper.TYPE, user.getType());

            database.insert(DBHelper.TBL_USER, null, contentValues);

            database.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            database.endTransaction();
        }
    }


    public ArrayList<User> getUsers(){
        String selectQuery = "SELECT  * FROM " + DBHelper.TBL_USER;
        Cursor cursor = database.rawQuery(selectQuery,null);
        ArrayList<User> users = new ArrayList<>();

        database.beginTransaction();
        try{
            if(cursor.moveToFirst()){
                do{
                    users.add(new User(cursor.getInt(0),cursor.getString(1)));
                }while (cursor.moveToNext());
            }
            database.setTransactionSuccessful();
        }finally {
            database.endTransaction();
        }

        cursor.close();
        return users;
    }

}
