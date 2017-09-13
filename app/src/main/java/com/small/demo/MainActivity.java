package com.small.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


//import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PALog.i("Welcome to host!");

        findViewById(R.id.btn_go_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Small.openUri("home", MainActivity.this);

            }
        });
    }
}
