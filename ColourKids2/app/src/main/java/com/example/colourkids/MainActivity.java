package com.example.colourkids;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int questionTime = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ColorCard> colorCardArrayList = QueryColorCardListGlobal.getColorCardArrayList();
        ArrayList<ColorCard> totalColorChoices = QueryColorCardListGlobal.getTotalColorChoices();
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

        ColorCard colorCard6 = new ColorCard(Color.rgb(128, 0,128), "Purple");
        ColorCard colorCard7 = new ColorCard(Color.BLUE, "Blue");
        ColorCard colorCard8 = new ColorCard(Color.CYAN, "Cyan");
        ColorCard colorCard9 = new ColorCard(Color.WHITE, "White");
        ColorCard colorCard10 = new ColorCard(Color.rgb(255, 140,0), "Orange");
        ColorCard colorCard11 = new ColorCard(Color.rgb(139, 69,19), "Brown");
        ColorCard colorCard12 = new ColorCard(Color.rgb(255, 192,203), "Pink");

        totalColorChoices.add(colorCard1);
        totalColorChoices.add(colorCard2);
        totalColorChoices.add(colorCard3);
        totalColorChoices.add(colorCard4);
        totalColorChoices.add(colorCard5);
        totalColorChoices.add(colorCard6);
        totalColorChoices.add(colorCard7);
        totalColorChoices.add(colorCard8);
        totalColorChoices.add(colorCard9);
        totalColorChoices.add(colorCard10);
        totalColorChoices.add(colorCard11);
        totalColorChoices.add(colorCard12);

        Button buttonStart = findViewById(R.id.buttonStart);
        Button buttonSetting = findViewById(R.id.buttonSetting);
        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LearningActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("colorCardList", colorCardArrayList);
//            intent.putExtras(bundle);
            intent.putExtra("question_time", questionTime);
            startActivityForResult(intent,100);
        });

        buttonSetting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("colorCardList", colorCardArrayList);
//            intent.putExtras(bundle);
            intent.putExtra("question_time", questionTime);
            startActivityForResult(intent,200);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                if(data != null){
                    String questionTimeValue = data.getExtras().get("question_time").toString();
                    questionTime = Integer.parseInt(questionTimeValue);
                }
            }
        }
    }
}