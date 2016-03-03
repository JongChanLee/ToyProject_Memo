package com.tistory.wproject.fragmentpractice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tistory.wproject.fragmentpractice.database.ReadDataBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MemoFragment.MemoFragmentListener, ListFragment.onItemClicked {
    ListFragment list_fragment;
    MemoFragment memo_fragment;
    ArrayList<MemoItem> memolist = new ArrayList<>();
    ReadDataBase db;
    public static final String DATABASE_NAME = "MemoFragment.db";
    public static final String TABLE_NAME = "MemoTablefrag";
    NomalFragment nomalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_fragment = new ListFragment(getApplicationContext());
        Log.d("test", "onCreate 호출됨");
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

    @Override
    public void onItemClicked(View view) {
        itemVIew memo_view = (itemVIew) view;
        String memo = memo_view.textView.getText().toString();
        nomalFragment = new NomalFragment(memo);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, nomalFragment).addToBackStack(null).commit();

    }

    @Override
    public void onItemLongClicked(View view) {

        Dialog dialog = createDialogBox(view);
        dialog.show();
    }

    private AlertDialog createDialogBox(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("메모 삭제");
        builder.setMessage("메모를 삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeMemo(view);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "삭제를 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }


    @Override
    protected void onPause() {
        db.deleteAll(TABLE_NAME);
        Log.d("test", "onStop 호출됨");

        for (int i = 0; i < memolist.size(); i++) {
            db.intertMemo(TABLE_NAME, memolist.get(i).getMemo());
        }

        super.onPause();
    }

    private void removeMemo(View view){
        itemVIew memo_delete = (itemVIew) view;
        String memo = memo_delete.textView.getText().toString();
        for (int i = 0 ; i < memolist.size() ; i++){
            if (memolist.get(i).getMemo() == memo) {
                memolist.remove(i);
                break;
            }
        }
        list_fragment.memoAdd(memolist);
    }


}

