package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    }

    private void initData() {

        context = getActivity();
        GirlPersenter persenter = new GirlPersenter(this);

        persenter.loadData(1, "福利");

    }

    private void initView(View view) {

        reLayout = (RecyclerView) view.findViewById(R.id.reLayout_girl);
        reLayout.setLayoutManager(new GridLayoutManager(context, 2));

    }


    @Override
    public void showResult(List<GirlResult> list) {

        Log.e("GirlResult", "GirlResult list = " + list.size());
        if (list != null) {

            if (girlFragmentAdapter == null) {
                girlFragmentAdapter = new GirlFragmentAdapter(this);

            }

            girlFragmentAdapter.setData(list);
            girlFragmentAdapter.notifyDataSetChanged();

            reLayout.setAdapter(girlFragmentAdapter);
        }


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
//        startActivity(intent);

    }
}
