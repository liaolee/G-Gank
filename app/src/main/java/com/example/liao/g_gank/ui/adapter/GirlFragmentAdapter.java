package com.example.liao.g_gank.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liao.g_gank.R;
import com.example.liao.g_gank.model.data.GirlResult;
import com.example.liao.g_gank.ui.fragment.GirlFragment;

import java.util.List;

/**
 * Created by liao on 2016/5/11.
 */
public class GirlFragmentAdapter extends RecyclerView.Adapter {

    private GirlFragment girlFragment;
    private List<GirlResult> list;

    public GirlFragmentAdapter(GirlFragment girlFragment) {

        this.girlFragment = girlFragment;
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

        ViewHolder viewHolder =  (ViewHolder) holder;

        Glide.with(girlFragment).load(list.get(position).getUrl()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void getShowGirl(RecyclerView.ViewHolder viewHolder){

        girlFragment.showGirlActivity(list.get(viewHolder.getAdapterPosition()),viewHolder.itemView);

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
