package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 어댑터 클래스 정의
 *
 * @author Mike
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<MemoItem> mItems = new ArrayList<MemoItem>();

    public ListAdapter(Context context) {
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
    public void addItem(ArrayList<MemoItem> list){
        this.mItems = list;
    }
    public void addItem(MemoItem item) {
        mItems.add(0, item);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemVIew vIew = new itemVIew(mContext);
        vIew.setText1(mItems.get(position).getMemo());
        return vIew;
    }
}
