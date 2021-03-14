package com.codeninja98.triviaquestionbank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codeninja98.triviaquestionbank.controller.AppController;
import com.codeninja98.triviaquestionbank.data.AnswerListAsyncResponse;
import com.codeninja98.triviaquestionbank.data.QuestionBank;
import com.codeninja98.triviaquestionbank.model.Question;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

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
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFDE03"));
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

        questionList = new QuestionBank().getQuestions(questionArrayList -> {
            questionTextView.setText(questionArrayList.get(currentQuestionIndex).getQuestion());
            questionCounterTextView.setText(currentQuestionIndex + " / " +questionArrayList.size() );
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                if(currentQuestionIndex>0){
                    currentQuestionIndex = (currentQuestionIndex-1) % questionList.size();
                    updateQuestion();
                }
                break;
            case R.id.next_btn:
                currentQuestionIndex = (currentQuestionIndex+1) % questionList.size();
                updateQuestion();
                break;
            case R.id.true_btn:
                currentQuestionIndex = (currentQuestionIndex+1) % questionList.size();
                checkAnswer(true);
                break;
            case R.id.false_btn:
                currentQuestionIndex = (currentQuestionIndex+1) % questionList.size();
                checkAnswer(false);
                break;
        }
    }

    private void checkAnswer(boolean userAnswer) {
        boolean isAnswerTrue = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId = 0;
        if(userAnswer == isAnswerTrue){
            fadeView();
            Toasty.success(MainActivity.this, "Your answer is correct!", Toast.LENGTH_SHORT, true).show();
        }else{
            shakeAnimation();
            Toasty.error(MainActivity.this, "Your answer is wrong!", Toast.LENGTH_SHORT, true).show();
        }
        updateQuestion();
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getQuestion();
        questionTextView.setText(question);
        questionCounterTextView.setText(currentQuestionIndex + " / " +questionList.size() );
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        CardView cardView = findViewById(R.id.cardView);
        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
                questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
                questionTextView.setTextColor(Color.BLACK);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void fadeView(){
        CardView cardView = findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}