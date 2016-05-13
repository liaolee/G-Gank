package com.example.liao.g_gank.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.contract.ChoiceTypeContract;
import com.example.liao.g_gank.persenter.ChoiceTypePersenter;
import com.example.liao.g_gank.ui.adapter.ChoiceTypeAdapter;

import java.util.List;

/**
 * Created by liao on 2016/5/9.
 */
public class ChoiceTypeActivity extends BaseActivity implements ChoiceTypeContract.IChoiceTypeView {

    public static final String INTENT_KEY = "TYPE_KEY";
    private ListView listView;
    private List<String> type_list;
    private ChoiceTypeAdapter choiceAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choice_type);

        initView();
        initData();
        initEvent();
    }



    private void initData() {

        Intent intent = getIntent();
        type_list = intent.getStringArrayListExtra(INTENT_KEY);

        ChoiceTypePersenter choiceTypePersenter = new ChoiceTypePersenter(this);
        choiceTypePersenter.loadChoiceType();

    }

    private void initView() {

        listView = (ListView) findViewById(R.id.list_type_choice);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }

    private void initEvent() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choiceAdapter.seleted(view,(ChoiceTypeAdapter.ViewHolder) view.getTag(),position);
            }
        });

    }


    @Override
    public void showChoiceType(String[] allType) {

        choiceAdapter = new ChoiceTypeAdapter(this, listView, type_list ,allType);
        listView.setAdapter(choiceAdapter);

    }
}
