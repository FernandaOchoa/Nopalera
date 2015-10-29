package com.fernanda.nopalera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FragmentoInicio extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private LinearLayoutManager layoutManager;
    FloatingActionButton fab;
    SwatchAdapter swatchAdapter;
    GridView gridView;
    ImageView imageView;
    int numPixels;
    public FragmentoInicio() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        gridView=(GridView)view.findViewById(R.id.grid_view);
        imageView =(ImageView)view.findViewById(R.id.imageView);

        fab.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity().getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PickerFragment pickerFragment = new PickerFragment();
        pickerFragment.show(getFragmentManager(), "dialog");

        ft.commit();

    }

    public void createPalette(Object object) {
        Bitmap bitmap;

        try {
            if (object instanceof Uri) {
                Uri imageUri = (Uri) object;
                Picasso.with(getActivity().getApplicationContext()).load(imageUri).into(imageView);
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(imageStream);
            } else {
                bitmap = (Bitmap) object;
                imageView.setImageBitmap(bitmap);
            }

            //Do this async activity

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    HashMap map = processPalette(palette);
                    swatchAdapter = new SwatchAdapter(getContext(), map.entrySet().toArray());
                    gridView.setAdapter(swatchAdapter);
                }
            });
        } catch (Exception e) {
            Log.e("MainActivity", "error in creating palette");
        }
    }

    HashMap<String, Palette.Swatch> processPalette(Palette p) {
        HashMap<String, Palette.Swatch> map = new HashMap<>();

        if (p.getVibrantSwatch() != null)
            map.put("Vibrant", p.getVibrantSwatch());
        if (p.getDarkVibrantSwatch() != null)
            map.put("DarkVibrant", p.getDarkVibrantSwatch());
        if (p.getLightVibrantSwatch() != null)
            map.put("LightMuted", p.getLightMutedSwatch());

        if(p.getMutedSwatch() != null)
            map.put("Muted",p.getMutedSwatch());
        if (p.getDarkMutedSwatch() !=null)
            map.put("DarkMuted",p.getDarkMutedSwatch());
        if(p.getLightMutedSwatch() != null)
            map.put("LightMuted",p.getLightMutedSwatch());

        return map;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Palette.Swatch swatch = ((Map.Entry<String, Palette.Swatch>) gridView.getItemAtPosition(position)).getValue();

        StringBuilder b = new StringBuilder();
        b.append("Title Text Color: ").append("#" + Integer.toHexString(swatch.getBodyTextColor()).toUpperCase()).append("\n");
        b.append("Population: ").append(swatch.getPopulation());

        Snackbar.make(gridView, b.toString(), Snackbar.LENGTH_LONG).show();
    }


}
