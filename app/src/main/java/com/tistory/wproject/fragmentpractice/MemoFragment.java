package com.tistory.wproject.fragmentpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lee on 2016-02-06.
 */
public class MemoFragment extends Fragment {

    EditText editText;
    Button input, cancel;

    public MemoFragment() {

    }

    public MemoFragment(String memo) {
        editText.setText(memo);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_memo, container, false);

        init(rootview);

        return rootview;
    }

    private  void init(View view){
        editText = (EditText) view.findViewById(R.id.input_memo);
        input = (Button) view.findViewById(R.id.bt_input);
        cancel = (Button) view.findViewById(R.id.bt_cancel);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoFragmentListener.onMemoFragmentListener(input);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoFragmentListener.onMemoFragmentListener(cancel);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        memoFragmentListener = (MemoFragmentListener) context;
    }

    private  MemoFragmentListener memoFragmentListener;
    public interface MemoFragmentListener {
        public void onMemoFragmentListener(View view);

    }


}
