package github.mik0war.hinote.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import github.mik0war.hinote.R

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {

    private val navController by lazy {
            (supportFragmentManager
                .findFragmentById(
                    R.id.nav_host_fragment_content_main
                ) as NavHostFragment)
                .navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appBar = findViewById<Toolbar>(R.id.appBar)
        setSupportActionBar(appBar)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.SettingsFragment) {
                appBar.visibility = View.GONE
            } else {
                appBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) = true.also {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.action_settings -> {
            val currentFragment = navController.currentDestination

            navController
                .navigate(if (currentFragment?.id == R.id.NotesListFragment)
                    R.id.action_NotesListFragment_to_SettingsFragment
                else R.id.action_CreateNoteFragment_to_SettingsFragment)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
