package com.gritacademy.se

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class HistoryFragment : Fragment(R.layout.fragment_history) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyText = view.findViewById<TextView>(R.id.historyText)

        //Firebase connection
        val db = FirebaseFirestore.getInstance()

        //Collects Firebase data and displays it in TextView
        db.collection("weatherHistory")
            .get()
            .addOnSuccessListener(OnSuccessListener { documents: QuerySnapshot? ->
                var history = ""
                for (document in documents!!) {
                    history += document.getString("city") + "\n"
                    history += document.getDouble("temperature").toString() + " °C\n"
                    history += document.getDouble("wind").toString() + " m/s\n"
                    //history += "---------------------";
                }
                historyText.setText(history)
            })
    }
}