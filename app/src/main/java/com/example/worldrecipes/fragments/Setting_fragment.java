package com.example.worldrecipes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldrecipes.R;

import java.util.prefs.PreferenceChangeEvent;


public class Setting_fragment extends PreferenceFragmentCompat {

    private SharedPreferences mSharedPreferences;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mSharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("notifications_frequency")) {
                    String frequency = sharedPreferences.getString(key, "1");
                    // Enregistrer la nouvelle valeur dans les SharedPreferences
                    // ...
                } else if (key.equals("notifications_enabled")) {
                    boolean enabled = sharedPreferences.getBoolean(key, true);
                    // Enregistrer la nouvelle valeur dans les SharedPreferences
                    // ...
                }
                // Ajouter d'autres clés en fonction de vos besoins
            }
        });
        // Charger la valeur de la fréquence de notification à partir des SharedPreferences
        String frequency = mSharedPreferences.getString("notifications_frequency", "1");
        // Définir la valeur de la préférence correspondante
        ListPreference notificationsFrequencyPreference = findPreference("notifications_frequency");
        notificationsFrequencyPreference.setValue(frequency);

        // Charger la valeur de l'activation des notifications à partir des SharedPreferences
        boolean notificationsEnabled = mSharedPreferences.getBoolean("notifications_enabled", true);
        // Définir la valeur de la préférence correspondante
        SwitchPreference notificationsEnabledPreference = findPreference("notifications_enabled");
        notificationsEnabledPreference.setChecked(notificationsEnabled);

        // Charger d'autres préférences en fonction de vos besoins

        // Ajouter du code supplémentaire ici si nécessaire
    }
}


