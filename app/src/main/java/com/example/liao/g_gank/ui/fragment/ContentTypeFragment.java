package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.example.liao.g_gank.event.OnRecyclerItemClickListener;
import com.example.liao.g_gank.model.data.ContentResult;
import com.example.liao.g_gank.persenter.ContentPersenter;
import com.example.liao.g_gank.ui.activity.GankWebActivity;
import com.example.liao.g_gank.ui.adapter.ContentFragmentAdapter;

import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentTypeFragment extends BaseFragment implements ContentContract.IContentView {

    private final String TAG = "ContentTypeFragment";
    public static String ARGUMENT_KAY = "TYPE";
    private RecyclerView recyclerView;
    private Context context;
    private Bundle arguments;
    private String type;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ContentPersenter contentPersenter;
    private ContentFragmentAdapter contentFragmentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoadingMore;
    private int loadIndex =1 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_content_type, container, false);
        initView(view);
        initData();
        initEvent();


        return view;
    }

    private void initEvent() {

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

                contentFragmentAdapter.getGankUrl(vh.getAdapterPosition());

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if(isLoadingMore){

                    } else{

                        swipeRefreshLayout.setRefreshing(true);
                        loadIndex++;
                        contentPersenter.loadData(loadIndex, type);
                        isLoadingMore = true;
                    }
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                contentPersenter.loadData(1, type);
                loadIndex =1;
            }
        });
    }


    private void initView(View view) {

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.reLayout);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initData() {


        context = getActivity();
        arguments = getArguments();
        type = arguments.getString(ARGUMENT_KAY);

        Log.e(TAG,"type = " +type);

        if (contentPersenter == null) {

            contentPersenter = new ContentPersenter(this);
        }

        contentPersenter.loadData(loadIndex, type);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showResult(List<ContentResult> list) {


        isLoadingMore = false;

        if (list != null) {

            if (contentFragmentAdapter == null) {
                contentFragmentAdapter = new ContentFragmentAdapter(this);
                recyclerView.setAdapter(contentFragmentAdapter);
            }
            contentFragmentAdapter.setData(list);
            contentFragmentAdapter.notifyDataSetChanged();
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



    public void showGankWebView(ContentResult contentResult){
        if (contentResult != null){

            Intent intent = new Intent(context, GankWebActivity.class);
            intent.putExtra(GankWebActivity.INTENT_URL_KEY,contentResult.getUrl());
            intent.putExtra(GankWebActivity.INTENT_TITLE_KEY,contentResult.getDesc());
            startActivity(intent);

        }

    }

    public String getTypt(){

        return type;
    }


}
