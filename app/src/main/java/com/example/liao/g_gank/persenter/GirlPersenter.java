package com.example.liao.g_gank.persenter;

import com.example.liao.g_gank.contract.GirlContract;
import com.example.liao.g_gank.model.GirlResultModel;
import com.example.liao.g_gank.model.data.GirlResult;
import com.example.liao.g_gank.model.data.GirlResultRoot;
import com.example.liao.g_gank.ui.fragment.GirlFragment;
import com.example.liao.g_gank.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by liao on 2016/5/8.
 */
public class GirlPersenter implements GirlContract.IGirlPersenter {

    private GirlFragment girlFragment;
    private final List<GirlResult> listAll;

    public GirlPersenter(final GirlFragment girlFragment) {

        this.girlFragment = girlFragment;
        listAll = new ArrayList<>();

    }

    @Override
    public void loadData(final int page, String type) {

        Subscriber subscriber = new Subscriber<GirlResultRoot>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GirlResultRoot girlResultRoot) {

                List<GirlResult> list = girlResultRoot.getResults();

                if (page == 1){
                    listAll.clear();
                    listAll.addAll(list);
                }else{
                    listAll.addAll(list);
                }

                girlFragment.showResult(listAll);

            }
        };

        GirlResultModel.instance(NetUtils.isConnected(girlFragment.getContext())).getGirlResult(subscriber, type, page);

    }


}


