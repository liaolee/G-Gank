package com.example.liao.g_gank.api;

import com.example.liao.g_gank.data.GirlResultRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by liao on 2016/5/8.
 */
public interface GirlService {

    @Headers("Cache-Control: public, max-age=3600")
    @GET("{data}/10/{page}")
    Call<GirlResultRoot> getContent(@Path("data") String data, @Path("page") int page);




}
