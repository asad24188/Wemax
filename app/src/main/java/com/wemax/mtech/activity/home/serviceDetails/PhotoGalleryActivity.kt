package com.wemax.mtech.activity.home.serviceDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wemax.mtech.Adapter.home.serviceDetails.GalleryAdapter
import com.wemax.mtech.Model.home.GalleryPhotoModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityPhotoGalleryBinding
import com.wemax.mtech.utils.ItemDecoration

class PhotoGalleryActivity : AppCompatActivity() {

    lateinit var binding: ActivityPhotoGalleryBinding

    private var photoList = ArrayList<GalleryPhotoModel>()
    private lateinit var rvPhoto: RecyclerView
    private lateinit var adapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        binding.arrowBack.setOnClickListener { finish() }

        rvPhoto = findViewById(R.id.rvPhotos)
        rvPhoto.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = GalleryAdapter(this, photoList)
        rvPhoto.adapter = adapter
        val decoration = ItemDecoration(16)
        rvPhoto.addItemDecoration(decoration)

        avengersAssemble()
    }

    private fun avengersAssemble() {
        val photo1 = GalleryPhotoModel(R.drawable.ic_image1)
        photoList.add(photo1)
        val photo2 = GalleryPhotoModel(R.drawable.ic_image2)
        photoList.add(photo2)
        val photo3 = GalleryPhotoModel(R.drawable.ic_image3)
        photoList.add(photo3)
        val photo4 = GalleryPhotoModel(R.drawable.ic_image4)
        photoList.add(photo4)
        val photo5 = GalleryPhotoModel(R.drawable.ic_image1)
        photoList.add(photo5)
        val photo6 = GalleryPhotoModel(R.drawable.ic_image2)
        photoList.add(photo6)
        val photo7 = GalleryPhotoModel(R.drawable.ic_image3)
        photoList.add(photo7)
        val photo8 = GalleryPhotoModel(R.drawable.ic_image4)
        photoList.add(photo8)
    }
}