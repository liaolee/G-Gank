package com.example.liao.g_gank.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.model.data.ContentResult;
import com.example.liao.g_gank.ui.fragment.ContentTypeFragment;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by liao on 2016/5/8.
 */
public class ContentFragmentAdapter extends RecyclerView.Adapter {

    private List<ContentResult> resultList;
    private ContentTypeFragment context;

    public ContentFragmentAdapter(ContentTypeFragment context) {


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
        viewHolder.tagGroup.setTags(resultList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void getGankUrl(int position){

        context.showGankWebView(resultList.get(position));

    }


    class ViewHolder extends RecyclerView.ViewHolder {


        TextView titleText;
        TextView authorText;
        TagGroup tagGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.txt_title);
            authorText = (TextView) itemView.findViewById(R.id.txt_author);
            tagGroup = (TagGroup) itemView.findViewById(R.id.tg_type);

        }


    }
}
