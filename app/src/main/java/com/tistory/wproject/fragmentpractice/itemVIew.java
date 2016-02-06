package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lee on 2016-01-26.
 */
public class itemVIew extends LinearLayout {
    TextView textView;
    LayoutInflater inflater;

    public itemVIew(final Context context) {
        super(context);
        init(context);
    }

    public itemVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);
        textView = (TextView) findViewById(R.id.memo_text);
    }

    public void setText1(String memo) {
        textView.setText(memo);
    }

}

