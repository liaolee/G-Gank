package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.contract.GirlContract;
import com.example.liao.g_gank.event.OnRecyclerItemClickListener;
import com.example.liao.g_gank.model.data.GirlResult;
import com.example.liao.g_gank.persenter.GirlPersenter;
import com.example.liao.g_gank.ui.activity.ShowGirlActivity;
import com.example.liao.g_gank.ui.adapter.GirlFragmentAdapter;

import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class GirlFragment extends BaseFragment implements GirlContract.IGirlView {

    private Context context;
    private GirlFragmentAdapter girlFragmentAdapter;
    private RecyclerView reLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GirlPersenter persenter;
    private GridLayoutManager gridLayoutManager;
    private String TAG = "GirlFragment" ;
    private boolean isLoadingMore;
    private int loadIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_girl, null);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initEvent() {

        reLayout.addOnItemTouchListener(new OnRecyclerItemClickListener(reLayout) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

                girlFragmentAdapter.getShowGirl(vh);
            }
        });



        reLayout.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = gridLayoutManager.getItemCount();

                //lastVisibleItem >= totalItemCount - 3 表示剩下4个item自动加载
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 3 && dy > 0) {
                    if(isLoadingMore){

                    } else{
                        swipeRefreshLayout.setRefreshing(true);
                        loadIndex++;
                        persenter.loadData(loadIndex, "福利");
                        isLoadingMore = true;
                    }
                }


            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                persenter.loadData(1, "福利");
                loadIndex = 1;
            }
        });
    }

    private void initData() {

        context = getActivity();
        if (persenter == null){

            persenter = new GirlPersenter(this);

        }

        persenter.loadData(1, "福利");

    }

    private void initView(View view) {

        reLayout = (RecyclerView) view.findViewById(R.id.reLayout_girl);
        gridLayoutManager = new GridLayoutManager(context,2);
        reLayout.setLayoutManager(gridLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout_girl);

    }


    @Override
    public void showResult(List<GirlResult> list) {

        isLoadingMore = false;

        if (list != null) {

            if (girlFragmentAdapter == null) {
                girlFragmentAdapter = new GirlFragmentAdapter(this);
                reLayout.setAdapter(girlFragmentAdapter);
            }

            girlFragmentAdapter.setData(list);
            reLayout.requestLayout();

        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //避免加载过快看不到加载进度
                    Thread.sleep(1000);

                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    public void showGirlActivity(GirlResult girlResult,View view){

        Intent intent = new Intent(getActivity(),ShowGirlActivity.class);
        intent.putExtra(ShowGirlActivity.INTENT_PIC_URL,girlResult.getUrl());


        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), view,getString(R.string.picture));
        try {
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }

    }
}
