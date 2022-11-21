package com.wemax.mtech.activity.home.serviceDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.home.serviceDetails.PickAServiceAdapter
import com.wemax.mtech.Model.serviceDetailsModel.ServicesMoreModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityPickaServiceBinding

class PickaServiceActivity : AppCompatActivity(),
    PickAServiceAdapter.onItemClickListner_interfaceName {

    lateinit var adapterServicesMore: PickAServiceAdapter
    var listServicesMore: ArrayList<ServicesMoreModel> = arrayListOf()
    lateinit var binding: ActivityPickaServiceBinding
    val contextActivity = this@PickaServiceActivity
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickaServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {

        initServicesMoreRecyclerView()
        binding.servicesMoreRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterServicesMore =
            PickAServiceAdapter(contextActivity, listServicesMore, this, layoutInflater)
        binding.servicesMoreRecyclerView.adapter = adapterServicesMore
        binding.arrowBack.setOnClickListener { finish() }

    }


    private fun initServicesMoreRecyclerView() {
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.hair_style_image,
                getString(R.string.hair_style),
                getString(R.string.service_time_30_min),
                getString(R.string.service_cost_10)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.hair_cut_imaga,
                getString(R.string.hair_cut),
                getString(R.string.service_time_1_hour),
                getString(R.string.service_cost_100)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.nail_polish_image,
                getString(R.string.nail_polish_styles),
                getString(R.string.service_time_30_min),
                getString(R.string.service_cost_10)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.nail_polish_remover,
                getString(R.string.nail_polish_removal),
                getString(R.string.service_time_15_min),
                getString(R.string.service_cost_5)
            )
        )
        /* listServicesMore.add(
             ServicesMoreModel(
                 "",
                 R.drawable.hair_style_image,
                 getString(R.string.hair_style),
                 getString(R.string.service_time_30_min),
                 getString(R.string.service_cost_10)
             )
         )*/
    }

    override fun onItemClickmyMethod_amd(listt: List<ServicesMoreModel>) {
//        getDataDialog()

    }

    override fun onItemClickmyMethod_amd2(listt: List<ServicesMoreModel>) {
    }


    private fun getDataDialog() {

        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_confirmation, null)
        val text = view.findViewById<TextView>(R.id.serviceDetials)
        val btnAccept = view.findViewById<CardView>(R.id.accept)

        btnAccept.setOnClickListener { builder.dismiss() }

        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}