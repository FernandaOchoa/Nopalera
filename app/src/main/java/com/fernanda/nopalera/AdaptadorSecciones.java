package com.fernanda.nopalera;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monsh on 28/10/2015.
 */

//FragmentStatePager to admin sections, fragments and titles.
public class AdaptadorSecciones extends FragmentStatePagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    private final List<String> titulosFragmentos = new ArrayList<>();

    public AdaptadorSecciones (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        titulosFragmentos.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titulosFragmentos.get(position);
    }
}
