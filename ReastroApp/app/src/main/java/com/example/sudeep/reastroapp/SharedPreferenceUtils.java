package com.example.sudeep.reastroapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {
    private static String PREFERENCE_NAME = "ReastroApp";
    private static SharedPreferenceUtils sharedPreferenceUtils;
    private SharedPreferences sharedPreference;

    private SharedPreferenceUtils(Context context) {
        PREFERENCE_NAME = PREFERENCE_NAME + context.getPackageName();
        this.sharedPreference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtils getInstance() {
        if (sharedPreferenceUtils == null) {
            sharedPreferenceUtils = new SharedPreferenceUtils(MyApp.getContext());
        }
        return sharedPreferenceUtils;
    }
    public void saveString(String key,String Val){
        SharedPreferences.Editor editor=sharedPreference.edit();
        editor.putString(key, Val);
        editor.commit();
    }
    public String getString(String key,String defVal){
        return sharedPreference.getString(key, defVal);
    }
    public String getString(String key){
        return sharedPreference.getString(key,"");
    }
}