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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkleib.android.playground.R;
import com.rkleib.android.playground.util.Utilities;

import java.util.Objects;
import java.util.regex.Pattern;

public class DialogCustomActivity extends AppCompatActivity {

    TextInputEditText mEtUsername;
    TextInputEditText mEtEmail;
    TextInputEditText mEtPassword;

    TextInputEditText mEtSetUsername;
    TextInputEditText mEtSetEmail;
    TextInputEditText mEtSetPassword;

    TextInputLayout mTiSetUsername;
    TextInputLayout mTiSetEmail;
    TextInputLayout mTiSetPassword;

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

        mTiSetUsername = dialogView.findViewById(R.id.ti_username_dialog);
        mTiSetEmail = dialogView.findViewById(R.id.ti_email_dialog);
        mTiSetPassword = dialogView.findViewById(R.id.ti_password_dialog);
        mEtSetUsername = dialogView.findViewById(R.id.et_username_dialog);
        mEtSetEmail = dialogView.findViewById(R.id.et_email_dialog);
        mEtSetPassword = dialogView.findViewById(R.id.et_password_dialog);
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
                if (isDataValid(mEtSetUsername, mEtSetEmail, mEtSetPassword)) {
                    mEtUsername.setText(mEtSetUsername.getText().toString().trim());
                    mEtEmail.setText(mEtSetEmail.getText().toString().trim());
                    mEtPassword.setText(mEtSetPassword.getText().toString().trim());
                    alertDialog.dismiss();
                } else {
                    setError();
                }
            }
        });
    }

    private boolean isDataValid(TextInputEditText username, TextInputEditText email, TextInputEditText password) {
        boolean isValid = true;

        if (username.getText().toString().trim().isEmpty()) {
            isValid = false;
        }
        if (email.getText().toString().trim().isEmpty()) {
            isValid = false;
        }
        if (!isEmailValid(mEtSetEmail.getText().toString().trim())) {
            isValid = false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public void setErrorState(TextInputLayout textInputLayout) {
        switch (textInputLayout.getId()) {
            case R.id.ti_username_dialog:
                if (mEtSetUsername.getText().toString().trim().isEmpty()) {
                    mTiSetUsername.setError("Username tidak boleh kosong");
                } else {
                    mTiSetUsername.setError(null);
                }
                break;
            case R.id.ti_email_dialog:
                if (mEtSetEmail.getText().toString().isEmpty()) {
                    mTiSetEmail.setError("Email Tidak Boleh Kosong");
                } else if (!isEmailValid(mEtSetEmail.getText().toString().trim())) {
                    mTiSetEmail.setError("Email format salah");
                } else {
                    mTiSetEmail.setError(null);
                }
                break;
            case R.id.ti_password_dialog:
                if (mEtSetPassword.getText().toString().trim().isEmpty()) {
                    mTiSetPassword.setError("Password Tidak Boleh kosong");
                } else {
                    mTiSetPassword.setError(null);
                }
                break;
        }
    }

    private boolean isEmailValid(String email) {
        Pattern utilities = Utilities.EMAIL_ADDRESS_PATTERN;
        return utilities.matcher(email).matches();
    }

    private void setError() {
        onTextChanged(mTiSetUsername, mEtSetUsername);
        onTextChanged(mTiSetEmail, mEtEmail);
        onTextChanged(mTiSetPassword, mEtSetPassword);
    }

    private void onTextChanged(final TextInputLayout textInputLayout, TextInputEditText textInputEditText) {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setErrorState(textInputLayout);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}