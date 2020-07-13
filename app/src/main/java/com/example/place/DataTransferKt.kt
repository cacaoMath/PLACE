package com.example.place

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime

class DataTransferKt {
    val TAG = "DataTransfer"
    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    // Create a new user with a first, middle, and last name
    @RequiresApi(Build.VERSION_CODES.O)
    val user = hashMapOf(
            "first" to "Alan",
            "middle" to "Mathison",
            "last" to "Turing",
            "born" to 1912,
            "time" to LocalDateTime.now().nano
    )

    fun test(){
        Log.d(TAG,"this is Kotlin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }
}