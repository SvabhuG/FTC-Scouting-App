package scouting.app.pioneerrobotics;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Analytics extends AppCompatActivity {

    AutoCompleteTextView eventText;
    AutoCompleteTextView teamName, teamNumber;
    Button enter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);
        final BarChart mChart = findViewById(R.id.barchart);

        enter = findViewById(R.id.enter_analytics);
        eventText = findViewById(R.id.eventEditTextAnalytics);
        teamName = findViewById(R.id.team_name_analytics);
        teamNumber = findViewById(R.id.team_number_analytics);


        eventText.setAdapter(GeneralTeamInfo.eventAdapter);

        teamName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (GeneralTeamInfo.count == 0){
                        if (!eventText.getText().toString().isEmpty()){
                            for (eventData obj: GeneralTeamInfo.eventsOccurred) {
                                if (eventText.getText().toString().equals(obj.getEvent())){
                                    GeneralTeamInfo.teamNames.add(obj.getTeamName());
                                    GeneralTeamInfo.teamNumbers.add(obj.getTeamNumber());
                                }
                                Log.i("teamName", obj.getTeamName());
                            }
                            GeneralTeamInfo.count = 1;
                        }

                    }
                    
                } else{
                    if (!teamName.getText().toString().isEmpty()){
                        if(GeneralTeamInfo.teamNames.contains(teamName.getText().toString())) {
                            teamNumber.setText(GeneralTeamInfo.teamNumbers.get(GeneralTeamInfo.teamNames.indexOf(teamName.getText().toString())));
                        }
                    }
                }
                ArrayAdapter<String> teamNamesAdapter = new ArrayAdapter<>(v.getContext(),
                        android.R.layout.simple_list_item_1,GeneralTeamInfo.teamNames);

                teamName.setAdapter(teamNamesAdapter);
            }
        });

        teamNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    if(teamName.getText().toString().isEmpty()){
                        if(GeneralTeamInfo.count==0){
                            if(!eventText.getText().toString().isEmpty()){
                                for (eventData obj: GeneralTeamInfo.eventsOccurred) {
                                    if (eventText.getText().toString().equals(obj.getEvent())){
                                        GeneralTeamInfo.teamNames.add(obj.getTeamName());
                                        GeneralTeamInfo.teamNumbers.add(obj.getTeamNumber());
                                    }
                                    Log.i("teamName", obj.getTeamName());
                                }

                                GeneralTeamInfo.count = 1;
                            }


                        }
                    }
                    ArrayAdapter<String> teamNumbersAdapter = new ArrayAdapter<String>(v.getContext(),
                            android.R.layout.simple_list_item_1,GeneralTeamInfo.teamNumbers);

                    teamNumber.setAdapter(teamNumbersAdapter);
                } else {
                    if(!teamNumber.getText().toString().isEmpty()) {
                        if(GeneralTeamInfo.teamNumbers.contains(teamNumber.getText().toString()))
                            teamName.setText(GeneralTeamInfo.teamNames.get(GeneralTeamInfo.teamNumbers.indexOf(teamNumber.getText().toString())));
                    }
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BarEntry> yVals1 = new ArrayList<>();
                int i = 1;
                for (AnalyticsData obj : GeneralTeamInfo.roundsOccurred) {
                    if(teamName.getText().toString().equals(obj.getTeamName())){
                        float val1 = obj.getAutonScore();
                        float val2 = obj.getTeleOpScore();
                        float val3 = obj.getEndScore();
                        yVals1.add(new BarEntry(
                                i,
                                new float[]{val1, val2, val3}));
                    }
                    i++;
                }

                BarDataSet set1;

                if (mChart.getData() != null &&
                        mChart.getData().getDataSetCount() > 0) {
                    set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
                    set1.setValues(yVals1);
                    mChart.getData().notifyDataChanged();
                    mChart.notifyDataSetChanged();
                } else {
                    set1 = new BarDataSet(yVals1, "Scores");
                    set1.setDrawIcons(false);
                    set1.setColors(getColors());
                    set1.setStackLabels(new String[]{"Autonomous", "TeleOp", "Endgame"});

                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);

                    BarData data = new BarData(dataSets);
                    data.setValueFormatter(new MyValueFormatter());
                    data.setValueTextColor(Color.WHITE);
                    mChart.getAxisLeft().setAxisMinimum(0f);
                    mChart.animateXY(500,500);
                    mChart.setData(data);
                }

                mChart.setFitBars(true);
                mChart.invalidate();
            }
        });


    }
    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[3];

        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3);

        return colors;
    }

}

