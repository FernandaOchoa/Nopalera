package com.fernanda.nopalera;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.net.URI;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickerFragment extends DialogFragment {

    private static final String TAG = "PICKER_FRAGMENT";
    private static final int PICK_PHOTO = 100;
    private static final int TAKE_PHOTO = 101;

    @Bind(R.id.pickImage)
    Button pickImage;

    @Bind(R.id.takeImage)
    Button takeImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picker, container,false);
        ButterKnife.bind(this, view);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @OnClick(R.id.pickImage)
    void pickImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_PHOTO);
    }

    @OnClick(R.id.takeImage)
    void takeImage(View view){
        //Snackbar.make(getView(), "I want to take an image.", Snackbar.LENGTH_SHORT).show();

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager())!=null){
            startActivityForResult(cameraIntent,TAKE_PHOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PICK_PHOTO:
                if (resultCode == Activity.RESULT_OK){
                    Log.d(TAG, "Picked a photo.");
                    Uri selectedImage = data.getData();
                    //Put origin (fragment) to apply select and its all
                    ((FragmentoInicio)getContext())).createPalette(selectedImage);
                    getDialog().dismiss();
                }
                break;
            case TAKE_PHOTO:
                if (resultCode== Activity.RESULT_OK){
                    Log.d(TAG,"Took a photo.");
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    //Put origin (fragment) to apply select and its all
                    ((MainActivity)getActivity()).createPalette(imageBitmap);

                    getDialog().dismiss();
                }
        }
    }
}

