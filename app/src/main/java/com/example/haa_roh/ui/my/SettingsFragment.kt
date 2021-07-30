package com.example.haa_roh.ui.my

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.haa_roh.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}