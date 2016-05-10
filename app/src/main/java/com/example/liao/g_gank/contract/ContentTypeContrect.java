package com.example.liao.g_gank.contract;

import com.example.liao.g_gank.BasePersenter;
import com.example.liao.g_gank.BaseView;

import java.util.List;

/**
 * Created by liao on 2016/5/9.
 */
public interface ContentTypeContrect {

    interface IContentTypeView extends BaseView{

       void  showType(List<String> types);
    }

    interface IContentTypePersenter extends BasePersenter{

        void readDB();
    }
}
