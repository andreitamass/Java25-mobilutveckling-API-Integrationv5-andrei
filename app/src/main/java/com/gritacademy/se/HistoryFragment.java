package com.gritacademy.se;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        super(R.layout.fragment_history);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView historyText = view.findViewById(R.id.historyText);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("weatherHistory")
                .get()
                .addOnSuccessListener(documents -> {

                    String history = "";

                    for (QueryDocumentSnapshot document : documents) {
                        history += document.getString("city") + "\n";
                        history += document.getDouble("temperature") + " °C\n";
                        history += document.getDouble("wind") + " m/s\n";
                        //history += "---------------------";
                    }

                    historyText.setText(history);

                });




    }
}