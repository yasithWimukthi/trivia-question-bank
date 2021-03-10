package com.codeninja98.triviaquestionbank.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.codeninja98.triviaquestionbank.controller.AppController;
import com.codeninja98.triviaquestionbank.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.codeninja98.triviaquestionbank.controller.AppController.TAG;

public class QuestionBank {
    List<Question> questionArrayList = new ArrayList<>();
    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements.json";

    public List<Question> getQuestions() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("json stuff", "onResponse: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return null;
    }
}
