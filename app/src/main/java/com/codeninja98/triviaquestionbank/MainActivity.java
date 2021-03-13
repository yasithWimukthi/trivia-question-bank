package com.codeninja98.triviaquestionbank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codeninja98.triviaquestionbank.controller.AppController;
import com.codeninja98.triviaquestionbank.data.AnswerListAsyncResponse;
import com.codeninja98.triviaquestionbank.data.QuestionBank;
import com.codeninja98.triviaquestionbank.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView questionCounterTextView;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex;
    private List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change app bar color
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#008677"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        nextButton = findViewById(R.id.next_btn);
        prevButton = findViewById(R.id.prev_btn);
        falseButton = findViewById(R.id.false_btn);
        trueButton = findViewById(R.id.true_btn);
        questionTextView = findViewById(R.id.question_textview);
        questionCounterTextView = findViewById(R.id.counterText);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);

        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}