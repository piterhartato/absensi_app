package com.example.astimen.absensi.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.astimen.absensi.R;
import com.example.astimen.absensi.abs_masuk_Activity;


public class frg_home extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public frg_home() {
        // Required empty public constructor
    }

    String uidvalue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_frg_home, container, false);

        View rootView = inflater.inflate(R.layout.fragment_frg_home, container, false);

        ImageButton absenmasukBtn = (ImageButton) rootView.findViewById(R.id.absenmasuk);
        ImageButton absenpulangBtn = (ImageButton) rootView.findViewById(R.id.absenkeluar);

        absenmasukBtn.setOnClickListener(this);
        absenpulangBtn.setOnClickListener(this);
        Bundle bundle = this.getArguments();
        uidvalue = bundle.getString("uidvalue");
        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.absenmasuk:
//                fragment = new frg_abs_masuk();
//                replaceFragment(fragment);

                Intent intent = new Intent(getActivity(), abs_masuk_Activity.class);
                intent.putExtra("uidvalue",uidvalue);
                startActivity(intent);

                break;
            case R.id.absenkeluar:
                fragment = new frg_abs_pulang();
                replaceFragment(fragment);
                break;
        }
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}