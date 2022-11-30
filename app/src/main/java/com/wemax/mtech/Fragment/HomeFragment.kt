package com.wemax.mtech.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wemax.mtech.activity.RecomendedForYouActivity
import com.wemax.mtech.activity.auth.BusinessOwnerActivity
import com.wemax.mtech.activity.home.BookAppointmentActivity
import com.wemax.mtech.activity.home.SearchActivity
import com.wemax.mtech.activity.home.event.CreateEventActivity
import com.wemax.mtech.Adapter.InviteFriendsAdapter
import com.wemax.mtech.Adapter.groups.CustomSpinnerAdapter
import com.wemax.mtech.Adapter.home.*
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.home.HotEventsPostModel
import com.wemax.mtech.Model.home.SearchModel
import com.wemax.mtech.R
import com.wemax.mtech.activity.auth.HomeActivity
import com.wemax.mtech.activity.auth.LoginActivity
import com.wemax.mtech.databinding.FragmentHomeBinding
import java.util.*
import kotlin.coroutines.coroutineContext

class HomeFragment : BaseFragment() {

    lateinit var bottomNavigation: BottomNavigationView
    lateinit var newArrayList: ArrayList<PostModel>
    lateinit var newArrayList2: ArrayList<HotEventsPostModel>
    lateinit var newArrayListHotEvents: ArrayList<HotEventsPostModel>
    lateinit var newArrayListSearch: ArrayList<SearchModel>
    lateinit var Images: Array<Int>
    lateinit var Title: Array<String>
    lateinit var Description: Array<String>
    lateinit var adapter: PagerAdapter
    lateinit var timer: Timer
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var privacy_titlesList = arrayOf("Places", "Business", "Accounts", "Events")
    var customSpinnerImagesList = intArrayOf(
        R.drawable.black_tick_mark,
        R.drawable.black_tick_mark,
        R.drawable.black_tick_mark,
        R.drawable.black_tick_mark,
    )

