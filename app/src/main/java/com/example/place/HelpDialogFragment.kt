package com.example.place

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class HelpDialogFragment : DialogFragment(){
    private val TAG = this::class.java.simpleName
    private val dialogTxt = "HELP"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("HELP")
                .setMessage(dialogTxt)
                .setPositiveButton("done") { dialog, id ->
                    Log.d(TAG,"dialog:$dialog which:$id")
                }

        return builder.create()
    }
}