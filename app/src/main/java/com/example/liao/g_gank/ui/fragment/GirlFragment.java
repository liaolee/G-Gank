package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.contract.GirlContract;
import com.example.liao.g_gank.data.GirlResult;
import com.example.liao.g_gank.persenter.GirlPersenter;
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

        return view;
    }

    private void initData() {

        context = getActivity();
        GirlPersenter persenter = new GirlPersenter(this);

        persenter.loadData(1, "福利", GirlPersenter.CACHE_RETROFIT);

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

                girlFragmentAdapter = new GirlFragmentAdapter(context);

            }


            girlFragmentAdapter.setData(list);
            girlFragmentAdapter.notifyDataSetChanged();

            reLayout.setAdapter(girlFragmentAdapter);
        }


    }
}
