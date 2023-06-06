package github.mik0war.hinote.presentation.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import github.mik0war.hinote.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}