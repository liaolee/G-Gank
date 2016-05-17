package com.example.liao.g_gank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();



    public ContentAdapter(FragmentManager manager) {
        super(manager);
    }

    public void setData( List<Fragment> fragments){

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment f = (Fragment) super.instantiateItem(container, position);
        return f;
    }



}
