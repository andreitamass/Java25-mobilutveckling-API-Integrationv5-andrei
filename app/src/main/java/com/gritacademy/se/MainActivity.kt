package com.gritacademy.se

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        updateWelcomeShown()

        //Hides title and button, counts where user is to know what to hide
        getSupportFragmentManager().addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener {
            updateWelcomeShown()
        })

        val welcomeButton = findViewById<Button>(R.id.welcomeButton)

        //Replaces fragment container with new
        welcomeButton.setOnClickListener(View.OnClickListener { v: View? ->
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MenuFragment())
                .addToBackStack(null)
                .commit()
        })

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
    }

    //Method to know where user is navigating to either hide or show elements
    private fun updateWelcomeShown() {
        val showWelcome =
            getSupportFragmentManager().getBackStackEntryCount() == 0

        findViewById<View?>(R.id.mainTitle)
            .setVisibility(if (showWelcome) View.VISIBLE else View.GONE)

        findViewById<View?>(R.id.welcomeButton)
            .setVisibility(if (showWelcome) View.VISIBLE else View.GONE)
    }
}