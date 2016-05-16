package com.example.liao.g_gank.persenter;

import com.example.liao.g_gank.contract.ContentContract;
import com.example.liao.g_gank.model.ContentResultModel;
import com.example.liao.g_gank.model.data.ContentResult;
import com.example.liao.g_gank.model.data.ContentResultRoot;
import com.example.liao.g_gank.ui.fragment.ContentTypeFragment;
import com.example.liao.g_gank.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentPersenter implements ContentContract.IContentPersenter {


    private ContentTypeFragment contentTypeFragment;
    private final List<ContentResult> listAll;

    public ContentPersenter(final ContentTypeFragment contentAndroidFragment) {

        this.contentTypeFragment = contentAndroidFragment;
        listAll = new ArrayList<>();
    }



    @Override
    public void loadData(final int page, String type) {

        //观察者
        Subscriber subscriber = new Subscriber<ContentResultRoot>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ContentResultRoot contentResultRoot) {

                List<ContentResult> list = contentResultRoot.getResults();
                if (page == 1){
                    listAll.clear();
                    listAll.addAll(list);
                }else{
                    listAll.addAll(list);
                }

                contentTypeFragment.showResult(listAll);

            }
        };

        ContentResultModel.instance(NetUtils.isConnected(contentTypeFragment.getContext())).getContentResult(subscriber,type,page);

    }


}


