package com.wemax.mtech.Fragment

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import androidx.viewpager.widget.PagerAdapter
import com.cheezycode.randomquote.viewmodels.MainViewModel
import com.cheezycode.randomquote.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.wemax.mtech.Adapter.home.*
import com.wemax.mtech.Model.homeApi.*
import com.wemax.mtech.R
import com.wemax.mtech.activity.RecomendedForYouActivity
import com.wemax.mtech.activity.auth.BusinessOwnerActivity
import com.wemax.mtech.activity.auth.LoginActivity
import com.wemax.mtech.activity.home.BookAppointmentActivity
import com.wemax.mtech.activity.home.SearchActivity
import com.wemax.mtech.activity.home.event.CreateEventActivity
import com.wemax.mtech.databinding.FragmentHomeBinding
import com.wemax.mtech.repository.Response
import com.wemax.mtech.utils.Constants
import com.wemax.mtech.utils.GPSTracker
import com.wemax.mtech.utils.WemaxApplication
import java.util.*


class HomeFragment : BaseFragment() {

    private var latitude = ""
    private var longitude = ""
    private var userId = ""

    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: PagerAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var timer: Timer

    private lateinit var recommendedBusinesse: ArrayList<RecommendedBusinesse>
    private lateinit var hotEvent: ArrayList<HotEvent>
    private lateinit var activities: ArrayList<Activity>
    private lateinit var nearByProvider: ArrayList<NearbyProvider>
    private lateinit var eventsNextToMe: ArrayList<EventsNextToMe>
    private lateinit var cleaningServiceAroundMe: ArrayList<CleaningServiceAroundMe>
    private lateinit var peopleAlsoViewed : ArrayList<PeopleAlsoViewed>
    private lateinit var nailSalonsAroundMe: ArrayList<NailSalonsAroundMe>
    private lateinit var topSectionService: ArrayList<TopSectionService>
    private lateinit var midSectionService: ArrayList<MidSectionService>
    private lateinit var bottomSectionService: ArrayList<BottomSectionService>

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initt()
        onClicks()
        homeApi()
        observer()
        swipeToRefresh()
    }

    private fun swipeToRefresh() {

        binding.swipe.setOnRefreshListener(OnRefreshListener {
            mainViewModel.home(userId,latitude,longitude)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun observer() {

        mainViewModel.homeResponse.observe(requireActivity(), androidx.lifecycle.Observer {
            when(it){
                is Response.Loading -> {
                    utilities.showProgressDialog(context, Constants.LOADING)
                }
                is Response.Success -> {
                    it.data?.let {
                        utilities.hideProgressDialog()
                        showToast(it.message)
                        if (it.status == true){

                            nearByProvider = ArrayList()
                            eventsNextToMe = ArrayList()
                            cleaningServiceAroundMe = ArrayList()
                            peopleAlsoViewed = ArrayList()
                            nailSalonsAroundMe = ArrayList()
                            topSectionService = ArrayList()
                            midSectionService = ArrayList()
                            bottomSectionService = ArrayList()

                            binding.swipe.setRefreshing(false)
                            recommendedBusinesse = it.data.recommended_businesses
                            hotEvent = it.data.hot_events
                            activities = it.data.activities
                            nearByProvider = it.data.nearby_providers
                            eventsNextToMe = it.data.events_next_to_me
                            cleaningServiceAroundMe = it.data.cleaning_service_around_me
                            peopleAlsoViewed = it.data.people_also_viewed
                            nailSalonsAroundMe = it.data.nail_salons_around_me
                            topSectionService = it.data.top_section_services
                            midSectionService = it.data.mid_section_services
                            bottomSectionService = it.data.bottom_section_services

                            setRecommended()
                            setHotEvent()
                            setActivities()
                            setNearByProvider()
                            setEventsNextToMe()
                            setCleaningServiceAroundMe()
                            setPeopleAlsoViewed()
                            setNailSalonsAroundMe()
                            nearByProvidersImageSlider()
                            cleaningServiceAroundMeImageSlider()
                            hotEventsImageSlider()

                        }
                    }
                }
                is Response.Error -> {
                    binding.swipe.setRefreshing(false)
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setNailSalonsAroundMe() {

        binding.rcvNailSalons.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvNailSalons.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.NAIL_SALON_AROUND,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun setPeopleAlsoViewed() {

        binding.rcvPeopleAlsoViewed.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvPeopleAlsoViewed.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.ALSO_VIEWED,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun setCleaningServiceAroundMe() {

        binding.rcvCleaingServiceAroundMe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvCleaingServiceAroundMe.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.CLEANING_SERVICE,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)

    }

    private fun setEventsNextToMe() {

        binding.rcvEventsNextToMe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvEventsNextToMe.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.EVENT_NEXT_TO_ME,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun setNearByProvider() {

        binding.rcvNearbyProvider.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvNearbyProvider.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.NEARBY_PROVIDERS,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun setActivities() {

        binding.rcvFindSomthing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvFindSomthing.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.ACTIVITIES,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)

    }

    private fun setHotEvent() {

        binding.rcvHotEvents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvHotEvents.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.HOT_EVENT,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun setRecommended() {

        binding.rcvrecommendedForYouProvider.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvrecommendedForYouProvider.adapter = HomeFragmentServicesAdapter(
            requireContext(),
            Constants.RECOMMENDED_BUSINESS,
            recommendedBusinesse,
            hotEvent,
            activities,
            nearByProvider,
            eventsNextToMe,
            cleaningServiceAroundMe,
            peopleAlsoViewed,
            nailSalonsAroundMe)
    }

    private fun homeApi() {

        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        val gpsTracker = GPSTracker(activity)
                        if (gpsTracker.canGetLocation()) {
                            latitude = gpsTracker.latitude.toString()
                            longitude = gpsTracker.longitude.toString()
                            userId = utilities.getUserId(context!!)
                            mainViewModel.home(userId,latitude,longitude)
                        }
                    }
                    if (report.isAnyPermissionPermanentlyDenied) {
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun initt() {

        recommendedBusinesse = ArrayList()
        hotEvent = ArrayList()
        activities = ArrayList()

        val repository = (activity!!.application as WemaxApplication).authRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    private fun onClicks() {

        binding.logo.setOnClickListener {
            utilities.clearSharedPref(context!!)
            startActivity(
                Intent(context, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }


        binding.floatbtnLayout.setOnClickListener {
            if (binding.fabOptions.visibility == View.GONE) {
                binding.fabOptions.visibility = View.VISIBLE
                binding.backLayout.visibility = View.VISIBLE
            } else {
                binding.fabOptions.visibility = View.GONE
                binding.backLayout.visibility = View.GONE
            }
        }
        binding.backLayout.setOnClickListener {
            if (binding.fabOptions.visibility == View.GONE) {
                binding.fabOptions.visibility = View.VISIBLE
                binding.backLayout.visibility = View.VISIBLE
            } else {
                binding.fabOptions.visibility = View.GONE
                binding.backLayout.visibility = View.GONE
            }
        }

        binding.createEvent.setOnClickListener {
            startActivity(
                Intent(context, CreateEventActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        binding.businessAcc.setOnClickListener {
            startActivity(
                Intent(context, BusinessOwnerActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        binding.bookAppointment.setOnClickListener {
            startActivity(
                Intent(context, BookAppointmentActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }

        binding.findActivity.setOnClickListener {

//            startActivity(Intent(context, FIndActivity::class.java)
//                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }



        binding.tvSeeAllRecommenedForYou.setOnClickListener {
            startActivity(Intent(context, RecomendedForYouActivity::class.java))
        }
//        binding.event.setOnClickListener {
//            bottomNavigation.selectedItemId = R.id.calenderFragment
//        }
        binding.tvSeeAllFindSomeThing.setOnClickListener {
            startActivity(Intent(context, RecomendedForYouActivity::class.java))
        }

        binding.search.setOnClickListener {
            startActivity(
                Intent(context, SearchActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        binding.btnSearchLayout.setOnClickListener {
            if (binding.searchLayout.visibility == View.GONE) {
                binding.searchLayout.visibility = View.VISIBLE
            } else {
                binding.searchLayout.visibility = View.GONE
            }
        }
    }

    private fun hotEventsImageSlider() {

        adapter = SliderAdapter(requireContext(),Constants.TOP_SLIDER, topSectionService, midSectionService, bottomSectionService)
        binding.viewPagerHotEvents.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerHotEvents.post {
                    binding.viewPagerHotEvents.currentItem =
                        (binding.viewPagerHotEvents.currentItem + 1) % topSectionService.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }

    private fun nearByProvidersImageSlider() {

        adapter = SliderAdapter(requireContext(),Constants.MID_SLIDER, topSectionService, midSectionService, bottomSectionService)
        binding.viewPagerNearbyProvider.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerNearbyProvider.post {
                    binding.viewPagerNearbyProvider.currentItem =
                        (binding.viewPagerNearbyProvider.currentItem + 1) % midSectionService.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }

    private fun cleaningServiceAroundMeImageSlider() {

        adapter = SliderAdapter(requireContext(),Constants.BOTTOM_SLIDER, topSectionService, midSectionService, bottomSectionService)
        binding.viewPagerCleaingServiceAroundMe.adapter = adapter

        // timer code
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPagerCleaingServiceAroundMe.post {
                    binding.viewPagerCleaingServiceAroundMe.currentItem =
                        (binding.viewPagerCleaingServiceAroundMe.currentItem + 1) % bottomSectionService.size
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }
}