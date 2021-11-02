package com.example.colourkids;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreView = findViewById(R.id.scoreView);
        TextView commentView = findViewById(R.id.comment);

        Intent intent = getIntent();
        String scoreValue = intent.getExtras().get("score").toString();
        scoreView.setText(scoreValue);

        ImageView resultImage = findViewById(R.id.resultImage);

        String commentValue = intent.getExtras().get("comment").toString();

        if(commentValue.equalsIgnoreCase("fail")){
            commentView.setText("DON'T BE DISCOURAGED!\nYOU CAN ALWAYS TRY AGAIN!");
            resultImage.setImageResource(R.mipmap.one_star);
        } else if (commentValue.equalsIgnoreCase("good")){
            commentView.setText("GOOD TRY!\nBUT YOU CAN EVEN DO BETTER!");
            resultImage.setImageResource(R.mipmap.two_stars);
        } else if (commentValue.equalsIgnoreCase("excellent")){
            commentView.setText("EXCELLENT!\nYOU ARE A COLOR EXPERT");
            resultImage.setImageResource(R.mipmap.three_stars);
        }

        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
        buttonBackToMain.setOnClickListener(v -> {
            Intent intentToMain = new Intent(ResultActivity.this, TakeExamActivity.class);
            setResult(RESULT_OK, intentToMain);
            finish();
        });

    }
}