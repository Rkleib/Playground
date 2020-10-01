package com.rkleib.android.playground.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkleib.android.playground.R;

public class DialogCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_custom);

        TextInputLayout mTiName = findViewById(R.id.ti_name);
        TextInputEditText mEtName = findViewById(R.id.et_name);
    }

    private void showDialogCustom() {
        AlertDialog.Builder dialogKondisi = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_custom, null);
    }
}