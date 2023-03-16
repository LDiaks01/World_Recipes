package com.example.worldrecipes.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.worldrecipes.LoginActivity;
import com.example.worldrecipes.R;
import com.example.worldrecipes.manager.UserManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class Profile_fragment extends Fragment {

    private UserManager userManager = UserManager.getInstance();

    private View thisView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisView = inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        updateUIWithUserData();
        setupListeners();

        return thisView;
    }

    private void updateUIWithUserData(){
        if(userManager.isCurrentUserLogged()){
            FirebaseUser user = userManager.getCurrentUser();

            if(user.getPhotoUrl() != null){
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into((ImageView) thisView.findViewById(R.id.profileImg));



            }
            setTextUserData(user);
        }
    }

    private void setProfilePicture(Uri profilePictureUrl){
        ((ImageView) thisView.findViewById(R.id.profileImg)).setImageURI(profilePictureUrl);

    }

    private void setTextUserData(FirebaseUser user){

        //Get email & username from User
        String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : user.getEmail();
        String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : user.getDisplayName();


        //Update views with data

        String nom = username.split("\\s+")[0];
        String prenom = username.split("\\s+").length > 1 ? username.split("\\s+")[1] : nom;

        ((TextView) thisView.findViewById(R.id.username)).setText(username);
        ((EditText) thisView.findViewById(R.id.firstname)).setText(nom);
        ((EditText) thisView.findViewById(R.id.lastname)).setText(prenom);
        ((EditText) thisView.findViewById(R.id.email)).setText(email);


    }

    private void setupListeners(){
        // Sign out button
        Button deconnexion = (Button) thisView.findViewById(R.id.deconnexion);
        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userManager.signOut(getActivity().getApplicationContext())
                        .addOnSuccessListener(
                                aVoid -> {
                                    getActivity().finish();
                                    Intent it = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                                    startActivity(it);
                                    getActivity().finish();

                                }
                        );

            }
        });



        // Delete button
        Button supprimer =  (Button) thisView.findViewById(R.id.delete_account);
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userManager.deleteUser(getActivity().getApplicationContext())
                        .addOnSuccessListener(aVoid -> {
                                    getActivity().finish();
                                    Intent it = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                                    Toast.makeText(getActivity().getApplicationContext(), "Utilisateur Supprimé, veuillez vous reconnecter", Toast.LENGTH_SHORT).show();

                                    startActivity(it);

                                }
                        );
            }
        });

        //modify button
        Button modify  = (Button) thisView.findViewById(R.id.modifier_profil);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView displayname = (TextView) thisView.findViewById(R.id.username);
               EditText nom = (EditText) thisView.findViewById(R.id.firstname);
                EditText prenom = (EditText) thisView.findViewById(R.id.lastname);
                EditText email = (EditText) thisView.findViewById(R.id.email);


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(nom.getText().toString() + " " + prenom.getText().toString())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Utilisateur Modifié", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                user.updateEmail(email.getText().toString());
                displayname.setText(nom.getText().toString() + " " + prenom.getText().toString());

            }

        });

    }






}