package com.small.lib.utils.logger;

public interface LogStrategy {

    void log(int priority, String tag, String message);
}
