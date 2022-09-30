package com.remindrobort.app.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Utilities(context: Context) {

    private val context: Context? = null
    var dialog: ProgressDialog? = null

    fun showProgressDialog(ctx: Context?, msg: String?) {
        dialog = ProgressDialog(ctx)
        dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog!!.setMessage(msg)
        dialog!!.setIndeterminate(true)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.show()
    }

    fun hideProgressDialog() {
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.cancel()
            dialog = null
        }
    }


    fun saveInt(context: Context, key: String?, value: Int) {
        val sharedPreferences =
            context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(context: Context, key: String?): Int {
        val sharedPreferences =
            context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)

    }

    fun saveString(context: Context, key: String, value: String) {
        val sharedPreferences =
            context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String): String {
        val sharedPreferences =
            context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "").toString()
    }

    fun clearSharedPref(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

    }

    // will be use un facebook login
    fun printHashKey(Context: Context) {
        try {
            val info = Context.packageManager.getPackageInfo(
                Context.packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.i(ContentValues.TAG, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e(ContentValues.TAG, "printHashKey()", e)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "printHashKey()", e)
        }
    }


/*    fun saveStrings(
        context: Context, key: String, value: String
    ) {
        val sharedPref = context.getSharedPreferences("loginshared", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStrings(context: Context, key: String): String {
        val sharedPref = context.getSharedPreferences("loginshared", Context.MODE_PRIVATE)
        return sharedPref.getString(key, "").toString()
    }*/


    fun saveBoolean(
        context: Context, key: String, value: Boolean
    ) {
        val sharedPref = context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(context: Context, key: String): String {
        val sharedPref = context.getSharedPreferences("myAppSharedPreference", Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false).toString()
    }


    fun saveStringInterest(
        context: Context, key: String, value: String
    ) {
        val sharedPref = context.getSharedPreferences("interest", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringInterest(context: Context, key: String): String {
        val sharedPref = context.getSharedPreferences("interest", Context.MODE_PRIVATE)
        return sharedPref.getString(key, "").toString()
    }

    fun isConnectingToInternet(context: Context): Boolean {
        val connectivity = context.getSystemService(
            AppCompatActivity.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    fun setWhiteBars(activity: Activity) {
        val window = activity.window
        val view = window.decorView
        view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility =
            view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        window.navigationBarColor = activity.resources.getColor(android.R.color.white)
        window.statusBarColor = activity.resources.getColor(android.R.color.white)
    }

    fun openDialog(manager: FragmentManager, fragment: DialogFragment) {
        val ft = manager.beginTransaction()
        val prev = manager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        fragment.show(ft, "dialog")
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun timestamp() {
        val timestampString = "model.product_upload_time".toLong()
        val value = SimpleDateFormat("dd/MM/yyyy").format(Date(timestampString * 1000))
    }

    //    var labelsTextsList: ArrayList<BodyRequestResponse> = arrayListOf()
/*
    var reminderId = ""
    var reminderName = ""
    var reminderPhone = ""
    var reminderHours = ""
    var reminderMints = ""*/

/*
    private fun eventOnBackpressed() {
        val myIntent = Intent(this@Login, ContinueAs::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(myIntent)
    }
*/

    fun toastMessageAmd(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun logcatMessage(key: String, value: String) {
        Log.d(log_amd + key, value)
    }

    fun noInternetToast() {
        Toast.makeText(
            context,
            "No Internet Connection",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun snackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
/*
        val snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snack.show()
*/
    }


    var log_amd = "amd:- "
//    var dateFormat = ""

    var listOfSelectedHours_GlobalArrayList: ArrayList<String> = arrayListOf()
    //todo:  set statusbar text visibility, iska bg theme.xml me set kiya h
    fun statusBarTextColorVisibility(window: Window) {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //todo: transparent toolbar code
    fun transparentToolbar(window: Window, context: Context) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(context as Activity)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    fun setWindowFlag(activity: Activity) {
        val win = activity.window
        val winParams = win.attributes
        winParams.flags =
            winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        win.attributes = winParams
    }

}