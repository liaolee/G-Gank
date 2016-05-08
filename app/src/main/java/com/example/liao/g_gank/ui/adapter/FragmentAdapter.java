package com.example.liao.g_gank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    List fragments;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
