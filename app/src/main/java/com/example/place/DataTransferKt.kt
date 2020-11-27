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
import kotlin.collections.HashMap

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



    //firebaseに送信するデータをリストにまとめる（追加する）処理
    fun addResultData(learning_time: LongArray, confidence_data: IntArray, known_words: ArrayList<Int>, mistakes_words: ArrayList<Int>, q_number: IntArray){
        val result  = hashMapOf(
                "learningTime" to learning_time.toList(),
                "q_Number" to q_number.toList(),
                "knownWords" to known_words.toList(),
                "mistakeWords" to mistakes_words.toList(),
                "confidenceData" to confidence_data.toList(),
                "Label" to metaData.labelData,
                "OtherData" to metaData.otherData,
                "QuestionPattern" to metaData.quizPattern,
                "User" to user?.email,
                "Device" to Build.MODEL,
                "SensingDataFileName" to metaData.sensingFilePath + metaData.labelData
        )
        metaData.dataList?.add(result)
        Log.d(TAG,"ResultData added to \" dataList \" ${metaData.dataList.size}")
    }

    fun resetDataList(){
        metaData.dataList.clear()
        Log.d(TAG,"canceled ${metaData.dataList.size}")
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun SendResultData(){
        Log.d(TAG,"sent result test data")

        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        for(resultData in metaData.dataList){
            // Add a new document with a generated ID
            db.collection("androidResults").document(user!!.uid)
                    .collection("MeasurementType").document("${timeStamp}_${metaData.labelData}")
                    .collection("Data").document()
                    .set(resultData)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${timeStamp}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
        }
        metaData.dataList.clear()
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