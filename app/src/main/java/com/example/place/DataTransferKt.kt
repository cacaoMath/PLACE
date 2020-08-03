package com.example.place

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class DataTransferKt {
    val TAG = "DataTransfer"
    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    val user = Firebase.auth.currentUser



    // Create a new user with a first, middle, and last name
    @RequiresApi(Build.VERSION_CODES.O)
    val userData = hashMapOf(
            "name" to user?.displayName,
            "email" to user?.email,
            "uid" to user?.uid
    )

    fun test(){
        Log.d(TAG,"this is Kotlin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        // Add a new document with a generated ID
        db.collection("users")
                .add(userData)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }

}