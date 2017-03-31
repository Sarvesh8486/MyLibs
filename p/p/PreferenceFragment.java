package com.example.sarveshtank.securenotes.util;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.sarveshtank.securenotes.R;

/**
 * Created by Sarvesh.Tank on 3/31/2017.
 */

public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
        SharedPreferences pref = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen screen = getPreferenceScreen();
        int count = screen.getPreferenceCount();

        for(int i=0;i<count-1;i++){
            Preference prefs = screen.getPreference(i);
            String value = pref.getString(prefs.getKey(), "");
            setPreferenceSummary(prefs, value);
        }
    }

    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
