package com.gritacademy.se;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        updateWelcomeShown();

        //Hides title and button, counts where user is to know what to hide
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            updateWelcomeShown();
        });

        Button welcomeButton = findViewById(R.id.welcomeButton);

        //Replaces fragment container with new
        welcomeButton.setOnClickListener(v -> {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MenuFragment())
                    .addToBackStack(null)
                    .commit();

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //Method to know where user is navigating to either hide or show elements
    private void updateWelcomeShown() {
        boolean showWelcome =
                getSupportFragmentManager().getBackStackEntryCount() == 0;

        findViewById(R.id.mainTitle)
                .setVisibility(showWelcome ? View.VISIBLE : View.GONE);

        findViewById(R.id.welcomeButton)
                .setVisibility(showWelcome ? View.VISIBLE : View.GONE);
    }
}