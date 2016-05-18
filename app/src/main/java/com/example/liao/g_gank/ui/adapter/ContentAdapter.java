package com.example.liao.g_gank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.liao.g_gank.ui.fragment.ContentTypeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentAdapter extends FragmentPagerAdapter {

    private String TAG = "ContentAdapter";
    List<ContentTypeFragment> fragments ;
    FragmentManager fragmentManager;
    List<String> oldTypes;
    List<String> types;


    public ContentAdapter(FragmentManager manager) {
        super(manager);
        this.fragmentManager = manager;

        fragments = new ArrayList<>();
        oldTypes = new ArrayList<>();
        types= new ArrayList<>();
    }

    public void setData(List<ContentTypeFragment> fragments, List<String> types) {

        this.oldTypes = this.types;
        this.types = types;
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


        Fragment fragment = (Fragment) super.instantiateItem(container, position);


        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {


        if (oldTypes.get(0).equals(types.get(0))){

            return POSITION_UNCHANGED;
        } else {

            return POSITION_NONE;
        }

    }


}
