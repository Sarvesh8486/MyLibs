package com.whiteboard.securenotes;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whiteboard.securenotes.fragments.PreferenceFragment;

public class PreferencesActivity extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    PreferenceFragment preferenceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        preferenceFragment = new PreferenceFragment();
        manager.beginTransaction()
                .add(R.id.settings_fragment_frame, preferenceFragment)
                .commit();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void reloadFragment(){
        if(preferenceFragment!=null){
            manager.beginTransaction().remove(preferenceFragment).commit();
        }
        preferenceFragment = new PreferenceFragment();
        manager.beginTransaction()
                .add(R.id.settings_fragment_frame, preferenceFragment)
                .commit();
        }
}
