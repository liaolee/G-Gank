package com.example.liao.g_gank.ui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liao.g_gank.R;
import com.example.liao.g_gank.ui.activity.ChoiceTypeActivity;
import com.example.liao.g_gank.utils.MySQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/9.
 */
public class ChoiceTypeAdapter extends BaseAdapter {

    Context context;
    ListView listView;
    String[] allType;
    List<String> type_list;
    List<String> list;
    private LayoutInflater inflater;
    private SQLiteDatabase writableDatabase;
    private Cursor follow;
    private ContentValues contentValues;
    private boolean isSeleted;


    public ChoiceTypeAdapter(ChoiceTypeActivity choiceTypeActivity, ListView listView, List<String> type_list, String[] allType) {

        this.context = choiceTypeActivity;
        this.listView = listView;
        this.allType = allType;
        this.type_list = type_list;

        init();

    }

    private void init() {


        inflater = LayoutInflater.from(context);
        MySQLite mySQLite = new MySQLite(context, "db", null, 1);
        writableDatabase = mySQLite.getWritableDatabase();
        list = new ArrayList<>();
        contentValues = new ContentValues();
    }

    @Override
    public int getCount() {
        return allType.length;
    }

    @Override
    public Object getItem(int position) {
        return allType[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_choice_list, null);

            viewHolder.titleText = (TextView) convertView.findViewById(R.id.txt_type_title);
            viewHolder.imgLine = (ImageView) convertView.findViewById(R.id.img_line);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleText.setText(allType[position]);

        if(!isSeleted){

            setDefault(viewHolder, position);
        }

        return convertView;
    }

    public void setDefault(ViewHolder viewHolder, int position) {

        if (type_list != null) {

            if (type_list.contains(allType[position])) {

                listView.setItemChecked(position, true);
                viewHolder.imgLine.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));


            }
        } else {

            listView.setItemChecked(position, false);
            viewHolder.imgLine.setBackgroundColor(context.getResources().getColor(R.color.colorDef));

        }
    }

    public void seleted(View convertView, ViewHolder viewHolder, int position) {

        isSeleted = true;
        if (!listView.isItemChecked(position)) {

            listView.setItemChecked(position, false);
            viewHolder.imgLine.setBackgroundColor(context.getResources().getColor(R.color.colorDef));

            writableDatabase.delete("follow", "type = ?", new String[]{allType[position]});

        } else {

            listView.setItemChecked(position, true);
            viewHolder.imgLine.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));


            follow = writableDatabase.query("follow", null, null, null, null, null, null);
            while (follow.moveToNext()) {

                list.add(follow.getString(0));

            }

            if (!list.contains(allType[position])) {

                contentValues.clear();
                contentValues.put("type", allType[position]);
                writableDatabase.insert("follow", null, contentValues);

            }
        }


    }



    public class ViewHolder {

        TextView titleText;
        ImageView imgLine;
    }

}
