package com.example.liao.g_gank.contract;

/**
 * Created by liao on 2016/5/9.
 */
public interface ChoiceTypeContract {

    interface  IChoiceTypeView{

        void showChoiceType(String[] type);

    }

    interface IChoicePersenter{

        void loadChoiceType();

    }
}
