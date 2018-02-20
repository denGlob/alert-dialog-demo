package com.denshiksmle.alert_dialog_demo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by denglob on 2/21/18.
 */

public class Fragment extends android.support.v4.app.Fragment {

    public static final String TAG = "FragmentTag";

    private TextView mStateName;
    private Button mChangeState;
    private AlertDialog alertDialog;

    private boolean state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment, container, false);
        mStateName = view.findViewById(R.id.content);
        mChangeState = view.findViewById(R.id.button);

        final String content = "LOOOOOL";
        createDialog("KEEEK");

        mStateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog != null) {
                    alertDialog.show();
                }
            }
        });

        mChangeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state = !state;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (state) {
            if (alertDialog != null) {
                alertDialog.show();
            }
            else {
                createDialog("KEEEK");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void createDialog(String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_layout, null);
        builder.setView(view);

        final EditText editText = view.findViewById(R.id.editText);
        TextView textView = view.findViewById(R.id.title);
        Button button = view.findViewById(R.id.button2);

        textView.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    mStateName.setText(editText.getText().toString());
                }
            }
        });

        alertDialog = builder.create();
    }
}
