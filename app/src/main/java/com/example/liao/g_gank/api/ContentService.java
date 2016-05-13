package com.example.liao.g_gank.api;

import com.example.liao.g_gank.model.data.ContentResultRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * "干货"页面api
 * Created by liao on 2016/5/8.
 */
public interface ContentService {

    @Headers("Cache-Control: public, max-age=3600")
    @GET("{data}/10/{page}")
    Call<ContentResultRoot> getContent(@Path("data")String data,@Path("page") int page);

    @Headers("Cache-Control: public, max-age=3600")
    @GET("{data}/10/{page}")
    Observable<ContentResultRoot> getContents(@Path("data")String data, @Path("page") int page);




}
