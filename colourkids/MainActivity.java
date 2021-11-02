package com.example.colourkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ColorCard> colorCardArrayList = ColorCardListGlobal.getColorCardArrayList();
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

        Button buttonStart = findViewById(R.id.buttonStart);
        Button buttonSetting = findViewById(R.id.buttonSetting);
        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LearningActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("colorCardList", colorCardArrayList);
//            intent.putExtras(bundle);
            startActivityForResult(intent,100);
        });

        buttonSetting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("colorCardList", colorCardArrayList);
//            intent.putExtras(bundle);
//            startActivityForResult(intent,200);
            startActivity(intent);
        });
    }
}