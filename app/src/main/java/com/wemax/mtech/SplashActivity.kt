package com.wemax.mtech

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mtecsoft.swapme.view.activities.base.BaseActivity
import com.permissionx.guolindev.PermissionX
import com.wemax.mtech.activity.auth.HomeActivity
import com.wemax.mtech.activity.auth.WellcomeSliderActivity
import com.wemax.mtech.utils.Constants

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
    }


    private fun initViews() {

        permissions()
    }

    private fun permissions()
    {
        PermissionX.init(this@SplashActivity)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "You need to allow necessary permissions in Settings manually", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Handler(Looper.getMainLooper()).postDelayed({

                        var loggedIn = utilities.getString(context,Constants.LOGGED_IN)
                        when(loggedIn){
                            Constants.TRUE -> {

                                val intent = Intent(this, HomeActivity::class.java)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                            }

                            Constants.FALS -> {

                                val intent = Intent(this, WellcomeSliderActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                             ""  -> {

                                val intent = Intent(this, WellcomeSliderActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }, 1000)
                } else {
                    permissions()
                }
            }
    }

}