package com.small.lib.utils.logger;

public interface FormatStrategy {

    void log(int priority, String tag, String message);
}
