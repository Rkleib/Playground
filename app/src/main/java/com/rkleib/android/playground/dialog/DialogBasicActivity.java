package com.rkleib.android.playground.dialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rkleib.android.playground.R;

import java.util.Objects;

public class DialogBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_basic);
        setupToolbar();
        showAlertDialog();
        showCustomAlert();
    }

    /**
     * Setup Toolbar UI.
     */
    protected void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.gray));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("Basic Dialog");
        //-- make status bar icon dark
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlertDialog() {
        Button mBtnAlert = findViewById(R.id.btn_alert_dialog);
        mBtnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(DialogBasicActivity.this,
                        R.style.CustomAlertDialog);
                alert.setMessage("This is Basic Alert Dialog");
                alert.setCancelable(true);

                alert.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View view = findViewById(android.R.id.content);
                        Snackbar.make(view, "Positive Button Clicked", Snackbar.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
                Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setTextColor(ContextCompat.getColor(DialogBasicActivity.this,
                        R.color.colorPrimaryDark));
                Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonNegative.setTextColor(ContextCompat.getColor(DialogBasicActivity.this,
                        R.color.colorPrimaryDark));
            }
        });
    }

    private void showCustomAlert() {
        Button mBtnCustomAlert = findViewById(R.id.btn_custom_alert);
        mBtnCustomAlert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DialogBasicActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_alert, null);

                alert.setView(dialogView);
                alert.setCancelable(true);
                alert.setIcon(R.mipmap.ic_launcher);

                Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
                TextView tvTitle = dialogView.findViewById(R.id.tv_title);
                TextView tvMessage = dialogView.findViewById(R.id.tv_message);

                final AlertDialog alertDialog = alert.create();
                alertDialog.show();

                //- make dialog background transparent so it can be rounded using cardView
                Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View rootView = findViewById(android.R.id.content);
                        Snackbar.make(rootView, "Button Confirm Clicked", Snackbar.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}