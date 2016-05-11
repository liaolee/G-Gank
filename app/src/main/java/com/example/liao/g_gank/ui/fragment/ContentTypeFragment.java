package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.contract.ContentContract;
import com.example.liao.g_gank.data.ContentResult;
import com.example.liao.g_gank.persenter.ContentPersenter;
import com.example.liao.g_gank.ui.adapter.ContentFragmentAdapter;

import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentTypeFragment extends BaseFragment implements ContentContract.IContentView {

    public static String ARGUMENT_KAY = "TYPE";
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Context context;
    private Bundle arguments;
    private String type;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ContentPersenter contentPersenter;
    private ContentFragmentAdapter contentFragmentAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content_type, null);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initEvent() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                contentPersenter.loadData(1, type,contentPersenter.NET_RETROFIT);

            }
        });

    }


    private void initView(View view) {

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.reLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout);

    }

    private void initData() {

        context = getActivity();
        arguments = getArguments();
        type = arguments.getString(ARGUMENT_KAY);
        if (contentPersenter == null){

            contentPersenter = new ContentPersenter(this);
        }

        contentPersenter.loadData(1, type,contentPersenter.CACHE_RETROFIT);

    }

    @Override
    public void showResult(List<ContentResult> list) {

        swipeRefreshLayout.setRefreshing(false);

        if (list != null) {

            if (contentFragmentAdapter ==null){

                Log.e("contentPersenter","contentPersenter = null");
                contentFragmentAdapter = new ContentFragmentAdapter(context);

            }

            contentFragmentAdapter.setData(list);
            contentFragmentAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(contentFragmentAdapter);

        }


    }

    @Override
    public void onStop() {
        super.onStop();

        if (contentFragmentAdapter !=null){

           contentFragmentAdapter = null;

        }
    }
}