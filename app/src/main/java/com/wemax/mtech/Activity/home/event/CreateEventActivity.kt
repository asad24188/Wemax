package com.wemax.mtech.Activity.home.event

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.wemax.mtech.Model.home.event.LabelsModelClass
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Adapter.NewReminderAdapter
import com.wemax.mtech.Adapter.groups.CustomSpinnerAdapter
import com.wemax.mtech.Adapter.home.event.LabelAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCreateEventBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    lateinit var binding: ActivityCreateEventBinding
    val contextActivity = this@CreateEventActivity
    lateinit var utils: Utilities
    var labelName = ""
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0
    var SavedHour = 0
    var SavedMinute = 0
    var dateAndTime = ""

    var dateAndTimeAfterAllProcess = ""
    var dateAndTimeFormatInLoginStr = "dd-MM-yyyy"
    var category = ""
    var privacyStr = ""
    var privacy_titlesList = arrayOf("Public", "Friends", "Only Me")
    var customSpinnerImagesList = intArrayOf(
        R.drawable.public_privacy,
        R.drawable.public_privacy,
        R.drawable.public_privacy
    )
    lateinit var labelsListObj: LabelsModelClass
    var labelsList: ArrayList<LabelsModelClass> = arrayListOf()

    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()
    lateinit var adapter: NewReminderAdapter


    var listChips: ArrayList<ChipsTagsModel> = arrayListOf()
    var listChipsResposneTime: ArrayList<ChipsTagsModel> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()

    }

    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        getSpinnersValue()
        chipsLayoutManager()
        chipsLayoutManagerResponseTime()
        binding.uploadEventImage.setOnClickListener {
            imagePicker()
        }

        binding.btncreate.setOnClickListener {
            startActivity(Intent(this, EventDetailsActivity::class.java))
        }

        binding.addCustomeLable.setOnClickListener {
            startActivity(Intent(this, AddYourOwnActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.rcvLabels.layoutManager=
            LinearLayoutManager(this)
        binding.rcvLabels.setHasFixedSize(true)
        getLabels()
        binding.rcvLabels.adapter=LabelAdapter(this,labelsList)
        binding.backpress.setOnClickListener { finish() }
        pickDate()

        binding.recyclerFriendshared.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerFriendshared.adapter = NewReminderAdapter(contextActivity, listGroupMembers)

    }

    private fun getLabels() {
        labelsList= arrayListOf()
        labelsList.add(LabelsModelClass("Cigaret","10","Faraz"))
        labelsList.add(LabelsModelClass("Cigaret","10","Faraz"))
    }

    private fun initRecyclerView() {
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                false,
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive),
                true
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william),
                false
            )
        )
    }

    private fun getSpinnersValue() {
        val list1_category = arrayOf(
            "Category 1",
            "Category 2",
            "Category 3",
        )
        binding.spinnerCategory.adapter =
            ArrayAdapter(this, R.layout.my_spinner_item, list1_category)
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    category = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    category = "Select Event Category"
                }
            }
// privacy Spinner
        binding.spinnerPrivacy.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    privacyStr = privacy_titlesList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    privacyStr = "Public"
                }
            }

        val customAdapter =
            CustomSpinnerAdapter(applicationContext, customSpinnerImagesList, privacy_titlesList)
        binding.spinnerPrivacy.adapter = customAdapter
    }

