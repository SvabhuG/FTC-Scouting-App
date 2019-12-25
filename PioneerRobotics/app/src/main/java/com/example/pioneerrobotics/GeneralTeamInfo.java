package com.example.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class GeneralTeamInfo extends AppCompatActivity {

    Toolbar toolbar,toolbartab;
    Button submit_info;
    public static EditText teamName, teamNumber, event, scorer, roundEditText;
    private Spinner intakeSpinner;

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
        roundEditText = (EditText)findViewById(R.id.roundEditText);
        intakeSpinner = (Spinner)findViewById(R.id.intakeSpinner);

        List<String> mechs = new ArrayList<String>();
        mechs.add("Forklift");
        mechs.add("Intake Wheels");
        mechs.add("Grabbing Arm");
        mechs.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,mechs);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        intakeSpinner.setAdapter(dataAdapter);


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
