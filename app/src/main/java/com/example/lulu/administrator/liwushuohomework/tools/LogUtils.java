package com.example.lulu.administrator.liwushuohomework.tools;

import android.util.Log;

/**
 * Log测试类
 */
public class LogUtils {

    public static final boolean DEBUG = true;
    private static final String TAG = "android";

    public static void log(Class clazz,String msg) {
        if (DEBUG) {
            Log.d(TAG, clazz.getName() + " --> " + msg);
        }
    }
}
