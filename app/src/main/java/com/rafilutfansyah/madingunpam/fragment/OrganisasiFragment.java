package com.rafilutfansyah.madingunpam.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafilutfansyah.madingunpam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganisasiFragment extends Fragment {
    public OrganisasiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organisasi, container, false);
        return view;
    }
}
