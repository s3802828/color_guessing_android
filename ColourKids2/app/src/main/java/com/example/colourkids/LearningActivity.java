package com.example.colourkids;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class LearningActivity extends AppCompatActivity {
    int colorIndex = 0;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        TextView colorImage = findViewById(R.id.colorImage);
        TextView colorName = findViewById(R.id.colorName);
        TextView timeCount = findViewById(R.id.timerExam);
        Button buttonReplay = findViewById(R.id.buttonReplay);
        Button buttonTakeExam = findViewById(R.id.buttonTakeExam);
        Intent intentResult = getIntent();
        int questionTimeValue = Integer.parseInt(intentResult.getExtras().get("question_time").toString());

        ArrayList<ColorCard> colorCardArrayList = QueryColorCardListGlobal.getColorCardArrayList();

        countDownTimer = new CountDownTimer((long) questionTimeValue * colorCardArrayList.size() * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText(((millisUntilFinished / 1000) % questionTimeValue) + "S");
                if(((millisUntilFinished / 1000) + 1) % questionTimeValue == 0) {
                    colorImage.setBackgroundColor(colorCardArrayList.get(colorIndex).getColorImage());
                    colorName.setText(colorCardArrayList.get(colorIndex).getColorName());
                    colorIndex++;
                }
            }

            public void onFinish() {
                colorImage.setVisibility(View.INVISIBLE);
                colorName.setVisibility(View.INVISIBLE);
                buttonReplay.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
        buttonReplay.setOnClickListener(v -> {
            buttonReplay.setVisibility(View.INVISIBLE);
            colorImage.setVisibility(View.VISIBLE);
            colorName.setVisibility(View.VISIBLE);
            colorIndex = 0;
            countDownTimer.start();
        });

        buttonTakeExam.setOnClickListener(v -> {
            countDownTimer.cancel();
            Intent intent = new Intent(LearningActivity.this, TakeExamActivity.class);
            intent.putExtra("question_time", questionTimeValue);
            startActivityForResult(intent, 300);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 300){
            Intent intentToMain = new Intent(this, MainActivity.class);
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