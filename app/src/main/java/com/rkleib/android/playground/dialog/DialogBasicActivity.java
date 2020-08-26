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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rkleib.android.playground.R;

import java.util.ArrayList;
import java.util.Objects;

public class DialogBasicActivity extends AppCompatActivity {

    private String selection = "";
    private ArrayList<String> multipleSelectionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_basic);
        setupToolbar();
        showAlertDialog();
        showCustomAlert();
        showAlertSelection();
        showDialogMultiple();
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

    private void showAlertSelection() {
        Button mBtnSelectionDialog = findViewById(R.id.btn_custom_selection);
        mBtnSelectionDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection = "";
                AlertDialog.Builder alert = new AlertDialog.Builder(DialogBasicActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_selection, null);

                alert.setView(dialogView);
                alert.setCancelable(true);
                alert.setIcon(R.mipmap.ic_launcher);

                RadioGroup mRadioGroup = dialogView.findViewById(R.id.radio_group);
                final RadioButton mRadio1 = dialogView.findViewById(R.id.radio_child_1);
                final RadioButton mRadio2 = dialogView.findViewById(R.id.radio_child_2);
                final RadioButton mRadio3 = dialogView.findViewById(R.id.radio_child_3);
                RadioButton mRadio4 = dialogView.findViewById(R.id.radio_child_4);

                Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

                final AlertDialog alertDialog = alert.create();
                alertDialog.show();

                //- make dialog background transparent so it can be rounded using cardView
                Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));

                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int position) {
                        if (mRadio1.isChecked()) {
                            selection = "Item 1";
                        } else if (mRadio2.isChecked()) {
                            selection = "Item 2";
                        } else if (mRadio3.isChecked()) {
                            selection = "Item 3";
                        } else {
                            selection = "Item 4";
                        }
                    }
                });

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selection.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Need to pick at least 1 item",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            View rootView = findViewById(android.R.id.content);
                            Snackbar.make(rootView, selection + " is selected", Snackbar.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
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

    private void showDialogMultiple() {
        Button mBtnMultiple = findViewById(R.id.btn_custom_multiple);
        mBtnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multipleSelectionList.clear();
                AlertDialog.Builder alert = new AlertDialog.Builder(DialogBasicActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_multiple_selection, null);

                alert.setView(dialogView);
                alert.setCancelable(true);
                alert.setIcon(R.mipmap.ic_launcher);

                Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
                CheckBox mCb1 = dialogView.findViewById(R.id.cb_1);
                CheckBox mCb2 = dialogView.findViewById(R.id.cb_2);
                CheckBox mCb3 = dialogView.findViewById(R.id.cb_3);
                CheckBox mCb4 = dialogView.findViewById(R.id.cb_4);

                mCb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {
                        if (isActive) {
                            multipleSelectionList.add("Item 1");
                        } else {
                            multipleSelectionList.remove("Item 1");
                        }
                    }
                });

                mCb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {
                        if (isActive) {
                            multipleSelectionList.add("Item 2");
                        } else {
                            multipleSelectionList.remove("Item 2");
                        }
                    }
                });

                mCb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {
                        if (isActive) {
                            multipleSelectionList.add("Item 3");
                        } else {
                            multipleSelectionList.remove("Item 3");
                        }
                    }
                });

                mCb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isActive) {
                        if (isActive) {
                            multipleSelectionList.add("Item 4");
                        } else {
                            multipleSelectionList.remove("Item 4");
                        }
                    }
                });

                final AlertDialog alertDialog = alert.create();
                alertDialog.show();

                //- make dialog background transparent so it can be rounded using cardView
                Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));


                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View rootView = findViewById(android.R.id.content);
                        if (multipleSelectionList.size() == 0) {
                            Toast.makeText(getApplicationContext(), "Need to pick at least 1 item",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            StringBuilder builder = new StringBuilder();
                            for (String s : multipleSelectionList) {
                                if (multipleSelectionList.size() > 1) {
                                    builder.append(s + " , ");
                                } else {
                                    builder.append(s);
                                }
                            }
                            Snackbar.make(rootView, builder.toString() + " is selected", Snackbar.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
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