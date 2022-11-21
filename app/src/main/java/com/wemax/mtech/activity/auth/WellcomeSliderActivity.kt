package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.remindrobort.app.utils.Utilities
import com.viewpagerindicator.CirclePageIndicator
import com.wemax.mtech.Adapter.WellcomeSliderAdapter
import com.wemax.mtech.Model.WellcomeSliderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityWellcomeSliderBinding

class WellcomeSliderActivity : AppCompatActivity() {
    lateinit var binding: ActivityWellcomeSliderBinding

    lateinit var mPager: ViewPager
    private var currentPage = 0

    private var sliderImagesArrayList: ArrayList<WellcomeSliderModel> = arrayListOf()
    private var data_IMAGES: ArrayList<WellcomeSliderModel> = arrayListOf()



    lateinit var indicator: CirclePageIndicator
    private lateinit var utilities: Utilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWellcomeSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClicks()
        sliderCode()
    }

    private fun initViews() {
        utilities = Utilities(this@WellcomeSliderActivity)
        utilities.setWhiteBars(this@WellcomeSliderActivity)
    }

    private fun onClicks() {
        binding.btnext.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))

        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }
    }

    private fun sliderCode() {
        data_IMAGES.add(
            WellcomeSliderModel(
                R.drawable.ic_banner,
                getString(R.string.banner1_title),
                getString(R.string.banner1_caption)
            )
        )
        data_IMAGES.add(
            WellcomeSliderModel(
                R.drawable.ic_banner2,
                getString(R.string.banner2_title),
                getString(R.string.banner2_caption)
            )
        )
        data_IMAGES.add(
            WellcomeSliderModel(
                R.drawable.ic_banner3,
                getString(R.string.banner3_title),
                getString(R.string.banner3_caption)
            )
        )
        data_IMAGES.add(
            WellcomeSliderModel(
                R.drawable.ic_banner4,
                getString(R.string.banner4_title),
                getString(R.string.banner4_caption)
            )
        )

        for (i in data_IMAGES.indices) sliderImagesArrayList.add(
            data_IMAGES.get(i)
        )

        mPager =
            findViewById<View>(R.id.pager) as ViewPager

        indicator = findViewById<View>(R.id.indicator) as CirclePageIndicator

        mPager.setAdapter(
            WellcomeSliderAdapter(this@WellcomeSliderActivity, sliderImagesArrayList)
        )

        getCirclePagerIndicator()
    }


    private fun getCirclePagerIndicator() {
        indicator.setViewPager(mPager)
        val density = resources.displayMetrics.density
        indicator.radius = 5 * density

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }
}