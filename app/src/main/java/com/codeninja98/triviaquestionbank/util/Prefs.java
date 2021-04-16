package com.codeninja98.triviaquestionbank.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Activity activity) {
        this.preferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void saveHighestScore(int score){
        int currentScore = score;
        int lastScore = preferences.getInt("highest_score",0);

        if(currentScore > lastScore){
            preferences.edit().putInt("highest_score",currentScore).apply();
        }
    }

    public int getHighestScore(){
        return preferences.getInt("highest_score",0);
    }
}
