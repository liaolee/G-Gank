package com.example.liao.g_gank.model;

import com.example.liao.g_gank.api.GirlService;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liao on 2016/5/12.
 */
public class GirlResultModel {


    private final Retrofit retrofit;
    private GirlService girlService;

    private GirlResultModel(final boolean isConnected) {

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (!isConnected) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (isConnected) {
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

        File file = new File("/data/data/com.example.liao.g_gank/cache");
        //设置缓存 10M
        Cache cache = new Cache(file, 10 * 1024 * 1024);

        // 创建OkHttpClient，并添加拦截器和缓存代码
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public static GirlResultModel instance(boolean isConnect) {

        if (isConnect) {

            return SingleHodlerNet.GIRL_RESULT_MODEL;

        } else {

            return SingleHolderCache.GIRL_RESULT_MODEL;
        }
    }

    public void getGirlResult(Observer observer, String type, int page) {

        girlService = retrofit.create(GirlService.class);
        girlService.getContents(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);


    }

    public static class SingleHodlerNet {

        private static final GirlResultModel GIRL_RESULT_MODEL = new GirlResultModel(true);


    }

    public static class SingleHolderCache

    {

        private static final GirlResultModel GIRL_RESULT_MODEL = new GirlResultModel(false);
    }


}
