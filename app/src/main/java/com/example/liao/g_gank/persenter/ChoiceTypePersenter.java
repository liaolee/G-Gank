package com.example.liao.g_gank.persenter;

import com.example.liao.g_gank.contract.ChoiceTypeContract;
import com.example.liao.g_gank.ui.activity.ChoiceTypeActivity;

/**
 * Created by liao on 2016/5/9.
 */
public class ChoiceTypePersenter implements ChoiceTypeContract.IChoicePersenter {

    ChoiceTypeActivity choiceTypeActivity;

    public ChoiceTypePersenter(ChoiceTypeActivity choiceTypeActivity) {

        this.choiceTypeActivity = choiceTypeActivity;

    }

    @Override
    public void loadChoiceType() {

        String[] type = {"Android", "iOS", "休息视频", "拓展资源", "前端", "all"};
        choiceTypeActivity.showChoiceType(type);

    }
}
