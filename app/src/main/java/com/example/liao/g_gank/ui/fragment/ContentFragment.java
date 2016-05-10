package com.example.liao.g_gank.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.contract.ContentTypeContrect;
import com.example.liao.g_gank.persenter.ContentTypePersenter;
import com.example.liao.g_gank.ui.activity.ChoiceTypeActivity;
import com.example.liao.g_gank.ui.adapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/7.
 */
public class ContentFragment extends BaseFragment implements ContentTypeContrect.IContentTypeView {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Context context;
    private FragmentManager manager;
    private ImageButton ibtnMore;
    private List<String> showTypes;
    private ContentTypePersenter contentTypePersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, null);


        initView(view);
        init();
        initEvent();


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        contentTypePersenter.readDB();
    }

    private void init() {


        manager = getActivity().getSupportFragmentManager();
        context = getActivity();

        contentTypePersenter = new ContentTypePersenter(this);
//        contentTypePersenter.readDB();


    }


    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        ibtnMore = (ImageButton) view.findViewById(R.id.ibtn_more);

    }


    private void initEvent() {


        ibtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ChoiceTypeActivity.class);


                intent.putStringArrayListExtra(ChoiceTypeActivity.INTENT_KEY, (ArrayList<String>) showTypes);

                startActivity(intent);

            }
        });
    }


    @Override
    public void showType(List<String> types) {

        showTypes = types;
        List<Fragment> fragments = new ArrayList<>();


        for (String type : types) {

            ContentTypeFragment allFragment = new ContentTypeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ContentTypeFragment.ARGUMENT_KAY,type);

            allFragment.setArguments(bundle);


            fragments.add(allFragment);


        }


        viewPager.setAdapter(new ContentAdapter(manager, fragments));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < types.size(); i++) {

            tabLayout.getTabAt(i).setText(types.get(i));

        }

    }
}
