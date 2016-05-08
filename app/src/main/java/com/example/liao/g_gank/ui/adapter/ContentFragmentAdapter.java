package com.example.liao.g_gank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liao.g_gank.R;
import com.example.liao.g_gank.data.ContentResult;

import java.util.List;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentFragmentAdapter extends RecyclerView.Adapter {

    private List<ContentResult> resultList;
    private Context context;

    public ContentFragmentAdapter(List<ContentResult> resultList,Context context) {

        this.resultList = resultList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_fragment,null);

        ViewHolder viewHolder = new ViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.titleText.setText(resultList.get(position).getDesc());

        Glide.with(context).load(resultList.get(position).getUrl()).into(viewHolder.authorImg);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        TextView titleText;
        ImageView authorImg;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.txt_title);
            authorImg = (ImageView) itemView.findViewById(R.id.img_author);


        }


    }
}
