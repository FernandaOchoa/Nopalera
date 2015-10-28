package com.fernanda.nopalera;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.TextView;


/**
 * Created by monsh on 28/10/2015.
 */
public class ViewFragment extends Fragment{
    //Set title for each section
    public static final String Seccion_Titulo = "numero";

    public static ViewFragment newInstance(String sectionTitle){
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putString(Seccion_Titulo, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }
    public ViewFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment,container,false);
        String title = getArguments().getString(Seccion_Titulo);
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        titulo.setText(title);
        return view;
    }
}
