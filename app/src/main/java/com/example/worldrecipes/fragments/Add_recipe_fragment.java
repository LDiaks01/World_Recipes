package com.example.worldrecipes.fragments;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.app.Activity;
import android.app.NotificationChannel;
import android.content.Context;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.worldrecipes.MainActivity;
import com.example.worldrecipes.R;


public class Add_recipe_fragment extends Fragment {
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private static final int CAMERA_REQUEST = 1888;
    Button mCaptureBtn;
    ImageView mImageView;
    Uri image_uri;

    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_recipe_fragment, container, false);
        mImageView = rootView.findViewById(R.id.image_view);
        mCaptureBtn = rootView.findViewById(R.id.capture_image_btn);
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*

                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)==
                            PackageManager.PERMISSION_DENIED ||
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                    PackageManager.PERMISSION_DENIED ){

                        String[] permission= {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);}
                    else{
                        openCamera();
                    }

                 */
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });

        Button btn_submit = rootView.findViewById(R.id.btn_submit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mynotif",
                    "mynot", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(intent2);
                Toast.makeText(getActivity().getApplicationContext(), "Votre recette a été envoyée", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);

                Intent launchIntent = new Intent(getActivity(), MainActivity.class);
                launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, launchIntent, PendingIntent.FLAG_IMMUTABLE);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getActivity().getApplicationContext(), "mynotif");
                builder.setContentTitle("Nouvelle recette délicieuse");
                builder.setContentText("Une nouvelle recette a été ajouté, ouvrez l'application afin de la découvrire");
                builder.setSmallIcon(R.drawable.ic_baseline_notifications_none_24);
                builder.setAutoCancel(true);

                builder.setContentIntent(pendingIntent); // Ajouter le PendingIntent

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(
                        getActivity().getApplicationContext());
                managerCompat.notify(1, builder.build());


            }
        });

        return rootView;

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "from the camera");
        image_uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    /*
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==getActivity().RESULT_OK){
            mImageView.setImageURI(image_uri);
        }
    }
    */


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);
        }
    }

}