package com.small.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.small.lib.utils.PALog;

/**
 * Created by suli690 on 2017/8/22.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        PALog.i("Welcome to plugin [HomeActivity]!");
    }
}
