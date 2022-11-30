package com.wemax.mtech.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.remindrobort.app.utils.Utilities

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    lateinit var dialog: ProgressDialog
    lateinit var utilities: Utilities

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::utilities.isInitialized) { utilities = Utilities(context!!) }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

    fun showProgressDialog(show: Boolean) {
        if (show) {
            if (!dialog.isShowing)
                dialog.apply {
                    show()
                }
        } else if (!show) {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }
}