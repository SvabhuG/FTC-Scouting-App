package com.example.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class GeneralTeamInfo extends AppCompatActivity {

    Toolbar toolbar,toolbartab;
    Button submit_info;
    public static EditText teamName, teamNumber, event, scorer;


    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_team_info);

        toolbar = (Toolbar)findViewById((R.id.toolbar));
        submit_info = (Button)findViewById(R.id.submit_info);
        teamName = (EditText)findViewById(R.id.team_name);
        teamNumber = (EditText)findViewById(R.id.team_number);
        event = (EditText)findViewById(R.id.event);
        scorer = (EditText)findViewById(R.id.scorer);


        submit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
