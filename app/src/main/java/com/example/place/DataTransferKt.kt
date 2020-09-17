package com.example.place

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.util.ArrayList

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
                "LearningTime" to learning_time.toList(),
                "Q_Number" to q_number.toList(),
                "KnownWords" to known_words.toList(),
                "MistakeWords" to mistakes_words.toList(),
                "ConfidenceData" to confidence_data.toList(),
                "label" to metaData.labelData,
                "otherData" to metaData.otherData,
                "questionPattern" to metaData.quizPattern,
                "uid" to user?.uid,
                "device" to Build.MODEL,
                "sensingDataFileName" to metaData.sensingFilePath + metaData.labelData
        )
        // Add a new document with a generated ID
        db.collection("results")
                .add(result)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }
    
}