package com.example.colourkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;

import android.os.Bundle;

public class LearningActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning1);

        MotionLayout motionLayout = findViewById(R.id.motion_layout);
        MotionScene motionScene = motionLayout.getScene();


        MotionScene.Transition transition = new MotionScene.Transition(1, motionScene, R.id.start, R.id.end);
        transition.setAutoTransition(MotionScene.Transition.AUTO_ANIMATE_TO_END);
        transition.setDuration(1000);

        motionScene.addTransition(transition);
        motionScene.setTransition(transition);
    }
}