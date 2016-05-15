package com.example.liao.g_gank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.liao.g_gank.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by liao on 2016/5/15.
 */
public class ShowGirlActivity extends BaseActivity {


    public static final String INTENT_PIC_URL = "INTENT_PIC_URL";
    private PhotoView photoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_girl);

        initView();
        initData();
        initEvent();

    }


    private void initView() {

        photoView = (PhotoView) findViewById(R.id.pv_girl);


    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        String url = (String) bundle.get(INTENT_PIC_URL);

        Glide.with(this).load(url).into(photoView);

    }


    private void initEvent() {

        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                finish();
            }
        });


    }
}
