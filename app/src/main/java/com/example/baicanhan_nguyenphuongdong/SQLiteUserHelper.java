package com.example.baicanhan_nguyenphuongdong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baicanhan_nguyenphuongdong.model.User;

public class SQLiteUserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.DB";
    private static final int DATABASE_VERSION = 1;

    public SQLiteUserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user(username text primary key, password text)";
        db.execSQL(sql);
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result =  sqLiteDatabase.insert("user", null, contentValues);
        if(result==-1) return false;
        else{
            return true;
        }
    }
    public boolean checkUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where username = ?", new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean checkUser(String usernamee, String passwordd){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where username = ? and password = ?", new String[]{usernamee, passwordd});
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
