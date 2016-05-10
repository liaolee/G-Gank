package com.example.liao.g_gank.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.liao.g_gank.R;
import com.example.liao.g_gank.ui.fragment.ContentFragment;
import com.example.liao.g_gank.ui.fragment.GirlFragment;
import com.example.liao.g_gank.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String TAG = "MainActivity";

    private BottomNavigationBar bottomNavigationBar;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    /**
     * 主页面的Fragment
     */
    private List<Fragment> fragments;

    /**
     * 记录Manager内添加的Fragment
     */
    private List<Fragment> fragmentManagerList;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();


    }

    @Override
    protected void onRestart() {
        super.onRestart();



    }

    private void initData() {

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();

        fragments = new ArrayList<>();

        fragmentManagerList = new ArrayList<>();

        ContentFragment contentFragment = new ContentFragment();
        fragments.add(contentFragment);
        GirlFragment girlFragment = new GirlFragment();
        fragments.add(girlFragment);
        VideoFragment videoFragment = new VideoFragment();
        fragments.add(videoFragment);

        transaction.add(R.id.fLayout, contentFragment, contentFragment.getTag());
        fragmentManagerList.add(contentFragment);
        transaction.commit();


    }


    private void initView() {


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("G-Gank");
        toolbar.setTitleTextColor(Color.WHITE);



        setSupportActionBar(toolbar);



        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_content, "干货"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_girl, "妹子"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_videocam, "视频"));
        bottomNavigationBar.initialise();

    }




    private void initEvent() {

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                toFragment(position);


            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });



    }

    private void toFragment(int position) {

        Fragment selFragment = fragments.get(position);

        transaction = manager.beginTransaction();

        if (manager.getFragments().contains(selFragment)) {

            for (Fragment fragment : fragments) {
                transaction.hide(fragment);
            }

            transaction.show(selFragment);

        } else {

            for (Fragment fragment : fragmentManagerList) {
                transaction.hide(fragment);
            }
            transaction.add(R.id.fLayout, selFragment, selFragment.getTag());
            fragmentManagerList.add(selFragment);
        }

        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}
