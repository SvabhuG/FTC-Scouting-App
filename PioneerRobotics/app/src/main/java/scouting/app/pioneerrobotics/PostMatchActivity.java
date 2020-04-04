package scouting.app.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



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

            }
        });

    }

    private void restartApp() {
        Intent intent = new Intent(this, GeneralTeamInfo.class);
        startActivity(intent);
        autonFragment.auton_skystones_val = 0;
        autonFragment.auton_stones_val = 0;
        autonFragment.auton_placing_val = 0;
        teleFragment.tele_delivered_val = 0;
        teleFragment.tele_placed_val = 0;
        teleFragment.tele_height_editText.setText("0");
        endFragment.end_capstone_val = 0;
        endFragment.firstCapHeight.setText("0");
        endFragment.secondCapHeight.setText("0");
    }


}
