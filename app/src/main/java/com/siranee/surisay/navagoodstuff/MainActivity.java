package com.siranee.surisay.navagoodstuff;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[][] strArray;
    private ListView listView;
    public static int listViewSelectId;
    public static String listViewSelectName,listViewSelectDetail;
    DbSQLite dbSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbSQLite = new DbSQLite(this);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewSelectId = Integer.parseInt(strArray[0][position]);
                listViewSelectName = strArray[1][position];
                listViewSelectDetail = strArray[2][position];
            }
        });
        loadDATA();
    }

    public void  clickMainToView(View view){
        startActivity(new Intent(MainActivity.this,ViewActivity.class));
    }
    public void  clickMainToAdd(View view){
        startActivity(new Intent(MainActivity.this,AddActivity.class));
    }
    public void  clickMainToEdit(View view){
        startActivity(new Intent(MainActivity.this,EditActivity.class));
    }
    public void  clickMainToDelete(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("ต้องการลบี้ใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
                sqLiteDatabase.execSQL("DELETE FROM NGSTable WHERE _id = '"+listViewSelectId+"'");
                loadDATA();
            }
        }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        }).show();
    }

    private void loadDATA(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ngsTable ORDER BY ngsName ASC", null);

        strArray = new String[4][cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String sqlNGSlId = cursor.getString(cursor.getColumnIndex("_id"));
            String sqlNGSName = cursor.getString(cursor.getColumnIndex("ngsName"));
            String sqlNGSDetail = cursor.getString(cursor.getColumnIndex("ngsDetail"));
            String sqlNGSPic = cursor.getString(cursor.getColumnIndex("ngsPic"));
            strArray[0][i] = sqlNGSlId;
            strArray[1][i] = sqlNGSName;
            strArray[2][i] = sqlNGSDetail;
            strArray[3][i] = sqlNGSPic;
            i++;
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strArray[1]);
        listView.setAdapter(itemsAdapter);

    }
}
