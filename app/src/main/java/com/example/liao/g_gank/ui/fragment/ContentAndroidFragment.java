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
public class ContentAndroidFragment extends BaseFragment implements ContentContract.IContentView {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Context context;


    public ContentAndroidFragment(Context context) {
        super();
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content_android, null);

        initView(view);
        initData();

        return view;
    }


    private void initView(View view) {


        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.reLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }

    private void initData() {

        ContentPersenter contentPersenter = new ContentPersenter(this);
        contentPersenter.loadData(1);

    }

    @Override
    public void showResult(List<ContentResult> list) {

        Log.e("content",""+list);

        if (list != null) {
            ContentFragmentAdapter contentFragmentAdapter = new ContentFragmentAdapter(list, context);
            recyclerView.setAdapter(contentFragmentAdapter);
        }


    }
}
