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

    TextInputEditText mEtUsername;
    TextInputEditText mEtEmail;
    TextInputEditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_custom);

        TextInputLayout mTiUsername = findViewById(R.id.ti_username);
        TextInputLayout mTiEmail = findViewById(R.id.ti_email);
        TextInputLayout mTiPassword = findViewById(R.id.ti_password);
        mEtUsername = findViewById(R.id.et_username);
        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        MaterialButton mBtnSimpan = findViewById(R.id.btn_show_dialog);

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCustom();
            }
        });
    }

    private void showDialogCustom() {
        final AlertDialog.Builder dialogKondisi = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        dialogKondisi.setView(dialogView);
        dialogKondisi.setCancelable(true);
        dialogKondisi.setIcon(R.mipmap.ic_launcher);

        TextInputLayout mTiSetUsername = dialogView.findViewById(R.id.ti_username);
        TextInputLayout mTiSetEmail = dialogView.findViewById(R.id.ti_email);
        TextInputLayout mTiSetPassword = dialogView.findViewById(R.id.ti_password);
        final TextInputEditText mEtSetUsername = dialogView.findViewById(R.id.et_username);
        final TextInputEditText mEtSetEmail = dialogView.findViewById(R.id.et_email);
        final TextInputEditText mEtSetPassword = dialogView.findViewById(R.id.et_password);
        MaterialButton mBtnSetSimpan = dialogView.findViewById(R.id.btn_set_simpan);
        Toolbar mToolbar = dialogView.findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setTitle("Tambah Kondisi Promo");

        final AlertDialog alertDialog = dialogKondisi.create();
        alertDialog.show();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBtnSetSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtUsername.setText(mEtSetUsername.getText().toString().trim());
                mEtEmail.setText(mEtSetEmail.getText().toString().trim());
                mEtPassword.setText(mEtSetPassword.getText().toString().trim());
                alertDialog.dismiss();
            }
        });
    }
}