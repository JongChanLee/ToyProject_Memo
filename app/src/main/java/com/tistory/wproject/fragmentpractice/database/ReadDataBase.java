package com.tistory.wproject.fragmentpractice.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lee on 2016-01-28.
 */
public class ReadDataBase {
    SQLiteDatabase db;

    public ReadDataBase(Context context, String DBname, String tablename) {
        createDatabase(context, DBname);
        createTable(tablename);
    }

    private void createDatabase(Context context, String DBname) {
        db = context.openOrCreateDatabase(
                DBname,
                Context.MODE_PRIVATE,
                null);

    }

    private void createTable(String tablename) {
        if (db != null) {
            db.execSQL("CREATE TABLE if not exists " + tablename + "(" + "_id integer PRIMARY KEY autoincrement, " + " memo text);");
        }
    }

    public void intertMemo(String tablename, String memo) {
        db.execSQL("INSERT INTO " + tablename + " (memo) VALUES " + "('" + memo + "')");
    }

    public String[] getMemo(String tablename) {
        Cursor cursor = db.rawQuery("SELECT memo FROM " + tablename, null);
        int count = cursor.getCount();
        String[] memo = new String[count];
        for ( int i = 0; i < count; i++){
            cursor.moveToNext();
            memo[i] = cursor.getString(0);
        }
        return memo;
    }

    public int[] getId(String tablename){
        Cursor cursor = db.rawQuery("SELECT _id FROM " + tablename, null);
        int count = cursor.getCount();
        int[] id = new int[count];
        for ( int i = 0; i < count; i++){
            cursor.moveToNext();
            id[i] = cursor.getInt(0);
        }
        return id;
    }
}
