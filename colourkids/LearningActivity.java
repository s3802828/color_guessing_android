package com.example.colourkids;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LearningActivity extends AppCompatActivity {
    boolean previousTimerActive = false;
    int i = 0;
    int colorIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        TextView colorImage = findViewById(R.id.colorImage);
        TextView colorName = findViewById(R.id.colorName);

        ArrayList<ColorCard> colorCardArrayList = new ArrayList<>();
        ColorCard colorCard1 = new ColorCard(Color.BLACK, "Black");
        ColorCard colorCard2 = new ColorCard(Color.YELLOW, "Yellow");
        ColorCard colorCard3 = new ColorCard(Color.GRAY, "Gray");
        ColorCard colorCard4 = new ColorCard(Color.GREEN, "Green");
        ColorCard colorCard5 = new ColorCard(Color.RED, "Red");
        colorCardArrayList.add(colorCard1);
        colorCardArrayList.add(colorCard2);
        colorCardArrayList.add(colorCard3);
        colorCardArrayList.add(colorCard4);
        colorCardArrayList.add(colorCard5);

        CountDownTimer countDownTimer = new CountDownTimer(75000, 1000) {
            public void onTick(long millisUntilFinished) {
                i++;
                if(i % 15 == 0){
                    colorImage.setBackgroundColor(colorCardArrayList.get(colorIndex).getColorImage());
                    colorName.setText(colorCardArrayList.get(colorIndex).getColorName());
                    colorIndex++;
                }
                Toast.makeText(LearningActivity.this, "seconds remaining: " + ((millisUntilFinished / 1000) % 15), Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                previousTimerActive = false;
                Toast.makeText(LearningActivity.this, "Done!", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }
}