package com.siranee.surisay.navagoodstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {


    ///
    private EditText edtName, edtDetail;;
    private String strName, strDetail;
    private DbSQLite dbSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtName = (EditText) findViewById(R.id.editTextName);
        edtDetail = (EditText) findViewById(R.id.editTextDetail);
    }



    public void  clickAddToMain(View view){
        startActivity(new Intent(AddActivity.this,MainActivity.class));
    }

    public void  clickAddToClear(View view){
        clickReset();
    }

    public void  clickAddToData(View view){
        strName = edtName.getText().toString().trim();
        strDetail = edtDetail.getText().toString().trim();

        MyAlert myAlert = new MyAlert();
        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{
            dbSQLite = new DbSQLite(this);
            dbSQLite.addNewData(strName,strDetail,"");
            clickReset();
            startActivity(new Intent(AddActivity.this,MainActivity.class));
        }
    }

    private boolean checkSpace() {
        return strName.equals("") || strDetail.equals("");
    }

    private void clickReset() {
        edtName.setText("");
        edtDetail.setText("");
    }

}
