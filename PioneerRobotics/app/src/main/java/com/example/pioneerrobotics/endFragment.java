package com.example.pioneerrobotics;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import java.util.Map;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link endFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link endFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class endFragment extends Fragment {
    private static final String TAG = "endFragment";

    Button end_capstone_add, end_capstone_minus, submit_data;
    TextView end_capstone_val_text;
    public static EditText firstCapHeight, secondCapHeight;
    Switch endFoundation, endParking;
    public static boolean foundationMovedOut, endParked;
    public static int autonScore, teleOpScore, endScore, totalScore, end_capstone_val;
    DatabaseReference database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance().getReference("data");
        // Inflate the layout for this fragment
        final View end_fragment= inflater.inflate(R.layout.fragment_end, container, false);
        end_capstone_add = end_fragment.findViewById(R.id.end_capstone_add);
        end_capstone_minus = end_fragment.findViewById(R.id.end_capstone_minus);
        end_capstone_val_text = end_fragment.findViewById(R.id.end_capstones_val_text);
        firstCapHeight = end_fragment.findViewById(R.id.end_capstone1_height_editText);
        secondCapHeight = end_fragment.findViewById(R.id.end_capstone2_height_editText);
        endFoundation = end_fragment.findViewById((R.id.end_foundation));
        endParking = end_fragment.findViewById(R.id.endParking);

        submit_data = end_fragment.findViewById(R.id.submit_data);

        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addTeam();
                writeNewPost();
                openPostActivity();
            }
        });


        end_capstone_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_capstone_val++;
                end_capstone_val_text.setText(String.valueOf(end_capstone_val));
            }
        });

        end_capstone_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_capstone_val--;
                if (end_capstone_val<0){
                    end_capstone_val = 0;
                }
                end_capstone_val_text.setText(String.valueOf(end_capstone_val));
            }
        });

        endFoundation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    foundationMovedOut = true;
                } else {
                    foundationMovedOut = false;
                }
            }
        });
        endParking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    endParked = true;
                } else {
                    endParked = false;
                }
            }
        });


        return end_fragment;
    }
    private void openPostActivity(){
        Intent myIntent = new Intent(getActivity(), PostMatchActivity.class);
        startActivity(myIntent);
    }


    private void writeNewPost() {

        // General team info
        int teamNumber = Integer.parseInt(GeneralTeamInfo.teamNumberEditText.getText().toString());
        String teamName = GeneralTeamInfo.teamNameEditText.getText().toString();
        String event = GeneralTeamInfo.eventEditText.getText().toString();
        String scorer = GeneralTeamInfo.scorer.getText().toString();
        int round = Integer.parseInt(GeneralTeamInfo.roundEditText.getText().toString());

        //Finalize info data
        DataSubmit info = new DataSubmit(teamName, teamNumber, event, scorer);
        Map<String, Object> infoValues = info.toMap();


        //Autonomous data
        int skystonesDelivered = autonFragment.auton_skystones_val;
        int stonesDelivered = autonFragment.auton_stones_val;
        int stonesPlaced = autonFragment.auton_placing_val;
        boolean foundationMoved = autonFragment.foundationMoved;
        int foundationTime = autonFragment.foundationTime;
        boolean parked = autonFragment.parked;
        String alliance = autonFragment.allianceSide;
        String startSide = autonFragment.startSide;
        int autonomousTime = autonFragment.autonomousTime;

        //Calculate autonomous score
        int autonFoundationPoints = ((foundationMoved) ? 10:0);
        int autonParkingPoints = ((parked == true) ? 5:0);
        autonScore = skystonesDelivered*10 + stonesDelivered*2 + autonFoundationPoints + autonParkingPoints + stonesPlaced*4;

        //Create a hashmap for autonomous values
        AutonomousData autonomous = new AutonomousData(skystonesDelivered,stonesDelivered,stonesPlaced,foundationTime, foundationMoved,parked,alliance,startSide,autonomousTime, autonScore);
        Map<String, Object> autonomousValues = autonomous.toMap();

        //Tele Op data
        int teleStonesDelivered = teleFragment.tele_delivered_val;
        int teleStonesPlaced = teleFragment.tele_placed_val;
        int teleHeight = Integer.parseInt(teleFragment.tele_height_editText.getText().toString());

        //Calculate tele op score
        teleOpScore = teleStonesDelivered + teleStonesPlaced + teleHeight;

        //Create a hashmap for tele op values
        TeleOpData teleOp = new TeleOpData(teleOpScore,teleHeight,teleStonesDelivered,teleStonesPlaced);
        Map<String, Object> teleOpValues = teleOp.toMap();

        //Endgame data
        int capstones = end_capstone_val;
        int capstoneHeight = Integer.parseInt(firstCapHeight.getText().toString());
        int secondCapstoneHeight = Integer.parseInt(secondCapHeight.getText().toString());

        //Calculate endgame score
        int foundationMovedOutPoints = ((foundationMovedOut) ? 15:0);
        int endParkingPoints = ((endParked) ? 5:0);
        endScore = capstones*5 + capstoneHeight + secondCapstoneHeight + foundationMovedOutPoints + endParkingPoints;

        //Create a hashmap for endgame values
        EndgameData endgame = new EndgameData(capstones,capstoneHeight,secondCapstoneHeight, foundationMovedOut, endParked,endScore, totalScore);
        Map<String, Object> endgameValues = endgame.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(teamName + "/" + ("Round " + round) + "/Info/", infoValues);
        childUpdates.put(teamName + "/" + ("Round " + round) + "/Autonomous/", autonomousValues);
        childUpdates.put(teamName + "/" + ("Round " + round) + "/Tele Op/", teleOpValues);
        childUpdates.put(teamName + "/" + ("Round " + round) + "/Endgame/", endgameValues);


        database.updateChildren(childUpdates);
    }

    private void addTeam() {
        String teamNumber = GeneralTeamInfo.teamNumberEditText.getText().toString();
        String teamName = GeneralTeamInfo.teamNameEditText.getText().toString();
        String event = GeneralTeamInfo.eventEditText.getText().toString();
        String scorer = GeneralTeamInfo.scorer.getText().toString();

        if (!TextUtils.isEmpty(String.valueOf(teamNumber))){
            Team team = new Team(teamName, teamNumber, event,scorer);
            database.child(teamNumber).setValue(team);
        }
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public endFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment endFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static endFragment newInstance(String param1, String param2) {
        endFragment fragment = new endFragment();
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
