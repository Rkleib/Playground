package com.rkleib.android.playground.main_layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rkleib.android.playground.adapter.ExpandableListAdapter;

import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.rkleib.android.playground.ExpandedMenuModel;
import com.rkleib.android.playground.MainActivity;
import com.rkleib.android.playground.R;
import com.rkleib.android.playground.boardView.BoardViewActivity;
import com.rkleib.android.playground.dialog.DialogBasicActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;
    private boolean isCollapsed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        mDrawerLayout = findViewById(R.id.main_layout);
        expandableList = findViewById(R.id.navigation_menu);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList) {
        };

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition,
                                        int childPosition, long childId) {
                if (groupPosition == 0) {
                    switch (childPosition) {
                        case 0:
                            Intent intent = new Intent(MainDrawerActivity.this, DialogBasicActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
                if (groupPosition == 1) {
                    switch (childPosition) {
                        case 0:
                            Intent intent = new Intent(MainDrawerActivity.this, BoardViewActivity.class);
                            startActivity(intent);
                            break;
                    }
                }

                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Log.d("DEBUG", "heading clicked");
                return false;
            }
        });

        handleSideBar();
        handleCollapsingToolbar();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("Dialog");
        item1.setIconImg(R.drawable.ic_menu_profile);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("BoardView");
        item2.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("heading3");
        item3.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item3);

        // Adding child data
        List<String> dialog = new ArrayList<String>();
        dialog.add("Dialog Basic");

        // Adding child data
        List<String> boardView = new ArrayList<String>();
        boardView.add("BoardView");

        listDataChild.put(listDataHeader.get(0), dialog);// Header, Child data
        listDataChild.put(listDataHeader.get(1), boardView);// Header, Child data

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //revision: this don't works, use setOnChildClickListener() and setOnGroupClickListener() above instead
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void handleSideBar() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        ImageView mImvBurger = findViewById(R.id.imv_burger);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
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
                mDrawerLayout.openDrawer(GravityCompat.START);
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        });
        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
        });*/
    }

    private void handleCollapsingToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.colapsing_toolbar);
        final AppBarLayout appBar = findViewById(R.id.app_bar);

        collapsingToolbar.setTitle("Welcome");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CoordinatorLayout parent = findViewById(R.id.layout_parent);
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