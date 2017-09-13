package com.small.home;

import android.app.Application;

import com.small.lib.utils.PALog;

/**
 * Created by suli690 on 2017/8/22.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PALog.initLog(false);
    }
}
