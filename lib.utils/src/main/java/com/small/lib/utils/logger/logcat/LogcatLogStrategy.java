package com.small.lib.utils.logger.logcat;

import android.util.Log;

import com.small.lib.utils.logger.LogStrategy;

public class LogcatLogStrategy implements LogStrategy {

    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, tag, message);
    }

}