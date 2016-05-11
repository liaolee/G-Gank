package com.example.liao.g_gank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liao.g_gank.R;
import com.example.liao.g_gank.data.GirlResult;

import java.util.List;

/**
 * Created by liao on 2016/5/11.
 */
public class GirlFragmentAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<GirlResult> list;

    public GirlFragmentAdapter(Context context) {

        this.context = context;
    }

    public void setData(List<GirlResult> list) {


        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, null);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        Log.e("position","position = "+list.get(position).getUrl());
        Glide.with(context).load(list.get(position).getUrl()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.img_girl);
            textView = (TextView) itemView.findViewById(R.id.txt_date);
        }
    }
}
