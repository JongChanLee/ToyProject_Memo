package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tistory.wproject.fragmentpractice.database.ReadDataBase;

/**
 * Created by Lee on 2016-02-06.
 */
public class ListFragment extends Fragment {

    ReadDataBase db;
    ListView listView;
    ListAdapter adapter;
    public static final String DATABASE_NAME = "MemoFragment.db";
    public static final String TABLE_NAME = "MemoTablefrag";
    Context context;

    public ListFragment() {

    }

    public ListFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memolist, container, false);


        listView = (ListView) rootView.findViewById(R.id.memo_list);
        adapter = new ListAdapter(context);

        listView.setAdapter(adapter);
        db = new ReadDataBase(context, DATABASE_NAME, TABLE_NAME);
        String[] dbData = db.getMemo(TABLE_NAME);
        if (dbData != null)
            for (int i = 0; i < dbData.length; i++) {
                adapter.addItem(dbData[i]);
            }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("listener", "click " + adapter.getItem(position));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("listener", "long " + adapter.getItem(position));
                return true;
            }
        });

        adapter.notifyDataSetChanged();
        return rootView;
    }

    public void memoAdd(String memo) {
        adapter.addItem(memo);
        db.intertMemo(TABLE_NAME, memo);

    }

}
