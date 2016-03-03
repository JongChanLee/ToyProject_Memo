package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lee on 2016-02-06.
 */
public class ListFragment extends Fragment {

    ListView listView;
    ListAdapter adapter;
    Context context;

    public ListFragment(Context context) {
        this.context = context;
        this.adapter = new ListAdapter(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memolist, container, false);

        listView = (ListView) rootView.findViewById(R.id.memo_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onItemClicked(view);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onItemLongClicked(view);
                return true;
            }
        });
        return rootView;
    }

    public void memoAdd(ArrayList<MemoItem> list) {
        adapter.addItem(list);
        adapter.notifyDataSetChanged();
    }

    public void memoAdd(MemoItem item) {
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (onItemClicked) context;
    }

    onItemClicked mListener;

    public interface onItemClicked {
        public void onItemClicked(View view);

        public void onItemLongClicked(View view);
    }
}
