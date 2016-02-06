package com.tistory.wproject.fragmentpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MemoFragment.MemoFragmentListener{
    ListFragment list_fragment;
    MemoFragment memo_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_fragment = new ListFragment(getApplicationContext());
        if (savedInstanceState == null) {
            Log.d("memo","list프레그먼트 commit하기전");
            getSupportFragmentManager().beginTransaction().add(R.id.container, list_fragment).commit();
        }

    }

    public void onButton1Clicked(View view) {
        memo_fragment = new MemoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, memo_fragment).commit();
    }

    @Override
    public void onMemoFragmentListener(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                getSupportFragmentManager().beginTransaction().remove(memo_fragment).commit();
                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.input_memo:
                TextView memoView = (TextView) view;
                String memo = memoView.getText().toString();
                list_fragment.memoAdd(memo);
                getSupportFragmentManager().beginTransaction().remove(memo_fragment).commit();

                break;
        }
    }


}

