package com.tistory.wproject.fragmentpractice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lee on 2016-01-26.
 */
public class itemVIew extends LinearLayout {
    TextView textView;
    Button button;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    float X_down = 0, X_up = 0;
    boolean isSwiped = false;
    LayoutInflater inflater;

    public itemVIew(final Context context) {
        super(context);
        Log.d("memo", "itemVIew 초기화 들어옴");
        init(context);
    }

    public itemVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("memo", "itemVIew 초기화 들어옴");
        init(context);
    }

    private void init(final Context context) {
        Log.d("memo", "init 들어옴");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d("memo", "인플레이터 하기전");
        inflater.inflate(R.layout.listitem, this, true);
        Log.d("memo", "인플레이터 함");
        textView = (TextView) findViewById(R.id.memo_text);
        translateLeftAnim = AnimationUtils.loadAnimation(context, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(context, R.anim.translate_right);
        button = (Button) findViewById(R.id.delete_bt);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*

                여기를 채워야함

                 */
            }
        });

    }

    public void setText1(String memo) {
        textView.setText(memo);
    }

    private AlertDialog createDialogBox(final TextView view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("메모 삭제");
        builder.setMessage("메모를 삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String memo = view.getText().toString();
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

