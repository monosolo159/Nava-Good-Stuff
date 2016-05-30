package com.siranee.surisay.navagoodstuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
    public static final String database_name = "NavaGoodStuff.db";
    private static final int database_version = 1;

    private static final String create_quiz_table = "create table NGSTable("+
            "_id integer primary key, "+
            "ngsName text, "+
            "ngsDetail text, "+
            "ngsPic text);";

    public DbOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_quiz_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
