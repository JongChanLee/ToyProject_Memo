package com.tistory.wproject.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lee on 2016-02-06.
 */
public class NomalFragment extends Fragment {
    TextView textView;
    String memo;
    Button returnButton;
    ListFragment list_fragment;

    public NomalFragment(String memo){
        this.memo = memo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_normal, container, false);
        list_fragment = new ListFragment(getContext());
        textView = (TextView) rootview.findViewById(R.id.normal_text);
        returnButton = (Button) rootview.findViewById(R.id.returnToList);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        textView.setText(memo);
        return rootview;

    }



}
