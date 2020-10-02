package com.rkleib.android.playground;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.rkleib.android.playground.main_layout.MainBottomActivity;
import com.rkleib.android.playground.main_layout.MainDrawerActivity;
import com.rkleib.android.playground.main_layout.MainNormalActivity;

public class MainActivity extends AppCompatActivity {

    private View rootView;
    private CardView mCvNavigationDrawer;
    private CardView mCvNormalLayout;
    private CardView mCvBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(android.R.id.content);
        mCvNavigationDrawer = findViewById(R.id.cv_navigation_drawer);
        mCvNormalLayout = findViewById(R.id.cv_normal_layout);
        mCvBottomMenu = findViewById(R.id.cv_bottom_menu);
        handleOnClick();
    }

    private void handleOnClick() {
        mCvBottomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainBottomActivity.class);
                startActivity(intent);
            }
        });

        mCvNormalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainNormalActivity.class);
                startActivity(intent);
            }
        });

        mCvNavigationDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void comingSoon() {
        Snackbar.make(rootView, "Coming Soon", Snackbar.LENGTH_LONG)
                .show();
    }
}