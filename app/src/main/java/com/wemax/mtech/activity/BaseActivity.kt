package com.mtecsoft.swapme.view.activities.base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.Ringtone
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.R


abstract class BaseActivity : AppCompatActivity() {

    lateinit var utilities: Utilities
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this
        if (!::utilities.isInitialized) utilities = Utilities(this)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }


    fun showAlertDialog(title: String, message: String, callback: DialogInterface.OnClickListener) {

        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton(getString(R.string.ok), callback)
        dialog.setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()

    }


    fun logout() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Logout")
        dialog.setMessage("Are you sure ?")
        dialog.setPositiveButton(getString(R.string.yes)) { _: DialogInterface, _: Int ->
            utilities.clearSharedPref(context = this)
        }
        dialog.setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }
}