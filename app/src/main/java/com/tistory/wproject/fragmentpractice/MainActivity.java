package com.tistory.wproject.fragmentpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tistory.wproject.fragmentpractice.database.ReadDataBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MemoFragment.MemoFragmentListener {
    ListFragment list_fragment;
    MemoFragment memo_fragment;
    ArrayList<MemoItem> memolist = new ArrayList<>();
    ReadDataBase db;
    public static final String DATABASE_NAME = "MemoFragment.db";
    public static final String TABLE_NAME = "MemoTablefrag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_fragment = new ListFragment(getApplicationContext());

        db = new ReadDataBase(getApplicationContext(), DATABASE_NAME, TABLE_NAME);
        String[] dbData = db.getMemo(TABLE_NAME);
        int[] id = db.getId(TABLE_NAME);
        if (dbData != null) {
            for (int i = 0; i < dbData.length; i++) {
                memolist.add(new MemoItem(id[i], dbData[i], "NEW"));
            }
            list_fragment.memoAdd(memolist);
        }

        if (savedInstanceState == null) {
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
            case R.id.bt_input:
                MemoItem newMemo = new MemoItem(this.memolist.size(), memo_fragment.editText.getText().toString(), "NEW");
                list_fragment.memoAdd(newMemo);
                getSupportFragmentManager().beginTransaction().remove(memo_fragment).commit();

                break;
        }
    }


}

