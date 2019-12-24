package com.example.pioneerrobotics;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
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

    Button end_capstone_add;
    Button end_capstone_minus;
    Button submit_data;
    TextView end_capstone_val_text;
    public int end_capstone_val;
    DatabaseReference mDatabase;
    public void basicReadWrite() {

        // [START write_message]

        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");



        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again

                // whenever data at this location is updated.

                String value = dataSnapshot.getValue(String.class);

                Log.d(TAG, "Value is: " + value);

            }


            @Override

            public void onCancelled(DatabaseError error) {

                // Failed to read value

                Log.w(TAG, "Failed to read value.", error.toException());

            }

        });

        // [END read_message]

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDatabase = FirebaseDatabase.getInstance().getReference();
        View end_fragment= inflater.inflate(R.layout.fragment_end, container, false);
        end_capstone_add = end_fragment.findViewById(R.id.end_capstone_add);
        end_capstone_minus = end_fragment.findViewById(R.id.end_capstone_minus);
        end_capstone_val_text = end_fragment.findViewById(R.id.end_capstones_val_text);
        submit_data = end_fragment.findViewById(R.id.submit_data);

/*        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.
            }
        });
*/

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
        return end_fragment;
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
