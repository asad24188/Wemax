package com.wemax.mtech.activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.groups.PostCommentsAdapter
import com.wemax.mtech.Model.groups.PostCommentsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostDetailsBinding
    val contextActivity = this@PostDetailsActivity
    lateinit var utils: Utilities


    lateinit var adapterComments: PostCommentsAdapter
    var listComments: ArrayList<PostCommentsModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)


    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }


        initRecyclerView()
        binding.commentsRecylerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterComments = PostCommentsAdapter(contextActivity, listComments)
        binding.commentsRecylerView.adapter = adapterComments




        binding.userImage.setOnClickListener {
            startActivity(Intent(contextActivity, UserProfileActivity::class.java))
        }
        binding.userName.setOnClickListener {
            startActivity(Intent(contextActivity, UserProfileActivity::class.java))
        }


        binding.dottedOptionsMenu.setOnClickListener {
            if (binding.reportThisPostLayout.visibility == View.GONE) {
                binding.reportThisPostLayout.visibility = View.VISIBLE
            } else {

                binding.reportThisPostLayout.visibility = View.GONE
            }

        }

        binding.reportThisPostLayout.setOnClickListener {

        }

        binding.sharesLayout.setOnClickListener {

        }

        binding.likessLayout.setOnClickListener {

        }

        binding.commentsLayout.setOnClickListener {

        }
    }


    private fun initRecyclerView() {
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Good Morning",
                "3 hors",

                )
        )

    }

}