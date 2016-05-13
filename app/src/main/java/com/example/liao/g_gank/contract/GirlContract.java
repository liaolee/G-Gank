package com.example.liao.g_gank.contract;

import com.example.liao.g_gank.BasePersenter;
import com.example.liao.g_gank.BaseView;
import com.example.liao.g_gank.data.GirlResult;

import java.util.List;

/**
 * Created by liao on 2016/5/8.
 */
public interface GirlContract {

    interface IGirlView extends BaseView{




        void showResult(List<GirlResult> list);
    }

    interface IGirlPersenter extends BasePersenter{

        void loadData(int page, String type, int retrofitType);
    }

}
