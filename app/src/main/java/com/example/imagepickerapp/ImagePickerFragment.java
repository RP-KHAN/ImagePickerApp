package com.example.imagepickerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.github.drjacky.imagepicker.ImagePicker;

public class ImagePickerFragment extends Fragment {
    ImageView image;
    Button getButton;
    private final int gallery_Request_Code = 100;
    private final int camera_Request_Code = 200;
    public ImagePickerFragment() {
        // Required empty public constructor
    }
    Context context;
    public ImagePickerFragment(Context context){
        this.context = context;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_picker, container, false);

        image = view.findViewById(R.id.set_image);
        getButton = view.findViewById(R.id.getimage);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,gallery_Request_Code);*/
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,camera_Request_Code);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == gallery_Request_Code){
            Uri uri = data.getData();
            image.setImageURI(uri);
        }else{
            Bitmap img = (Bitmap) data.getExtras().get("data");
//
            image.setImageBitmap(img);
        }

    }
}