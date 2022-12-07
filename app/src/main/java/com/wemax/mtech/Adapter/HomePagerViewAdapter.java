package com.wemax.mtech.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wemax.mtech.Fragment.CalendarFragment;
import com.wemax.mtech.Fragment.ChatFragment;
import com.wemax.mtech.Fragment.HomeFragment;
import com.wemax.mtech.Fragment.NotInUse.SearchFragment;
import com.wemax.mtech.Fragment.NotificationFragment;

public class HomePagerViewAdapter extends FragmentPagerAdapter {


    int mNumOfTabs;
    Context context;

    public HomePagerViewAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment= null;

        switch (i)
        {
            case 0:
                fragment = new HomeFragment();
                break;

            case 1:
                fragment =new SearchFragment();
                break;

            case 2:
                fragment = new CalendarFragment();
                break;

            case 3:

                fragment = new ChatFragment();
                break;

            case 4:

                fragment = new NotificationFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }



}
