package com.wemax.mtech.activity

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.mtecsoft.swapme.view.activities.base.BaseActivity
import com.wemax.mtech.Adapter.HomePagerViewAdapter
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tab_label: TextView
    private lateinit var tab_icon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initt()
    }

    private fun initt() {

        binding.pager.setOffscreenPageLimit(4)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.custom_tab).setIcon(R.drawable.ic_home))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.custom_tab).setIcon(R.drawable.ic_search))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.custom_tab).setIcon(R.drawable.ic_calanader))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.custom_tab).setIcon(R.drawable.ic_chat))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(R.layout.custom_tab).setIcon(R.drawable.ic_notification))
        for (i in 0..4) {
            val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(i)!!
            val tabView: View = tabb.getCustomView()!!
            tab_icon = tabView.findViewById(R.id.tab_icon)
            tab_label = tabView.findViewById(R.id.tab_label)

            if (i == 0) {
                tab_icon.setImageResource(R.drawable.ic_home)
                tab_label.setText("Home")
                tab_label.setTextColor(R.color.app_blue_color)
            }
            if (i == 1) {
                tab_icon.setImageResource(R.drawable.search_icon)
                tab_label.setText("Search")
            }
            if (i == 2) {

                tab_icon.setImageResource(R.drawable.ic_calanader)
                tab_label.setText("Calender")
            }
            if (i == 3) {
                tab_icon.setImageResource(R.drawable.ic_chat)
                tab_label.setText("Chat")
            }
            if (i == 4) {
                tab_icon.setImageResource(R.drawable.ic_notification)
                tab_label.setText("Notification")
            }
        }

        var homePagerViewAdapter = HomePagerViewAdapter(supportFragmentManager, binding.tabLayout.getTabCount())
        binding.pager.setAdapter(homePagerViewAdapter)


        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pager.setCurrentItem(tab.position)
                val pos = tab.position
                if (pos == 0) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(pos)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_home)
                    tab_label.setTextColor(R.color.app_blue_color)
                }
                if (pos == 1) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(pos)!!
                    val tabView = tabb.customView
                    tab_label = tabView!!.findViewById(R.id.tab_label)
                    tab_icon = tabView.findViewById(R.id.tab_icon)
                    tab_icon.setImageResource(R.drawable.ic_search)
                    tab_label.setTextColor(R.color.app_blue_color)
                }
                if (pos == 2) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(pos)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_calanader)
                    tab_label.setTextColor(R.color.app_blue_color)

                }
                if (pos == 3) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(pos)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_chat)
                    tab_label.setTextColor(R.color.app_blue_color)

                }

                if (pos == 4) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(pos)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_notification)
                    tab_label.setTextColor(R.color.app_blue_color)

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val pos = tab.position
                if (tab.position === 0) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(tab.position)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_icon.setImageResource(R.drawable.ic_home)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_label.setTextColor(Color.parseColor("#000000"))
                }
                if (tab.position === 1) {
//                    tab.setIcon(R.drawable.search_gray);
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(tab.position)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_search)
                    tab_label.setTextColor(Color.parseColor("#000000"))

                }
                if (tab.position === 2) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(tab.position)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_calanader)
                    tab_label.setTextColor(Color.parseColor("#000000"))

                }

                if (tab.position === 3) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(tab.position)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_chat)
                    tab_label.setTextColor(Color.parseColor("#000000"))


                }

                if (tab.position === 4) {
                    val tabb: TabLayout.Tab = binding.tabLayout.getTabAt(tab.position)!!
                    val tabView = tabb.customView
                    tab_icon = tabView!!.findViewById(R.id.tab_icon)
                    tab_label = tabView.findViewById(R.id.tab_label)
                    tab_icon.setImageResource(R.drawable.ic_notification)
                    tab_label.setTextColor(Color.parseColor("#000000"))


                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }
}