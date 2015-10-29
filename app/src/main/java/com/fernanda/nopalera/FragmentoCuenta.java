package com.fernanda.nopalera;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by monsh on 28/10/2015.
 */
//Fragment of section Mi Cuenta
public class FragmentoCuenta extends Fragment {
    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;

    public FragmentoCuenta(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado,container,false);
        if (savedInstanceState == null){
            insertarTabs(container);

            //Set ViewPager
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestañas.setupWithViewPager(viewPager);
        }

        return view;
    }


    private void insertarTabs(ViewGroup container){
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestañas = new TabLayout(getActivity());
        pestañas.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBar.addView(pestañas);
    }

    private void poblarViewPager(ViewPager viewPager){
    AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());

        adapter.addFragment(new FragmentoPlanta(),getString(R.string.titulo_tab_plantas));

        adapter.addFragment(new FragmentoPlagas(),getString(R.string.titulo_tab_plagas));

        adapter.addFragment(new FragmentoReport(),getString(R.string.titulo_tab_report));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
            super.onDestroyView();
        appBar.removeView(pestañas);
    }

}
