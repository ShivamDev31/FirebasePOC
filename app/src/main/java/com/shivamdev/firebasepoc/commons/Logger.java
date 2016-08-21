package com.shivamdev.firebasepoc.commons;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Shivam on 15-08-2016.
 */

public class Logger {
    private static final String TAG = Logger.class.getSimpleName();

    public static void log(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void log(String msg) {
        log(TAG, msg);
    }

    public static void logError(String tag, String error) {
        Log.e(tag, error);
    }

    public static void logError(String error) {
        logError(TAG, error);
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void snackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
