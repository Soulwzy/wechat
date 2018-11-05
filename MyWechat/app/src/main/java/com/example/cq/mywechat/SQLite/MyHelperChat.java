package com.example.cq.mywechat.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelperChat extends SQLiteOpenHelper{

    private String ddlCreateChat="create table chatmsg_a(id integer primary key autoincrement not null," +
            "username varchar(40),msgdate varchar(50),maincontent varchar(500),iscommeg integer)";

    public MyHelperChat(Context context){
        super(context,"chatmsg_a",null,1);
    }

    public MyHelperChat(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ddlCreateChat);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
