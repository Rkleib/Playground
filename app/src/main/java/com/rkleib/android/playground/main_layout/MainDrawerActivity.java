package com.rkleib.android.playground.main_layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.rkleib.android.playground.MainActivity;
import com.rkleib.android.playground.R;


public class MainDrawerActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean isCollapsed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        handleSideBar();
        handleCollapsingToolbar();
    }

    private void handleSideBar() {
        drawerLayout = findViewById(R.id.main_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.navigation_view);

        ImageView mImvBurger = findViewById(R.id.imv_burger);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                if (isCollapsed) {
                    getWindow().getDecorView().setSystemUiVisibility(0);
                } else {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mImvBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.menu_home:
                        Toast.makeText(MainDrawerActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_profile:
                        Toast.makeText(MainDrawerActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainDrawerActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_logout:
                        showLogoutDialog();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void handleCollapsingToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.colapsing_toolbar);
        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);

        collapsingToolbar.setTitle("Welcome");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    // - expanded
                    isCollapsed = false;
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    // -collapsed
                    isCollapsed = true;
                    getWindow().getDecorView().setSystemUiVisibility(0);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainDrawerActivity.this,
                R.style.CustomAlertDialog);
        alert.setMessage("Anda yakin akan keluar dari aplikasi ?");
        alert.setCancelable(true);

        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.runFinalization();
                Runtime.getRuntime().gc();
                System.gc();
                System.exit(0);
                MainDrawerActivity.this.finish();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainDrawerActivity.this,
                R.style.CustomAlertDialog);
        alert.setMessage("Anda yakin akan logout ?");
        alert.setCancelable(true);

        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainDrawerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }
}