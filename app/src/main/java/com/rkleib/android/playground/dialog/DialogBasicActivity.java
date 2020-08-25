package com.rkleib.android.playground.dialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.rkleib.android.playground.R;

public class DialogBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_basic);
        setupToolbar();
    }

    /**
     * Setup Toolbar UI.
     */
    protected void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("Basic Dialog");
    }
}