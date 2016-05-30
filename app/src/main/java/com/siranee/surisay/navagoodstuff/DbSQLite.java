package com.siranee.surisay.navagoodstuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbSQLite {
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String quiz_table = "NGSTable";
    public static final String column_id = "_id";
    public static final String column_book = "ngsName";
    public static final String column_detail = "ngsDetail";
    public static final String column_pic = "ngsPic";

    public DbSQLite(Context context){
        dbOpenHelper = new DbOpenHelper(context);
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public long addNewData(String strBName, String strBDetail, String strPic){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_book,strBName);
        contentValues.put(column_detail,strBDetail);
        contentValues.put(column_pic,strPic);

        return sqLiteDatabase.insert(quiz_table,null,contentValues);
    }
}
