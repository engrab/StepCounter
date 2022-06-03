package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {
    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().getWindow().clearFlags(1024);
        return null;
    }
}
