package com.example.liao.g_gank.contract;

import com.example.liao.g_gank.BasePersenter;
import com.example.liao.g_gank.BaseView;
import com.example.liao.g_gank.data.ContentResult;

import java.util.List;

/**
 * Created by liao on 2016/5/8.
 */
public interface ContentContract {

    interface IContentView extends BaseView{




        void showResult(List<ContentResult> list);
    }

    interface IContentPersenter extends BasePersenter{

        void loadData(int page, String type);
    }

}
