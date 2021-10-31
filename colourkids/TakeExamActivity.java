package com.example.colourkids;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TakeExamActivity extends AppCompatActivity {
    int colorIndex = 0;
    ColorCard chosenCard= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_exam);

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

        Button buttonNext = findViewById(R.id.buttonNext);
        Button buttonPrevious = findViewById(R.id.buttonPrevious);

        chosenCard = colorCardArrayList.get(0);

        TextView guessColorView = findViewById(R.id.guessColor);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        ViewGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );

        for (int i = 0; i < 3; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(params);
            radioButton.setText("Choice " + (i+1));
            radioGroup.addView(radioButton);
        }
        RadioButton radioButtonCorrect = new RadioButton(this);
        radioButtonCorrect.setLayoutParams(params);
        radioButtonCorrect.setText(chosenCard.getColorName());
        radioGroup.addView(radioButtonCorrect);

        buttonNext.setOnClickListener(v -> {
            colorIndex++;
            if(colorIndex <= colorCardArrayList.size() - 1){
                chosenCard = colorCardArrayList.get(colorIndex);
                guessColorView.setBackgroundColor(chosenCard.getColorImage());
                radioButtonCorrect.setText(chosenCard.getColorName());
            } else {
                colorIndex = colorCardArrayList.size() - 1;
            }
        });

        buttonPrevious.setOnClickListener(v -> {
            colorIndex--;
            if(colorIndex >= 0) {
                chosenCard = colorCardArrayList.get(colorIndex);
                guessColorView.setBackgroundColor(chosenCard.getColorImage());
                radioButtonCorrect.setText(chosenCard.getColorName());
            } else {
                colorIndex = 0;
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == radioButtonCorrect.getId()){
                    Toast.makeText(TakeExamActivity.this, "Correct!!!", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(TakeExamActivity.this, "No!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}