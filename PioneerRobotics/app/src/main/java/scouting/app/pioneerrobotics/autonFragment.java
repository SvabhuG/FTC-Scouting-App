package scouting.app.pioneerrobotics;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;



import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link autonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link autonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class autonFragment extends Fragment {
    public String mParam1;
    public String mParam2;
    public Button auton_skystones_add;
    public Button auton_skystones_minus;
    public Button auton_stones_add;
    public Button auton_stones_minus;
    public Button auton_placing_add;
    public Button auton_placing_minus;
    public static int auton_skystones_val;
    public static int auton_stones_val;
    public static int auton_placing_val;
    public static int autonomousTime;
    public TextView auton_skystones_val_text;
    public TextView auton_stones_val_text;
    public TextView auton_placing_val_text;
    private Chronometer autonFoundationChronometer;
    private Button autonFoundationStart;
    private Button autonFoundationStop;
    private boolean foundationCountdownRunning;
    private long foundationPauseOffset;
    private long autonPauseOffset;
    private Chronometer autonChronometer;
    private Button autonStop;
    private boolean autonChronometerRunning;
    public static int foundationTime;
    public Switch zone, alliance, auton_parking, foundation;
    public static String startSide, allianceSide;
    public static boolean parked, foundationMoved;
    public TextView autonZoneLoadingText, blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View auton_fragment= inflater.inflate(R.layout.fragment_auton, container, false);

        //Initialize timer textview
        autonFoundationChronometer = auton_fragment.findViewById(R.id.foundationChronometer);
        autonChronometer = auton_fragment.findViewById(R.id.autonChronometer);
        autonChronometer.setBase(SystemClock.elapsedRealtime());
        autonChronometer.start();

        autonChronometerRunning = true;

        //Initializing textViews
        autonZoneLoadingText = auton_fragment.findViewById(R.id.auton_zone_text);
        blue = auton_fragment.findViewById(R.id.blue);

        //Initialize buttons
        auton_skystones_add = auton_fragment.findViewById(R.id.auton_skystones_add);
        auton_skystones_minus = auton_fragment.findViewById(R.id.auton_skystones_minus);
        auton_stones_add = auton_fragment.findViewById(R.id.auton_stones_add);
        auton_stones_minus = auton_fragment.findViewById(R.id.auton_stones_minus);
        auton_placing_add = auton_fragment.findViewById(R.id.auton_placing_add);
        auton_placing_minus = auton_fragment.findViewById(R.id.auton_placing_minus);
        auton_skystones_val_text = auton_fragment.findViewById(R.id.auton_skystones_val_text);
        auton_stones_val_text = auton_fragment.findViewById(R.id.auton_stones_val_text);
        auton_placing_val_text = auton_fragment.findViewById(R.id.auton_placing_val_text);
        autonFoundationStart = auton_fragment.findViewById(R.id.autonFoundationStart);
        autonFoundationStop = auton_fragment.findViewById(R.id.autonFoundationStop);
        autonStop = auton_fragment.findViewById(R.id.autonStop);

        //Initialize switches
        zone = auton_fragment.findViewById(R.id.zone);
        alliance = auton_fragment.findViewById(R.id.alliance);
        auton_parking = auton_fragment.findViewById(R.id.auton_parking);
        foundation = auton_fragment.findViewById(R.id.auton_foundation);

        //Buttons listening for clicks
        autonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAutonChronometer(v);
            }
        });

        autonFoundationStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFoundationChronometer(v);
            }
        });

        autonFoundationStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFoundationChronometer(v);
            }
        });

        auton_skystones_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_skystones_val++;
                if (auton_skystones_val>2){
                    auton_skystones_val = 2;
                }
                auton_skystones_val_text.setText(String.valueOf(auton_skystones_val));
            }
        });

        auton_skystones_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_skystones_val--;
                if (auton_skystones_val<0){
                    auton_skystones_val = 0;
                }
                auton_skystones_val_text.setText(String.valueOf(auton_skystones_val));
            }
        });

        auton_stones_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_stones_val++;
                auton_stones_val_text.setText(String.valueOf(auton_stones_val));
            }
        });

        auton_stones_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_stones_val--;
                if (auton_stones_val<0){
                    auton_stones_val = 0;
                }
                auton_stones_val_text.setText(String.valueOf(auton_stones_val));
            }
        });

        auton_placing_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_placing_val++;
                if (auton_placing_val>(auton_skystones_val+auton_stones_val)){
                    auton_placing_val = auton_skystones_val+auton_stones_val;
                }
                auton_placing_val_text.setText(String.valueOf(auton_placing_val));
            }
        });
        auton_placing_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_placing_val--;
                if (auton_placing_val<0){
                    auton_placing_val = 0;
                }
                auton_placing_val_text.setText(String.valueOf(auton_placing_val));
            }
        });

        //Switches looking for changes in state

        zone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        startSide = "Loading";
                        autonZoneLoadingText.setTextColor(Color.rgb(169, 17, 1));
                        zone.setTextColor(Color.parseColor("#000000"));

                } else {
                       startSide = "Building";
                       zone.setTextColor(Color.rgb(169, 17, 1));
                       autonZoneLoadingText.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        alliance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    allianceSide = "Blue";
                    blue.setTextColor(Color.rgb(169, 17, 1));
                    alliance.setTextColor(Color.parseColor("#000000"));
                } else {
                    alliance.setTextColor(Color.rgb(169, 17, 1));
                    blue.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        auton_parking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parked = true;
                } else {
                    parked = false;
                }
            }
        });

        foundation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    foundationMoved = true;
                } else {
                    foundationMoved = false;
                }
            }
        });

        return auton_fragment;

    }

    public void startFoundationChronometer(View v) {
        if (!foundationCountdownRunning) {
            autonFoundationChronometer.setBase(SystemClock.elapsedRealtime()-foundationPauseOffset);
            autonFoundationChronometer.start();
            foundationCountdownRunning = true;
        }
    }

    public void stopFoundationChronometer(View v) {
        if (foundationCountdownRunning){
            autonFoundationChronometer.stop();
            foundationPauseOffset = SystemClock.elapsedRealtime()-autonFoundationChronometer.getBase();
            foundationCountdownRunning = false;
            foundationTime = (int) (SystemClock.elapsedRealtime() - autonFoundationChronometer.getBase())/1000;
            foundation.setChecked(true);
        }
    }

    public void stopAutonChronometer(View v) {
        if (autonChronometerRunning){
            autonChronometer.stop();
            autonChronometerRunning = false;
            autonomousTime = (int) (SystemClock.elapsedRealtime() - autonChronometer.getBase())/1000;
        }
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Inflater inflater = new Inflater();


    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

    public autonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment autonFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static autonFragment newInstance(String param1, String param2) {
        autonFragment fragment = new autonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
