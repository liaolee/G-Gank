package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.ui.adapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentFragment extends BaseFragment {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Context context;
    private FragmentManager manager;

    public ContentFragment() {


    }

    public ContentFragment(FragmentManager manager) {

        this.manager = manager;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, null);


        init();
        initView(view);
        initData();
        initEvent();


        return view;

    }

    private void init() {

        context = getActivity();


    }


    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tLayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }


    private void initData() {

        List<Fragment> fragments = new ArrayList<>();
        ContentAllFragment allFragment = new ContentAllFragment();
        fragments.add(allFragment);
        ContentAndroidFragment androidFragment = new ContentAndroidFragment();
        fragments.add(androidFragment);
        ContentIOSFragment iosFragment = new ContentIOSFragment();
        fragments.add(iosFragment);


        viewPager.setAdapter(new ContentAdapter(manager, fragments));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("All");
        tabLayout.getTabAt(1).setText("Android");
        tabLayout.getTabAt(2).setText("ios");









    }

    private void initEvent() {
    }

}
