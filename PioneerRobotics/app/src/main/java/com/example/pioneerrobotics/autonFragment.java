package com.example.pioneerrobotics;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public int auton_skystones_val;
    public int auton_stones_val;
    public int auton_placing_val;
    public TextView auton_skystones_val_text;
    public TextView auton_stones_val_text;
    public TextView auton_placing_val_text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View auton_fragment= inflater.inflate(R.layout.fragment_auton, container, false);
        auton_skystones_add = auton_fragment.findViewById(R.id.auton_skystones_add);
        auton_skystones_minus = auton_fragment.findViewById(R.id.auton_skystones_minus);
        auton_stones_add = auton_fragment.findViewById(R.id.auton_stones_add);
        auton_stones_minus = auton_fragment.findViewById(R.id.auton_stones_minus);
        auton_placing_add = auton_fragment.findViewById(R.id.auton_placing_add);
        auton_placing_minus = auton_fragment.findViewById(R.id.auton_placing_minus);
        auton_skystones_val_text = auton_fragment.findViewById(R.id.auton_skystones_val_text);
        auton_stones_val_text = auton_fragment.findViewById(R.id.auton_stones_val_text);
        auton_placing_val_text = auton_fragment.findViewById(R.id.auton_placing_val_text);

        auton_skystones_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_skystones_val++;
                auton_skystones_val_text.setText(String.valueOf(auton_skystones_val));
            }
        });

        auton_skystones_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_skystones_val--;
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
                auton_stones_val_text.setText(String.valueOf(auton_stones_val));
            }
        });

        auton_placing_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_placing_val++;
                auton_placing_val_text.setText(String.valueOf(auton_placing_val));
            }
        });
        auton_placing_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auton_placing_val--;
                auton_placing_val_text.setText(String.valueOf(auton_placing_val));
            }
        });

        return auton_fragment;

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
