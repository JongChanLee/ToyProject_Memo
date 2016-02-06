package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 어댑터 클래스 정의
 *
 * @author Mike
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;

    private List<String> mItems = new ArrayList<String>();

    public ListAdapter(Context context) {
        Log.d("memo", "어뎁터 생성자 들어옴");
        this.mContext = context;
    }

    @Override
    public int getCount() {

        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    public void addItem(String memo) {
        mItems.add(memo);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("memo", "getView안으로 들어옴");
        itemVIew vIew = new itemVIew(mContext);
        vIew.setText1(mItems.get(position));
        return vIew;
    }
}
