package com.example.liao.g_gank.api;

import com.example.liao.g_gank.data.ContentResultRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by liao on 2016/5/8.
 */
public interface ContentService {

    @GET("Android/10/{page}")
    Call<ContentResultRoot> getContent(@Path("page") int page);


}
