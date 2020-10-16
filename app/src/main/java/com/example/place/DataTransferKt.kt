package com.example.place

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class DataTransferKt {
    private val TAG = "DataTransfer"
    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()

    private val user = Firebase.auth.currentUser
    private val metaData = MetaData.getInstance()



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


    //Word data input to firestore
    fun test(data : Array<Array<String>>){
        Log.d(TAG,"test"+data.toList()[1][1])
        // Add a new document with a generated ID


        for(wordItem in data.toList() ){
            db.collection("vocabulary")
                    .document(wordItem[0])
                    .set(hashMapOf(
                            "Number" to wordItem[0],
                            "EnglishWords" to wordItem[1],
                            "Japanese" to wordItem[2],
                            "Part of speech" to wordItem[3],
                            "Memorize" to "0"))
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }

        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun SendResultData(learning_time: LongArray, confidence_data: IntArray, known_words: ArrayList<Int>, mistakes_words: ArrayList<Int>, q_number: IntArray){
        Log.d(TAG,"sent result test data")
        val result  = hashMapOf(
                "learningTime" to learning_time.toList(),
                "q_Number" to q_number.toList(),
                "knownWords" to known_words.toList(),
                "mistakeWords" to mistakes_words.toList(),
                "confidenceData" to confidence_data.toList(),
                "Label" to metaData.labelData,
                "OtherData" to metaData.otherData,
                "QuestionPattern" to metaData.quizPattern,
                "Uid" to user?.uid,
                "Device" to Build.MODEL,
                "SensingDataFileName" to metaData.sensingFilePath + metaData.labelData
        )
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        // Add a new document with a generated ID
        db.collection("androidResults").document(user!!.uid).collection("data").document(timeStamp)
                .set(result)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${timeStamp}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }

    fun getUid() : FirebaseUser?{
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        return user
    }
}