package com.example.liao.g_gank.persenter;

import android.util.Log;

import com.example.liao.g_gank.api.ContentService;
import com.example.liao.g_gank.contract.ContentContract;
import com.example.liao.g_gank.data.ContentResult;
import com.example.liao.g_gank.data.ContentResultRoot;
import com.example.liao.g_gank.ui.fragment.ContentAndroidFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentPersenter implements ContentContract.IContentPersenter {

    private ContentService contentService;
    private Call<ContentResultRoot> call;
    private final Retrofit retrofit;
    ContentAndroidFragment contentAndroidFragment;

    public ContentPersenter(ContentAndroidFragment contentAndroidFragment) {

        this.contentAndroidFragment =  contentAndroidFragment;

        retrofit = new Retrofit.Builder().baseUrl("http://gank.io/api/data/").addConverterFactory(GsonConverterFactory.create()).build();




    }

    @Override
    public void loadData(int page) {

        contentService = retrofit.create(ContentService.class);

        call = contentService.getContent(page);
        call.enqueue(new Callback<ContentResultRoot>() {

            private ContentResultRoot body;
            private List<ContentResult> list;

            @Override
            public void onResponse(Call<ContentResultRoot> call, Response<ContentResultRoot> response) {


                body = response.body();
                list = body.getResults();
                Log.e("response","response = "+ list);
                contentAndroidFragment.showResult(list);


            }

            @Override
            public void onFailure(Call<ContentResultRoot> call, Throwable t) {

                Log.e("response","Failure = ");
            }
        });


    }
}


