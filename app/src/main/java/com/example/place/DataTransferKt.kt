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
    val metaData = MetaData.getInstance()



    // Create a new user with a first, middle, and last name
    @RequiresApi(Build.VERSION_CODES.O)
    val userData = hashMapOf(
            "name" to user?.displayName,
            "email" to user?.email,
            "uid" to user?.uid
    )
    @RequiresApi(Build.VERSION_CODES.O)
    private val resultData = hashMapOf(
            "right" to 10,
            "label" to metaData.labelData,
            "other" to metaData.otherData,
            "uid" to user?.uid,
            "sensingDataFileName" to metaData.sensingFilePath
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


    @RequiresApi(Build.VERSION_CODES.O)
    fun SendResultData(){
        Log.d(TAG,"sent result test data")
        // Add a new document with a generated ID
        db.collection("results")
                .add(resultData)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }

}