package com.example.colourkids;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TakeExamActivity extends AppCompatActivity {
    int colorIndex = 0;
    ColorCard chosenCard = null;
    int totalScore = 0;
    CountDownTimer countDownTimer;

    protected String scoreToComment(double score, double total){
        if((score/total) * 100 < 40){
            return "fail";
        } else if ((score/total) * 100 < 70){
            return "good";
        } else{
            return "excellent";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_exam);

        ArrayList<ColorCard> colorCardArrayList = QueryColorCardListGlobal.getColorCardArrayList();

        RelativeLayout relativeLayout = findViewById(R.id.relative_layout_exam);

        Button buttonNext = findViewById(R.id.buttonNext);
        Button buttonPrevious = findViewById(R.id.buttonPrevious);
        Button buttonDone = findViewById(R.id.buttonDone);

        chosenCard = colorCardArrayList.get(0);

        TextView guessColorView = findViewById(R.id.guessColor);
        TextView timeCount = findViewById(R.id.timerExam);

        ViewGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );

        int[] radioGroupIds = new int[colorCardArrayList.size()];
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );
        for (ColorCard card: colorCardArrayList) {
            RadioGroup radioGroup = new RadioGroup(this);
            relativeLayout.addView(radioGroup);
            radioGroup.setId(View.generateViewId());
            radioGroupIds[colorCardArrayList.indexOf(card)] = radioGroup.getId();
            HashSet<String> choiceText = new HashSet<>();
            choiceText.add(card.getColorName());
            while(choiceText.size() != 4){
                int randomInt = new Random().nextInt(QueryColorCardListGlobal.getTotalColorChoices().size());
                choiceText.add(QueryColorCardListGlobal.getTotalColorChoices().get(randomInt).getColorName());
            }
            for (int i = 0; i < 4; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setLayoutParams(params);
                radioButton.setText(choiceText.toArray(new String[4])[i]);
                radioGroup.addView(radioButton);
            }
            params1.addRule(RelativeLayout.BELOW, R.id.guessColor);
            params1.addRule(RelativeLayout.CENTER_IN_PARENT);;
            radioGroup.setLayoutParams(params1);
            radioGroup.setVisibility(View.GONE);
        }
        findViewById(radioGroupIds[0]).setVisibility(View.VISIBLE);
        Intent intent = new Intent(TakeExamActivity.this, ResultActivity.class);
        Intent intentResult = getIntent();
        int questionTimeValue = Integer.parseInt(intentResult.getExtras().get("question_time").toString());

        countDownTimer = new CountDownTimer((long) questionTimeValue * colorCardArrayList.size() * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText((millisUntilFinished / 1000) + "S");
            }
            public void onFinish() {
                intent.putExtra("score", totalScore + "/" + colorCardArrayList.size());
                intent.putExtra("comment", scoreToComment(totalScore, colorCardArrayList.size()));
                startActivityForResult(intent, 400);
            }
        }.start();

        buttonNext.setOnClickListener(v -> {
            colorIndex++;
            if(colorIndex <= colorCardArrayList.size() - 1){
                chosenCard = colorCardArrayList.get(colorIndex);
                guessColorView.setBackgroundColor(chosenCard.getColorImage());
                RadioGroup radioGroup = findViewById(radioGroupIds[colorIndex-1]);
                radioGroup.setVisibility(View.GONE);
                RadioGroup radioGroup2 = findViewById(radioGroupIds[colorIndex]);
                radioGroup2.setVisibility(View.VISIBLE);
            } else {
                colorIndex = colorCardArrayList.size() - 1;
            }
        });

        buttonPrevious.setOnClickListener(v -> {
            colorIndex--;
            if(colorIndex >= 0) {
                chosenCard = colorCardArrayList.get(colorIndex);
                guessColorView.setBackgroundColor(chosenCard.getColorImage());
                RadioGroup radioGroup = findViewById(radioGroupIds[colorIndex+1]);
                radioGroup.setVisibility(View.GONE);
                RadioGroup radioGroup2 = findViewById(radioGroupIds[colorIndex]);
                radioGroup2.setVisibility(View.VISIBLE);
            } else {
                colorIndex = 0;
            }
        });

        buttonDone.setOnClickListener(v -> {
            countDownTimer.cancel();
            intent.putExtra("score", totalScore + "/" + colorCardArrayList.size());
            String comment = scoreToComment(totalScore, colorCardArrayList.size());
            intent.putExtra("comment", comment);
            startActivityForResult(intent, 400);
        });

        for (int radioGroupID :radioGroupIds) {
            RadioGroup radioGroup = findViewById(radioGroupID);
            radioGroup. setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                       boolean alreadyMinus = true;
                                                       boolean alreadyAdd = false;
                                                       @Override
                                                       public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                           RadioButton radioButton = findViewById(checkedId);
                                                           if(radioButton.getText().equals(chosenCard.getColorName())){
                                                               if(!alreadyAdd) {
                                                                   totalScore ++;
                                                                   alreadyAdd = true;
                                                                   alreadyMinus = false;
                                                               }
                                                           } else {
                                                               if(!alreadyMinus) {
                                                                   totalScore--;
                                                                   alreadyMinus = true;
                                                                   alreadyAdd = false;
                                                               }
                                                           }
                                                       }
                                                   }
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 400){
            Intent intentToMain = new Intent(this, LearningActivity.class);
            setResult(RESULT_OK, intentToMain);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}