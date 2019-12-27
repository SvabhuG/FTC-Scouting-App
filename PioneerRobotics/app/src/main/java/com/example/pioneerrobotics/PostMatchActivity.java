package com.example.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class PostMatchActivity extends AppCompatActivity {

    TextView autonScore, teleScore, endScore, totalScore;
    Button score_another_match;


    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_match);

        autonScore = (TextView)findViewById(R.id.autonScore);
        teleScore = (TextView)findViewById(R.id.teleScore);
        endScore = (TextView)findViewById(R.id.endScore);
        totalScore = (TextView)findViewById(R.id.totalScore);
        score_another_match = (Button)findViewById(R.id.score_another_match);

        autonScore.setText(String.valueOf(endFragment.autonScore));
        teleScore.setText(String.valueOf(endFragment.teleOpScore));
        endScore.setText(String.valueOf(endFragment.endScore));

        totalScore.setText(String.valueOf(endFragment.autonScore+endFragment.teleOpScore+endFragment.endScore));

        score_another_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartApp();
                autonFragment.auton_skystones_val = 0;
                autonFragment.auton_stones_val = 0;
                autonFragment.auton_placing_val = 0;

            }
        });

    }

    private void restartApp() {
        Intent intent = new Intent(this, GeneralTeamInfo.class);
        startActivity(intent);
    }


}
