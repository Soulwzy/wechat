package com.example.cq.mywechat.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelperPyq extends SQLiteOpenHelper {

    private String ddlCreatePyq = "create table pyqmsg(id integer primary key autoincrement not null,username varchar(40)," +
            "pyqmsg varvhar(500),userimage varchar(150),zancount integer);";


    public MyHelperPyq(Context context){
        super(context,"pyqmsg",null,1);
    }
    public MyHelperPyq(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ddlCreatePyq);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
