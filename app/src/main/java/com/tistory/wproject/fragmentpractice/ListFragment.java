package com.tistory.wproject.fragmentpractice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
                Toast.makeText(getContext(), "일반클릭", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = createDialogBox(view);
                dialog.show();
                return true;
            }
        });
        return rootView;
    }

    public void memoAdd(ArrayList<MemoItem> list) {
        adapter.addItem(list);
    }

    public void memoAdd(MemoItem item) {
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }

    private AlertDialog createDialogBox(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("메모 삭제");
        builder.setMessage("메모를 삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "삭제를 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
