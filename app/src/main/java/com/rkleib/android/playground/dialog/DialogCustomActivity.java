package com.rkleib.android.playground.dialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkleib.android.playground.R;

import java.util.Objects;

public class DialogCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_custom);

        TextInputLayout mTiName = findViewById(R.id.ti_name);
        TextInputEditText mEtName = findViewById(R.id.et_name);
        MaterialButton mBtnSimpan = findViewById(R.id.btn_show_dialog);

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCustom();
            }
        });
    }

    private void showDialogCustom() {
        AlertDialog.Builder dialogKondisi = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        dialogKondisi.setView(dialogView);
        dialogKondisi.setCancelable(true);
        dialogKondisi.setIcon(R.mipmap.ic_launcher);

        TextInputLayout mTiUsername = findViewById(R.id.ti_username);
        TextInputLayout mTiEmail = findViewById(R.id.ti_email);
        TextInputLayout mTiPassword = findViewById(R.id.ti_password);
        TextInputEditText mEtUsername = findViewById(R.id.et_username);
        TextInputEditText mEtEmail = findViewById(R.id.et_email);
        TextInputEditText mEtPassword = findViewById(R.id.et_password);
        MaterialButton mBtnSimpan = findViewById(R.id.btn_simpan);
        Toolbar mToolbar = dialogView.findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setTitle("Tambah Kondisi Promo");

        AlertDialog alertDialog = dialogKondisi.create();
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}