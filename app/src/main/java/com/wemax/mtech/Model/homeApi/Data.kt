package com.wemax.mtech.Model.homeApi

data class Data(

    val recommended_businesses: ArrayList<RecommendedBusinesse>,
    val hot_events: ArrayList<HotEvent>,
    val top_section_services: ArrayList<TopSectionService>,
    val activities: ArrayList<Activity>,
    val nearby_providers: ArrayList<NearbyProvider>,
    val mid_section_services: ArrayList<MidSectionService>,
    val events_next_to_me: ArrayList<EventsNextToMe>,
    val cleaning_service_around_me: ArrayList<CleaningServiceAroundMe>,
    val bottom_section_services: ArrayList<BottomSectionService>,
    val people_also_viewed: ArrayList<PeopleAlsoViewed>,
    val nail_salons_around_me: ArrayList<NailSalonsAroundMe>




)