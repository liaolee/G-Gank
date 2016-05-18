package com.example.liao.g_gank.persenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import com.example.liao.g_gank.contract.ContentTypeContrect;
import com.example.liao.g_gank.ui.fragment.ContentFragment;
import com.example.liao.g_gank.utils.MySQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016/5/9.
 */
public class ContentTypePersenter extends Fragment implements ContentTypeContrect.IContentTypePersenter {

    ContentFragment contentFragment;
    private Cursor cursor;
    private SQLiteDatabase writableDatabase;
    private String type;

    public ContentTypePersenter(ContentFragment contentFragment) {

        this.contentFragment = contentFragment;
    }

    @Override
    public void readDB() {

        MySQLite mySQLite = new MySQLite(contentFragment.getContext(), "db", null, 1);
        writableDatabase = mySQLite.getWritableDatabase();

        List<String> types = new ArrayList<>();

        cursor = writableDatabase.query("follow", null, null, null, null, null, null);

        if (cursor.getCount() == 0) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("type", "all");

            writableDatabase.insert("follow", null, contentValues);
            types.add("all");

        } else {

            while (cursor.moveToNext()) {

                type = cursor.getString(cursor.getColumnIndex("type"));
                types.add(type);
            }
        }

        contentFragment.showType(types);

    }


}
