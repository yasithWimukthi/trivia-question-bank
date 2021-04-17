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
    
    public void setState(int index){
        preferences.edit().putInt("last_question_index",index).apply();
    }

    public int getState(){
        return preferences.getInt("last_question_index",0);
    }
}
