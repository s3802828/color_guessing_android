package com.example.colourkids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intentResult = getIntent();
        int questionTime = Integer.parseInt(intentResult.getExtras().get("question_time").toString());

        SeekBar timeSeekBar = findViewById(R.id.timeSeekBar);
        TextView eachQuestionTimeValue = findViewById(R.id.eachQuestionTimeValue);
        timeSeekBar.setProgress(questionTime);
        eachQuestionTimeValue.setText(questionTime + "S");


        ArrayList<ColorCard> totalColorChoices = QueryColorCardListGlobal.getTotalColorChoices();
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               eachQuestionTimeValue.setText(timeSeekBar.getProgress() + "S");
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {}

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {}
        }
        );
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.MATCH_PARENT
        );
        TableLayout colorPickerTable = findViewById(R.id.colorPickerTable);
        int[] checkBoxIds = new int[totalColorChoices.size()];
        for (int i = 0; i < totalColorChoices.size(); i = i + 2) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(params);
            colorPickerTable.addView(tableRow);

            CheckBox checkBox = new CheckBox(this);
            ColorCard colorCard = totalColorChoices.get(i);
            checkBox.setText(colorCard.getColorName());
            checkBox.setId(View.generateViewId());
            checkBoxIds[i] = checkBox.getId();
            tableRow.addView(checkBox);
            if(QueryColorCardListGlobal.getColorCardArrayList().contains(colorCard)){
                checkBox.setChecked(true);
            }

            colorCard = totalColorChoices.get(i + 1);
            CheckBox checkBox1 = new CheckBox(this);
            checkBox1.setText(totalColorChoices.get(i + 1).getColorName());
            checkBox1.setId(View.generateViewId());
            checkBoxIds[i + 1] = checkBox1.getId();
            tableRow.addView(checkBox1);
            if(QueryColorCardListGlobal.getColorCardArrayList().contains(colorCard)){
                checkBox1.setChecked(true);
            }
        }
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> {
            ArrayList<ColorCard> queryColorList = QueryColorCardListGlobal.getColorCardArrayList();
            queryColorList.clear();
            for (int i = 0; i < checkBoxIds.length; i++) {
                CheckBox foundCheckBox = findViewById(checkBoxIds[i]);
                if(foundCheckBox.isChecked()){
                    queryColorList.add(totalColorChoices.get(i));
                }
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("question_time", timeSeekBar.getProgress());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}