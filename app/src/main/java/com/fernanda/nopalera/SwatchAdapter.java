package com.fernanda.nopalera;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by monsh on 27/09/2015.
 */
public class SwatchAdapter extends ArrayAdapter {
    //Instance of palette
    Palette palette;

    //My Constructor
    public SwatchAdapter(Context context, Object[] swatches){
        super(context, 0, swatches);
    }

    //Override my view for a customview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Map my image
        Map.Entry<String,Palette.Swatch> entry = (Map.Entry<String, Palette.Swatch>)getItem(position);

        //if my image is null, then inflate my view with my custom parameters
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.swatch_view, parent, false);
        }

        //Brought my views and assign a view to my values
        View colorView = convertView.findViewById(R.id.color_swatch);
        TextView colorTitle = (TextView)convertView.findViewById(R.id.color_title);

        //When I touch give me the value and parse to rgb then parse to string and these string apply to Uppercase
        int colorValue = entry.getValue().getRgb();
        colorView.setBackgroundColor(colorValue);
        colorTitle.setText(entry.getKey()+ " (#"+ Integer.toHexString(entry.getValue().getRgb()).toUpperCase()+")");
        return convertView;
    }
}
