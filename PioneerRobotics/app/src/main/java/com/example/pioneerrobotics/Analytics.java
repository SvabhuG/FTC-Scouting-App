package com.example.pioneerrobotics;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Analytics extends AppCompatActivity {

    AutoCompleteTextView eventText;
    AutoCompleteTextView teamName, teamNumber;
    int count;
    List<String> teamNames = new ArrayList<>();
    List<String> teamNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);
        BarChart chart = findViewById(R.id.barchart);

        eventText = findViewById(R.id.eventEditTextAnalytics);
        teamName = findViewById(R.id.team_name_analytics);
        teamNumber = findViewById(R.id.team_number_analytics);


        eventText.setAdapter(GeneralTeamInfo.eventAdapter);

        teamName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (count == 0){
                        if (!eventText.getText().toString().isEmpty()){
                            for (eventData obj: GeneralTeamInfo.eventsOccurred) {
                                if (eventText.getText().toString().equals(obj.getEvent())){
                                    teamNames.add(obj.getTeamName());
                                    teamNumbers.add(obj.getTeamNumber());
                                }
                                Log.i("teamName", obj.getTeamName());
                            }
                            count = 1;
                        }

                    }
                    ArrayAdapter<String> teamNamesAdapter = new ArrayAdapter<String>(v.getContext(),
                            android.R.layout.simple_list_item_1,teamNames);

                    teamName.setAdapter(teamNamesAdapter);
                } else{
                    if (!teamName.getText().toString().isEmpty()){
                        if(teamNames.contains(teamName.getText().toString())) {
                            teamNumber.setText(teamNumbers.get(teamNames.indexOf(teamName.getText().toString())));
                        }
                    }
                }
            }
        });

        teamNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    if(teamName.getText().toString().isEmpty()){
                        if(count==0){
                            if(!eventText.getText().toString().isEmpty()){
                                for (eventData obj: GeneralTeamInfo.eventsOccurred) {
                                    if (eventText.getText().toString().equals(obj.getEvent())){
                                        teamNames.add(obj.getTeamName());
                                        teamNumbers.add(obj.getTeamNumber());
                                    }
                                    Log.i("teamName", obj.getTeamName());
                                }

                                count = 1;
                            }


                        }
                        ArrayAdapter<String> teamNumbersAdapter = new ArrayAdapter<String>(v.getContext(),
                                android.R.layout.simple_list_item_1,teamNumbers);

                        teamNumber.setAdapter(teamNumbersAdapter);
                    }
                }else {
                    if(!teamNumber.getText().toString().isEmpty()) {
                        if(teamNumbers.contains(teamNumber.getText().toString()))
                            teamName.setText(teamNames.get(teamNumbers.indexOf(teamNumber.getText().toString())));
                    }
                }
            }
        });

        ArrayList<BarEntry> NoOfEmp = new ArrayList<>();

        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        NoOfEmp.add(new BarEntry(1578f, 8));
        NoOfEmp.add(new BarEntry(1695f, 9));

        ArrayList<String> year = new ArrayList<>();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Year");
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(bardataset);
        chart.animateY(500);
        BarData barData = new BarData(dataSets);

        chart.setData(barData);

    }

}

