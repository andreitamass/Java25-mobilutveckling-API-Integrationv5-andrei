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

        Button welcomeButton = findViewById(R.id.welcomeButton);

        welcomeButton.setOnClickListener(v -> {

            findViewById(R.id.mainTitle).setVisibility(View.GONE);
            findViewById(R.id.welcomeButton).setVisibility(View.GONE);

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
}