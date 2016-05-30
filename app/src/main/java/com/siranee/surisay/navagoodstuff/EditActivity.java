package com.siranee.surisay.navagoodstuff;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText eName, eDetail;
    private String strName, strDetail;
    private DbSQLite dbSQLite;
    private MyAlert myAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        myAlert = new MyAlert();
        eName = (EditText) findViewById(R.id.editTextEditName);
        eDetail = (EditText) findViewById(R.id.editTextEditDetail);
        eName.setText(MainActivity.listViewSelectName);
        eDetail.setText(MainActivity.listViewSelectDetail);
    }

    public void  clickEditToMain(View view){
        startActivity(new Intent(EditActivity.this,MainActivity.class));
    }
    public void  clickEditToClear(View view){
        eName.setText("");
        eDetail.setText("");
    }
    public void  clickEditToAddDB(View view){
        strName = eName.getText().toString().trim();
        strDetail = eDetail.getText().toString().trim();

        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ต้องการแก้ไขใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
                    sqLiteDatabase.execSQL("UPDATE NGSTable SET ngsName='"+strName+"', ngsDetail='"+strDetail+"', ngsPic='' WHERE _id='"+MainActivity.listViewSelectId+"'");
                    startActivity(new Intent(EditActivity.this,MainActivity.class));
                }
            }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            }).show();
        }
    }

    private boolean checkSpace() {
        return strName.equals("")||strDetail.equals("");
    }


}
