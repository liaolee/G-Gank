package com.example.liao.g_gank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.data.ContentResult;

import java.util.List;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentFragmentAdapter extends RecyclerView.Adapter {

    private List<ContentResult> resultList;
    private Context context;

    public ContentFragmentAdapter(Context context) {


        this.context = context;
    }

    public void setData(List<ContentResult> resultList){

        this.resultList = resultList;
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
        viewHolder.authorText.setText(resultList.get(position).getWho());


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        TextView titleText;
        TextView authorText;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.txt_title);
            authorText = (TextView) itemView.findViewById(R.id.txt_author);


        }


    }
}