    var privacyStr = ""
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        onClicks()
        bottomNavigation = requireActivity().findViewById(R.id.bottomNavigation)
        return view
    }

    private fun initViews() {
    }

    private fun onClicks() {

        binding.logo.setOnClickListener {
            utilities.clearSharedPref(context!!)
            startActivity(Intent(context, LoginActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

        binding.floatbtnLayout.setOnClickListener {
            if (binding.fabOptions.visibility==View.GONE){
                binding.fabOptions.visibility=View.VISIBLE
                binding.backLayout.visibility=View.VISIBLE
            }else{
                binding.fabOptions.visibility=View.GONE
                binding.backLayout.visibility=View.GONE
            }
        }
        binding.backLayout.setOnClickListener {
            if (binding.fabOptions.visibility==View.GONE){
                binding.fabOptions.visibility=View.VISIBLE
                binding.backLayout.visibility=View.VISIBLE
            }else{
                binding.fabOptions.visibility=View.GONE
                binding.backLayout.visibility=View.GONE
            }
        }

        binding.createEvent.setOnClickListener {
            startActivity(Intent(context, CreateEventActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.businessAcc.setOnClickListener {
            startActivity(Intent(context, BusinessOwnerActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.bookAppointment.setOnClickListener {
            startActivity(Intent(context, BookAppointmentActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.findActivity.setOnClickListener {

//            startActivity(Intent(context, FIndActivity::class.java)
//                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        searchAdapter()
        recommendedForYou()
        hotEvents()
        hotEventsImageSlider()
        findSomethingToDo()
        nearByProviders()
        nearByProvidersImageSlider()
        eventsNextToMe()
        cleaningServiceAroundMe()
        cleaningServiceAroundMeImageSlider()
        peopleAlsoViewed()
        nailSalonsAroundMe()

        binding.tvSeeAllRecommenedForYou.setOnClickListener {
            startActivity(Intent(context, RecomendedForYouActivity::class.java))
        }
        binding.event.setOnClickListener {
            bottomNavigation.selectedItemId = R.id.calenderFragment
        }
        binding.tvSeeAllFindSomeThing.setOnClickListener {
            startActivity(Intent(context, RecomendedForYouActivity::class.java))
        }

        binding.search.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.btnSearchLayout.setOnClickListener {
            if (binding.searchLayout.visibility == View.GONE) {
                binding.searchLayout.visibility = View.VISIBLE
            } else {
                binding.searchLayout.visibility = View.GONE
            }
        }

//        getSpinnersValue()
//
/*        binding.btnSearchLayout.setOnClickListener {
            getSpinnersValue()
        }*/


/*        binding.btnSearchLayoutParent.setOnClickListener {
            if (binding.searchLayout.visibility == View.GONE) {
                binding.searchLayout.visibility = View.VISIBLE
            } else {

                binding.searchLayout.visibility = View.GONE
            }

        }*/
    }
    /* private fun getSpinnersValue() {


         binding.spinnerCategory.onItemSelectedListener =
             object : AdapterView.OnItemSelectedListener {
                 override fun onItemSelected(
                     parent: AdapterView<*>,
                     view: View,
                     position: Int,
                     id: Long
                 ) {
 *//*                    Toast.makeText(
                        context,
                        "You Select Position: " + position + " " + privacy_titlesList[position],
                        Toast.LENGTH_SHORT
                    ).show()*//*
//                    privacyStr = parent.getItemAtPosition(position).toString()
                    privacyStr = privacy_titlesList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

//                    category = "Category 1"
                    privacyStr = ""
                }
            }

        val customAdapter =
            CustomSpinnerSearchAdapter(context!!, customSpinnerImagesList, privacy_titlesList)
        binding.spinnerCategory.adapter = customAdapter

    }
*/

    private fun searchAdapter() {
//        binding.rcvrecommendedForYouProvider.layoutManager = GridLayoutManager(context, 2)
        binding.searchRecyclerView.layoutManager =
            LinearLayoutManager(context)
        binding.searchRecyclerView.setHasFixedSize(true)
        newArrayListSearch = arrayListOf<SearchModel>()
        getSearch()
        binding.searchRecyclerView.adapter =
            SearchHomeAdapter(requireContext(), newArrayListSearch)
    }

    private fun recommendedForYou() {
//        binding.rcvrecommendedForYouProvider.layoutManager = GridLayoutManager(context, 2)
        binding.rcvrecommendedForYouProvider.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvrecommendedForYouProvider.setHasFixedSize(true)
        newArrayList = arrayListOf<PostModel>()
        getUserData()
        binding.rcvrecommendedForYouProvider.adapter =
            HomeFragmentServicesAdapter(requireContext(), newArrayList)
    }

    private fun hotEvents() {
        binding.rcvHotEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvHotEvents.setHasFixedSize(true)
        newArrayListHotEvents = arrayListOf()
        getHotEventsData()
        binding.rcvHotEvents.adapter = HotEventsAdapter(requireContext(), newArrayListHotEvents)
    }

    private fun findSomethingToDo() {
        binding.rcvFindSomthing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvFindSomthing.setHasFixedSize(true)
        newArrayList = arrayListOf()
        getUserData3()
        binding.rcvFindSomthing.adapter =
            HomeFragmentPlacesAdapter(requireContext(), newArrayList)

    }


    private fun nearByProviders() {
        binding.rcvNearbyProvider.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvNearbyProvider.setHasFixedSize(true)
        newArrayList = arrayListOf<PostModel>()
        getUserData()
        binding.rcvNearbyProvider.adapter =
            HomeFragmentServicesAdapter(requireContext(), newArrayList)
    }

    private fun eventsNextToMe() {
        binding.rcvEventsNextToMe.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvEventsNextToMe.setHasFixedSize(true)
        newArrayList2 = arrayListOf()
        getUserData2()
        binding.rcvEventsNextToMe.adapter =
            HotEventsAdapter(requireContext(), newArrayList2)

    }

    private fun cleaningServiceAroundMe() {
        binding.rcvCleaingServiceAroundMe.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvCleaingServiceAroundMe.setHasFixedSize(true)
        newArrayList = arrayListOf()
        getUserData()
        binding.rcvCleaingServiceAroundMe.adapter =
            HomeFragmentServicesAdapter(requireContext(), newArrayList)

    }

    private fun peopleAlsoViewed() {
        binding.rcvPeopleAlsoViewed.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvPeopleAlsoViewed.setHasFixedSize(true)
        newArrayList = arrayListOf()
        getUserData()
        binding.rcvPeopleAlsoViewed.adapter =
            HomeFragmentServicesAdapter(requireContext(), newArrayList)

    }

    private fun nailSalonsAroundMe() {
        binding.rcvNailSalons.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvNailSalons.setHasFixedSize(true)
        newArrayList = arrayListOf()
        getUserData()
        binding.rcvNailSalons.adapter = HomeFragmentServicesAdapter(requireContext(), newArrayList)

    }

    private fun getSearch() {
        newArrayListSearch = arrayListOf()
        newArrayListSearch.add(
            SearchModel(
                "",
                resources.getString(R.string.places),
                R.drawable.black_tick_mark
            )
        )
        newArrayListSearch.add(
            SearchModel(
                "",
                resources.getString(R.string.business),
                R.drawable.black_tick_mark
            )
        )
        newArrayListSearch.add(
            SearchModel(
                "",
                resources.getString(R.string.accouts),
                R.drawable.black_tick_mark
            )
        )
        newArrayListSearch.add(
            SearchModel(
                "",
                resources.getString(R.string.events),
                R.drawable.black_tick_mark
            )
        )
    }

    private fun getUserData() {
        newArrayList = arrayListOf()
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider2, "Paradise Beauty Salon",
                getString(R.string.rating_text),
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),

            )
        )
    }
    private fun getUserData2() {
        newArrayList2 = arrayListOf()
        newArrayList2.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, getString(R.string.post_title),
                getString(R.string.rating_text),
                "2km away"
            )
        )
        newArrayList2.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, "Paradise Beauty Salon",
                getString(R.string.rating_text),
                "2km away"
            )
        )
        newArrayList2.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, getString(R.string.post_title),
                getString(R.string.rating_text),
                "2km away"
            )
        )
        newArrayList2.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, getString(R.string.post_title),
                getString(R.string.rating_text),
                "2km away"
            )
        )
    }
    private fun getUserData3() {
        newArrayList = arrayListOf()
        newArrayList.add(
            PostModel(
                R.drawable.ic_park_view1, "US National Park",
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.playgroundimg,"Clay County Little Rain Lake Park",
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.ic_park_view1, "US National Park",
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.ic_park_view, getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
    }

    private fun getHotEventsData() {
        newArrayListHotEvents = arrayListOf()
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, "New Year Party",
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, "New Year Party",
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.eventparty_img, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
    }

    private fun hotEventsImageSlider() {
        Images = arrayOf(
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider
        )
        Title = arrayOf(
            getString(R.string.text_title_slider_image),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning)
        )
        Description = arrayOf(
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description)
        )
        adapter = SliderAdapter(requireContext(), Images, Title, Description)

        binding.viewPagerHotEvents.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerHotEvents.post {
                    binding.viewPagerHotEvents.currentItem =
                        (binding.viewPagerHotEvents.currentItem + 1) % Images.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 2000, 2000)
    }

    private fun nearByProvidersImageSlider() {
        Images = arrayOf(
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider
        )
        Title = arrayOf(
            getString(R.string.text_title_slider_image),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning)
        )
        Description = arrayOf(
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description)
        )
        adapter = SliderAdapter(requireContext(), Images, Title, Description)

        binding.viewPagerNearbyProvider.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerNearbyProvider.post {
                    binding.viewPagerNearbyProvider.currentItem =
                        (binding.viewPagerNearbyProvider.currentItem + 1) % Images.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 2000, 2000)
    }

    private fun cleaningServiceAroundMeImageSlider() {
        Images = arrayOf(
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider,
            R.drawable.image_slider
        )
        Title = arrayOf(
            getString(R.string.text_title_slider_image),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning),
            getString(R.string.office_cleaning)
        )
        Description = arrayOf(
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description),
            getString(R.string.slider_image_description)
        )
        adapter = SliderAdapter(requireContext(), Images, Title, Description)

        binding.viewPagerCleaingServiceAroundMe.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerCleaingServiceAroundMe.post {
                    binding.viewPagerCleaingServiceAroundMe.currentItem =
                        (binding.viewPagerCleaingServiceAroundMe.currentItem + 1) % Images.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 2000, 2000)
    }
}