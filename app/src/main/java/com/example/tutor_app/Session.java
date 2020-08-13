package com.example.tutor_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit()
                .putString("username", usename)
                .apply();
    }

    public void setresponse(String response) {
        prefs.edit()
                .putString("response", response)
                .apply();
    }

    public String getusename() {
        String usename = prefs.getString("username", "");
        return usename;
    }

    public String getresponse() {
        String response = prefs.getString("response", "");
        return response;
    }

    public void  remove(){
        prefs.edit().remove("username").apply();
        prefs.edit().remove("response").apply();

    }
}
