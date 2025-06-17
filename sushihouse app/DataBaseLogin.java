package com.sushihouse.uassushihouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseLogin extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_LOGIN";
    public DataBaseLogin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session (id integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE user (" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                "email text, " +
                "password text, " +
                "nama text, " +
                "notelepon text)");
        db.execSQL("INSERT INTO session (id, login) VALUES (1, 'kosong')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //check session
    public boolean checksession(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{value});
        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    //upgrade session
    public boolean upgradeSession(String value, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", value);
        long update = db.update("session", values, "id=" +id, null);
        if (update == -1){
            return false;
        }else {
            return true;
        }
    }

    //input user
    public boolean insertuser(String email, String password, String nama, String notelepon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("nama", nama);
        values.put("notelepon", notelepon);

        long insert = db.insert("user", null, values);
        Log.d("REGISTRASI", "Insert Result: " + insert);
        return insert != -1;
    }



    //checklogin
    public boolean checklogin(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

}
