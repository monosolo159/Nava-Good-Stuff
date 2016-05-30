package com.siranee.surisay.navagoodstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    private TextView eName, eDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        eName = (TextView) findViewById(R.id.txtShowTitle);
        eDetail = (TextView) findViewById(R.id.txtShowDetail);

        eName.setText(MainActivity.listViewSelectName);
        eDetail.setText(MainActivity.listViewSelectDetail);
    }
    public void  clickViewToMain(View view){
        startActivity(new Intent(ViewActivity.this,MainActivity.class));
    }
}
