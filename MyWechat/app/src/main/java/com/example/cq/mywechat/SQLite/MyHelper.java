package com.example.cq.mywechat.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private String ddlCreateUser="create table userinfo(id integer primary key autoincrement not null," +
            "username varchar(40),password varchar(20),userimage varchar(150))";


    public MyHelper(Context context){
        super(context,"userinfo",null,1);
    }

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ddlCreateUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
