package com.shivamdev.firebasepoc.commons;

import android.app.Application;

/**
 * Created by Shivam on 21-08-2016.
 */

public class FcmApplication extends Application {

    private static FcmApplication instance;

    public static FcmApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
