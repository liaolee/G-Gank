package com.example.liao.g_gank.persenter;

import android.os.Environment;

import com.example.liao.g_gank.api.ContentService;
import com.example.liao.g_gank.contract.ContentContract;
import com.example.liao.g_gank.data.ContentResult;
import com.example.liao.g_gank.data.ContentResultRoot;
import com.example.liao.g_gank.ui.fragment.ContentTypeFragment;
import com.example.liao.g_gank.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentPersenter implements ContentContract.IContentPersenter {

    private final Retrofit retrofit;
    ContentTypeFragment contentAndroidFragment;
    private ContentService contentService;
    private Call<ContentResultRoot> call;

    public ContentPersenter(final ContentTypeFragment contentAndroidFragment) {

        this.contentAndroidFragment = contentAndroidFragment;


        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!NetUtils.isConnected(contentAndroidFragment.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
//                Logger.t(TAG).w("no network");
                }
                okhttp3.Response originalResponse = chain.proceed(request);
                if (NetUtils.isConnected(contentAndroidFragment.getContext())) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };

        //设置缓存路径
        File httpCacheDirectory = new File(Environment.getDataDirectory(), "responses");
        //设置缓存 10M
        Cache cache = new Cache(contentAndroidFragment.getActivity().getCacheDir(), 10 * 1024 * 1024);

        //创建OkHttpClient，并添加拦截器和缓存代码
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .cache(cache).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/").client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    @Override
    public void loadData(int page, String type) {

        contentService = retrofit.create(ContentService.class);

        call = contentService.getContent(type, page);
        call.enqueue(new Callback<ContentResultRoot>() {

            private ContentResultRoot body;
            private List<ContentResult> list;

            @Override
            public void onResponse(Call<ContentResultRoot> call, Response<ContentResultRoot> response) {


                body = response.body();
                list = body.getResults();
                contentAndroidFragment.showResult(list);


            }

            @Override
            public void onFailure(Call<ContentResultRoot> call, Throwable t) {

            }
        });


    }
}