//    private fun getDataDialog() {
//
//        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
//            .create()
//        val view = layoutInflater.inflate(R.layout.popup_add_custom_label, null)
//
//        val eventName = view.findViewById<EditText>(R.id.eventName_et)
//        val btnsave = view.findViewById<CardView>(R.id.btnsave)
//
//        btnsave.setOnClickListener {
//            labelName = eventName.text.toString()
//
//            if (labelName.equals("")) {
//                utils.toastMessageAmd(contextActivity, "Label is Required")
//            } else {
//                labelsListObj = LabelsModelClass()
////                countryCode = c
//                labelsListObj.Item = labelName
//                addnew()
//                builder.dismiss()
//            }
//
//
//        }
//
//        builder.setView(view)
//        builder.setCanceledOnTouchOutside(true)
//        builder.show()
//    }
//
//    fun addnew() {
//        val v: View = layoutInflater.inflate(R.layout.item_custom_label, null)
//        binding.container.addView(v)
//
//        var name = v.findViewById<TextView>(R.id.labelName)
//        name.text = labelsListObj.Item
//
//        val icon_close = v.findViewById<RelativeLayout>(R.id.btnClose)
//        icon_close.setOnClickListener {
//            removeview(v)
//        }
//
//    }
//
//    fun removeview(view: View) {
//        binding.container.removeView(view)
//    }

    private fun getDateTimeCalender() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        cal.timeInMillis
    }

    private fun pickDate() {
        binding.dateTimeLayout.setOnClickListener {
            getDateTimeCalender()
            val cal = Calendar.getInstance()
            val datedialog = DatePickerDialog(this, this, year, month, day)
            datedialog.datePicker.minDate = cal.timeInMillis
//            datedialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datedialog.show()

            // when you want to choose previous date too
/*            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()*/
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        SavedDay = p3
        SavedMonth = p2 + 1
        SavedYear = p1

        getDateTimeCalender()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        SavedHour = p1
        SavedMinute = p2

        dateAndTime = "$SavedDay-$SavedMonth-$SavedYear $SavedHour:$SavedMinute"

        var dateFormatterTry = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(dateAndTime)
        val timeFormatterTry = SimpleDateFormat("@ h:mm aa")
        val displayValueTry = timeFormatterTry.format(dateFormatterTry)
        binding.tvdateTime.text = "$SavedDay-$SavedMonth-$SavedYear " + displayValueTry
        // for 12 hours .
        dateAndTimeAfterAllProcess = binding.tvdateTime.text.toString()
//        dateFormat1(dateAndTime)  // dd-MM-yyyy, 10-May-2022"


    }

    private fun dateFormat1(dateAndTime: String) {

//        var userEnteredDate = "15/06/2022 23:10"
        var userEnteredDate = dateAndTime
        var dateFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(userEnteredDate)
        val todayDate = Date()
//        val diffAmd =  todayDate.time - userEnteredDate.time // to select previous date

        var dateDiffAmd_miliSeconds = dateFormatter.time - todayDate.time   // to get new/next date
        var numOfDaysAmd =
            (dateDiffAmd_miliSeconds / (1000 * 60 * 60 * 24)).toInt() + 1 // days wrong arhy thy to mene one add kya
        var hoursAmd = (dateDiffAmd_miliSeconds / (1000 * 60 * 60)).toInt()
//        var minutesAmd = ((dateDiffAmd_miliSeconds / (1000 * 60)).toInt()) - (hoursAmd * 60)  // when yo want to get total mintues
        var minutesAmd =
            ((dateDiffAmd_miliSeconds / (1000 * 60)).toInt()) - (hoursAmd * 60)   // when you want to get remaining mintues after specific like 50 hours
        var secondsAmd = (dateDiffAmd_miliSeconds / 1000).toInt()

/*
        if (minutesAmd < 0 || secondsAmd < 0) {
            binding.tvdateTime.text =
                Editable.Factory.getInstance()
                    .newEditable(dateAndTimeFormatInLoginStr + " @ " + timeFormatInLoginStr)

        } else {
        }
*/


    }

    private fun chipsLayoutManager() {

        // ref link : https://github.com/BelooS/ChipsLayoutManager

        val chipsLayoutManager =
            ChipsLayoutManager.newBuilder(contextActivity)
                //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
                .setChildGravity(Gravity.TOP)
                //whether RecyclerView can scroll. TRUE by default
                .setScrollingEnabled(true)
                //set maximum views count in a particular row
                .setMaxViewsInRow(3)
                //set gravity resolver where you can determine gravity for item in position.
                //This method have priority over previous one
                .setGravityResolver { Gravity.CENTER }
                //you are able to break row due to your conditions. Row breaker should return true for that views
                .setRowBreaker { position -> position == 6 || position == 11 || position == 2 }
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                // whether strategy is applied to last row. FALSE by default
//                .withLastRow(true)
                .build()
        binding.rv.setLayoutManager(chipsLayoutManager)


//        binding.recyclerAccepted.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerViewListChips()
        binding.rv.adapter = ChipsAdapter(contextActivity, listChips)
//        ViewCompat.setLayoutDirection(binding.rv, ViewCompat.LAYOUT_DIRECTION_RTL);

    }

    private fun initRecyclerViewListChips() {
        listChips.add(
            ChipsTagsModel(
                "Friends",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Couple",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "18+",
            )
        )

        listChips.add(
            ChipsTagsModel(
                "Under 18",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Adventurous",
            )
        )


    }

    private fun chipsLayoutManagerResponseTime() {

        // ref link : https://github.com/BelooS/ChipsLayoutManager

        val chipsLayoutManagerRespoTime =
            ChipsLayoutManager.newBuilder(contextActivity)
                //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
                .setChildGravity(Gravity.TOP)
                //whether RecyclerView can scroll. TRUE by default
                .setScrollingEnabled(true)
                //set maximum views count in a particular row
                .setMaxViewsInRow(3)
                //set gravity resolver where you can determine gravity for item in position.
                //This method have priority over previous one
                .setGravityResolver { Gravity.CENTER }
                //you are able to break row due to your conditions. Row breaker should return true for that views
                .setRowBreaker { position -> position == 6 || position == 11 || position == 2 }
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                // whether strategy is applied to last row. FALSE by default
//                .withLastRow(true)
                .build()
        binding.rv1RespoTime.setLayoutManager(chipsLayoutManagerRespoTime)

        initRecyclerViewListChipsResponseTime()
        binding.rv1RespoTime.adapter = ChipsAdapter(contextActivity, listChips)
    }


    private fun initRecyclerViewListChipsResponseTime() {
        listChipsResposneTime.add(
            ChipsTagsModel(
                "1 Hour",
            )
        )
        listChipsResposneTime.add(
            ChipsTagsModel(
                "2 Hours",
            )
        )
        listChipsResposneTime.add(
            ChipsTagsModel(
                "2 Days",
            )
        )

        listChipsResposneTime.add(
            ChipsTagsModel(
                "30 Min Before The Event",
            )
        )

    }
    private fun imagePicker() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(3024)            //Final image size will be less than 3 MB(Optional)
//            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            binding.eventImage.setImageURI(uri)
            binding.eventImage.visibility=View.VISIBLE
            binding.uploadEventImage.visibility=View.GONE
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}