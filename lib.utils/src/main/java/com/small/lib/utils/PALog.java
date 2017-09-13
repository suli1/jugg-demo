/*
 * 文  件   名： APPLog.java
 * 版          权： Copyright GoPawPaw All Rights Reserved.
 * 描          述：日志输出类
 * 创  建   人： LiJinHua
 * 创建时间： 2012-2-23
 * 
 * 修   改  人：
 * 修改时间：
 * 修改内容：[修改内容]
 */
package com.small.lib.utils;

import android.support.annotation.Nullable;
import com.small.lib.utils.logger.Logger;
import com.small.lib.utils.logger.logcat.AndroidLogAdapter;

/**
 * 输出日志类
 * @author LiJinHua
 * @version [Android 1.0.0, 2012-3-11]
 */
public class PALog {

	/**
	 * Log标签
	 */
    private static final String TAG = "paem";


	/**
	 * 初始化日志
	 * @author LiJinHua
	 * @modify 2013-12-5  下午8:30:47
	 */
    public static void initLog(boolean isSecurityLog) {
        Logger.t(TAG);
        Logger.clearLogAdapters();
        if (!isSecurityLog) {
            Logger.addLogAdapter(new AndroidLogAdapter());
//            Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder().build())); // 仅INFO级别及以上的日志记录到文件
        }
    }


    public static void d(String tag, @Nullable String message) {
        Logger.log(Logger.DEBUG, tag, message, null);
    }

    public static void d(String message, Object... args) {
        Logger.d(message, args);
    }

    public static void i(String tag, String message) {
        Logger.log(Logger.INFO, tag, message, null);
    }

    public static void i(String message, Object... args) {
        Logger.i(message, args);
    }

    public static void v(String tag, String message) {
        Logger.log(Logger.VERBOSE, tag, message, null);
    }

    public static void v(String message, Object... args) {
        Logger.v(message, args);
    }


    public static void w(String tag, String message) {
        Logger.log(Logger.WARN, tag, message, null);
    }

    public static void w(String message, Object... args) {
        Logger.w(message, args);
    }

    public static void e(String tag, Throwable t, String message) {
        Logger.log(Logger.ERROR, tag, message, t);
    }

    public static void e(String tag, String message) {
        Logger.log(Logger.ERROR, tag, message, null);
    }

    public static void e(Throwable t, String message, Object... args) {
        Logger.e(t, message, args);
    }

    public static void e(String message, Object... args) {
        Logger.e(message, args);
    }

    public static void wtf(String tag, Throwable t, String message) {
        Logger.log(Logger.ASSERT, tag, message, t);
    }

    public static void wtf(String tag, String message) {
        Logger.log(Logger.ASSERT, tag, message, null);
    }

    public static void wtf(String message, Object... args) {
        Logger.wtf(message, args);
    }

    public static void json(String message) {
        Logger.json(message);
    }

}
