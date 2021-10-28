package com.example.place.activity

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.place.databinding.ActivityConfigBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth

open class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding
    private lateinit var configSaveBtn: Button
    private lateinit var signOutBtn: Button
    private lateinit var measurementTimeEt: EditText
    private lateinit var seeThrowSwitch: SwitchMaterial
    private lateinit var voiceSwitch: SwitchMaterial
    private lateinit var flashCardSwitch: SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)


        configSaveBtn = binding.configSaveBtn
        signOutBtn = binding.signoutBtn
        measurementTimeEt = binding.measurementTimeEt
        seeThrowSwitch = binding.seeThrowSwitch
        voiceSwitch = binding.voiceSwitch
        flashCardSwitch = binding.flashCardSwitch

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        seeThrowSwitch.isChecked = sharedPreferences.getBoolean("seeThrow",false)
        voiceSwitch.isChecked = sharedPreferences.getBoolean("voiceMode",false)
        flashCardSwitch.isChecked = sharedPreferences.getBoolean("flashCardMode",false)

        measurementTimeEt.setText(sharedPreferences.getInt("measurementTime",10).toString())
        configSaveBtn.setOnClickListener(View.OnClickListener {
            val inputTxt = measurementTimeEt.text.toString()
            when {
                inputTxt.contains(":") -> {
                    Toast.makeText(
                        applicationContext, "計測時間は数値だけを入れてください",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i(TAG,"計測時間は数値だけを入れてください")
                }
                TextUtils.isEmpty(inputTxt) -> {
                    sharedPreferences.edit().putInt("measurementTime", 10).apply()
                }
                else -> {
                    sharedPreferences.edit().putInt("measurementTime", inputTxt.toInt()).apply()
                }
            }
            Log.d(TAG, "Saved!!")
            finish()
        })
        signOutBtn.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val titleIntent = Intent(applicationContext, TitleActivity::class.java)
            startActivity(titleIntent)
            finish()
        })

        seeThrowSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("seeThrow", isChecked).apply()
        }

        voiceSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("voiceMode", isChecked).apply()
        }

        flashCardSwitch.setOnCheckedChangeListener{ _, isChecked ->
            sharedPreferences.edit().putBoolean("flashCardMode", isChecked).apply()
        }

    }

    companion object {
        private var TAG = ConfigActivity::class.java.simpleName
    }
}