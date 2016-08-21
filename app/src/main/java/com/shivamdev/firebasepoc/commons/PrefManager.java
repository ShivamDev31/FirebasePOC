package com.shivamdev.firebasepoc.commons;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Shivam on 21-08-2016.
 */

public class PrefManager {

    private static PrefManager manager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PrefManager() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FcmApplication.getInstance());
        editor = sharedPreferences.edit();
    }

    public static PrefManager getInstance() {
        if (manager == null) {
            manager = new PrefManager();
        }
        return manager;
    }


    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public void remove(String key) {
        editor.remove(key).apply();
    }

    public void clearAll() {
        editor.clear().apply();
    }


}
