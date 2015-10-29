package com.fernanda.nopalera;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentoInicio extends Fragment {
private LinearLayoutManager layoutManager;
    public FragmentoInicio () {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio,container,false);
        layoutManager = new LinearLayoutManager(getActivity());
        return view;
    }
}
