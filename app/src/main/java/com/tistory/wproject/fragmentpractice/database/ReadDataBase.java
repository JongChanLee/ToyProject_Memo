package com.tistory.wproject.fragmentpractice.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Lee on 2016-01-28.
 */
public class ReadDataBase {
    SQLiteDatabase db;

    public ReadDataBase(Context context, String DBname, String tablename) {
        Log.d("memo", "db 객체 초기화");
        createDatabase(context, DBname);
        createTable(tablename);
    }

    private void createDatabase(Context context, String DBname) {
        Log.d("memo","db생성");
        db = context.openOrCreateDatabase(
                DBname,
                Context.MODE_PRIVATE,
                null);

    }

    private void createTable(String tablename) {
        Log.d("memo","table 생성");
        if (db != null) {
            db.execSQL("CREATE TABLE if not exists " + tablename + "(" + "_id integer PRIMARY KEY autoincrement, " + " memo text);");
        }
    }

    public void intertMemo(String tablename, String memo) {
        Log.d("memo","테이블에 데이터 집어넣기");
        db.execSQL("INSERT INTO " + tablename + " (memo) VALUES " + "('" + memo + "')");
    }

    public String[] getMemo(String tablename) {
        Log.d("memo","데이터 가져오기");
        Cursor cursor = db.rawQuery("SELECT memo FROM " + tablename, null);
        int count = cursor.getCount();
        String[] memo = new String[count];
        for ( int i = 0; i < count; i++){
            cursor.moveToNext();
            memo[i] = cursor.getString(0);
            Log.d("memo", memo[i]);
        }
        return memo;
    }
}
