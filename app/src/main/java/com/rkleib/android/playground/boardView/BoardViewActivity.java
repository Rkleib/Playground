package com.rkleib.android.playground.boardView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.allyants.boardview.BoardAdapter;
import com.allyants.boardview.BoardItem;
import com.allyants.boardview.BoardView;
import com.allyants.boardview.SimpleBoardAdapter;
import com.rkleib.android.playground.R;
import com.rkleib.android.playground.adapter.BoardViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class BoardViewActivity extends AppCompatActivity {

    ArrayList<BoardAdapter.Column> data = new ArrayList<>();
    ArrayList<String> open = new ArrayList<>();
    ArrayList<String> onProgress = new ArrayList<>();
    ArrayList<String> test = new ArrayList<>();
    ArrayList<String> done = new ArrayList<>();

    int indexes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_view);
        final BoardView boardView = findViewById(R.id.boardView);
        boardView.SetColumnSnap(false);

        done.add("Item 1");
        done.add("Item 2");
        done.add("Item 3");

        data.add(new BoardViewAdapter.CustomColumn("Open", (ArrayList) open));
        data.add(new BoardViewAdapter.CustomColumn("On Progress", (ArrayList) onProgress));
        data.add(new BoardViewAdapter.CustomColumn("Test", (ArrayList) test));
        data.add(new BoardViewAdapter.CustomColumn("Done", (ArrayList) done));

        final BoardViewAdapter adapter = new BoardViewAdapter(this, data);
        boardView.setAdapter(adapter);
        boardView.setOnHeaderClickListener(new BoardView.HeaderClickListener() {
            @Override
            public void onClick(View view, int position) {
                indexes = adapter.getItemCount(position);
                showDialogAdd(adapter, position, indexes);
            }
        });
    }

    private void showDialogAdd(final BoardViewAdapter adapter, final int position, final int index) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_add_item, null);

        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);

        final AppCompatEditText mEtItemTitle = dialogView.findViewById(R.id.et_item);
        Button mBtnSimpan = dialogView.findViewById(R.id.btn_simpan);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(position, index, mEtItemTitle.getText().toString().trim());
                alertDialog.dismiss();
            }
        });
    }
}