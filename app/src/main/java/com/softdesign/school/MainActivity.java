package com.softdesign.school;

import android.app.Notification;
import android.content.ClipData;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VISIBLE_KEY = "Visible";
    public static final String  STATUSBAR_KEY = "sbColor";
    public static final String TOOLBAR_KEY = "tbColor";

    private int mColor;

    CheckBox mCheckBox;
    EditText mEditText;
    EditText mEditText2;
    Toolbar mToolbar;
    Button mButton;
    Button mButton2;
    Button mButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.e(this.getLocalClassName(), "on Create");

        setTitle("Home Task 1");

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mButton = (Button) findViewById(R.id.btn_blue);
        mButton.setOnClickListener(this);

        mButton2 = (Button) findViewById(R.id.btn_green);
        mButton2.setOnClickListener(this);

        mButton3 = (Button) findViewById(R.id.btn_red);
        mButton3.setOnClickListener(this);

        setupToolbar();
    }

    /**
     * Метод проверяющий поддерживает ли устройство Action bar
     * если не поддерживает, то устанавливаем tool bar
     */
    private void setupToolbar () {
        setSupportActionBar(mToolbar);
        Lg.e(this.getLocalClassName(), "use toolbar");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * При клике на Меню всплывает Toast сообщение
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "menu click!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * установка события "клик" на check box и кнопки:
     * При установк флага на checkbox поле edittext2 скрывается
     * При клике на кнопку green меняется цвет action bar и если поддерживается, то и  status bar на зеленый
     * При клике на кнопку red меняется цвет action bar и если поддерживается, то и  status bar на красны
     * При клике на кнопку blue меняется цвет action bar и если поддерживается, то и  status bar на синий
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.checkBox:
                Toast.makeText(this, "Click Check Box!", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    Lg.e(this.getLocalClassName(), "Edit text invisible");
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    Lg.e(this.getLocalClassName(), "Edit text visible");
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_green:
                Lg.e(this.getLocalClassName(), "select the green theme");
                Toast.makeText(this, "You set the green theme!", Toast.LENGTH_SHORT).show();
                setActionBarAndStatusBar(R.color.actionBar_green, R.color.statusBar_greenDark);
                break;
            case R.id.btn_red:
                Lg.e(this.getLocalClassName(), "select the red theme");
                Toast.makeText(this, "You set the red theme!", Toast.LENGTH_SHORT).show();
                setActionBarAndStatusBar(R.color.actionBar_red, R.color.statusBar_redDark);
                break;
            case R.id.btn_blue:
                Lg.e(this.getLocalClassName(), "select the blue theme");
                Toast.makeText(this, "You set the blue theme!", Toast.LENGTH_SHORT).show();
                setActionBarAndStatusBar(R.color.colorPrimary, R.color.colorPrimaryDark);
        }
    }

    /**
     * Метод проверяющий поддерживает ли устройство Action bar, если не поддерживается, то
     * устанавливаем цвет только на tool bar
     */
    private void setActionBarAndStatusBar (int tbColor, int sbColor) {
        mToolbar.setBackgroundResource(tbColor);
        mColor = tbColor;
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(sbColor));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "on Start");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Lg.e(this.getLocalClassName(), "on Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "on Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "on Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "on Destroy");
    }

    /**
     * Метод сохраняющий в себе ключ состояния
     * @param outState объект сохраняющий в себе ключ состояяния
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
        Lg.e(this.getLocalClassName(), "on save instance state");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outState.putInt(STATUSBAR_KEY, getWindow().getStatusBarColor());
        }
        outState.putInt(TOOLBAR_KEY, mColor);
    }

    /**
     * Метод вызывается после onStart, когда происходит повторная инициализация из ранее сохраненного
     * состояния в onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;

        int tbColor = savedInstanceState.getInt(TOOLBAR_KEY);
        mColor = tbColor;
        mToolbar.setBackgroundResource(tbColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int sbColor = savedInstanceState.getInt(STATUSBAR_KEY);
            getWindow().setStatusBarColor(sbColor);
        }

        mEditText2.setVisibility(visibleState);
        Lg.e(this.getLocalClassName(), "on restore instance state");
    }
}
