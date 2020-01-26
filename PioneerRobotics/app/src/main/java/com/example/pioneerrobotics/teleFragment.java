package com.example.pioneerrobotics;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link teleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link teleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class teleFragment extends Fragment{
    public Button tele_delivered_add;
    public Button tele_delivered_minus;
    public Button tele_placed_add;
    public Button tele_placed_minus, startCycleChronometer, stopCycleChronometer;
    public static EditText tele_height_editText;
    public TextView tele_delivered_val_text;
    public TextView tele_placed_val_text, pushbot;
    public static int tele_delivered_val;
    public static int tele_placed_val;
    public static String autonTelePlaced = Integer.toString(autonFragment.auton_placing_val + tele_placed_val);
    public Chronometer cycleChronometer;
    private long cyclePauseOffset;
    public ArrayList<Integer> cycleTimes = new ArrayList<>();
    public static Switch robotType;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View tele_fragment= inflater.inflate(R.layout.fragment_tele, container, false);
        tele_delivered_add = tele_fragment.findViewById(R.id.tele_delivered_add);
        tele_delivered_minus = tele_fragment.findViewById(R.id.tele_delivered_minus);
        tele_placed_add = tele_fragment.findViewById(R.id.tele_placed_add);
        tele_placed_minus = tele_fragment.findViewById(R.id.tele_placed_minus);
        tele_delivered_val_text = tele_fragment.findViewById(R.id.tele_delivered_val_text);
        tele_placed_val_text = tele_fragment.findViewById(R.id.tele_placed_val_text);
        tele_height_editText = (EditText) tele_fragment.findViewById(R.id.tele_height_editText);
        robotType = tele_fragment.findViewById(R.id.robotType);
        pushbot = tele_fragment.findViewById(R.id.pushbot);

        robotType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pushbot.setTextColor(Color.rgb(169, 17, 1));
                    robotType.setTextColor(Color.parseColor("#000000"));

                } else {

                    robotType.setTextColor(Color.rgb(169, 17, 1));
                    pushbot.setTextColor(Color.parseColor("#000000"));
                }
            }
        });


        tele_delivered_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View t) {
                tele_delivered_val++;
                tele_delivered_val_text.setText(String.valueOf(tele_delivered_val));
            }
        });

        tele_delivered_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tele_delivered_val--;
                if (tele_delivered_val<0){
                    tele_delivered_val = 0;
                }
                tele_delivered_val_text.setText(String.valueOf(tele_delivered_val));
            }
        });

        tele_placed_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tele_placed_val++;
                tele_placed_val_text.setText(String.valueOf(tele_placed_val));
            }
        });

        tele_placed_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tele_placed_val--;
                if (tele_placed_val<0){
                    tele_placed_val = 0;
                }
                tele_placed_val_text.setText(String.valueOf(tele_placed_val));
            }
        });

        tele_height_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!tele_height_editText.getText().toString().isEmpty()) {
                    if (Integer.parseInt(tele_height_editText.getText().toString()) > (autonFragment.auton_placing_val + tele_placed_val)) {
                        CharSequence text = "Skyscraper Height is greater than number of stones placed";
                        int duration = Toast.LENGTH_SHORT;
                        Context context = getActivity().getApplicationContext();
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return tele_fragment;

    }

    public boolean cycleChronometerRunning = false;

    public void startChron(View v){
        if (!cycleChronometerRunning) {
            cycleChronometer.setBase(SystemClock.elapsedRealtime()-cyclePauseOffset);
            cycleChronometer.start();
            cycleChronometerRunning = true;
        }
    }
    
    public void stopChron(View v) {
        if (cycleChronometerRunning){
            cycleChronometer.stop();
            cyclePauseOffset = SystemClock.elapsedRealtime()-cycleChronometer.getBase();
            cycleChronometerRunning = false;
            cycleTimes.add((int) (SystemClock.elapsedRealtime() - cycleChronometer.getBase())/1000);
            Log.i("MyApp",Integer.toString(cycleTimes.get(0)));
        }
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Inflater inflater = new Inflater();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public teleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static teleFragment newInstance(String param1, String param2) {
        teleFragment fragment = new teleFragment();
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
