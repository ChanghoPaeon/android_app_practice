package com.example.paeon.practice;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {

    private AppCompatDelegate mDelegate;

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof SwitchPreference) {
                Log.d("onPreferenceChange", preference.getKey() + "  and val : " + ((SwitchPreference) preference).isChecked());


            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        addPreferencesFromResource(R.xml.preferences);

        bindPreferenceSummaryToValue(findPreference("ondevice_mode"));
    }

    private void setupActionBar() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Show the Up button in the action bar.
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }

    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        Log.d("bindPreferenceSummaryToValue", "param: " + preference);
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        Log.d("bindPreferenceSummaryToValue", "context: " +         preference.getContext());

        // Trigger the listener immediately with the preference's
        // current value.
        if (preference instanceof SwitchPreference){

            boolean bVal = PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getBoolean(preference.getKey(), false);
            Log.d("preference is bool type", "param: " + bVal);
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, bVal);
        }else{
            String strKey = PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), "");
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, strKey);
        }
    }


}
