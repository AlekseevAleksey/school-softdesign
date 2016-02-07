package com.softdesign.school.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SetingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;
import com.softdesign.school.utils.RoundImage;


public class MainActivity extends AppCompatActivity {

    private static final String VISABLE_KEY = "VISABLE";

    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;
    private Fragment mFragment;
    private FrameLayout mFrameConteiner;
    private ImageView mImageView;



    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.e(this.getLocalClassName(), "on Create");

        setTitle("Home Task 3");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner, new ProfileFragment()).commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerLayout = mNavigationView.getHeaderView(0);
        mImageView = (ImageView) headerLayout.findViewById(R.id.drawer_image);

        setupToolbar();
        setupDrawer();

    }

    /**
     * Метод проверяющий поддерживает ли устройство Action bar
     * если не поддерживает, то устанавливаем tool bar
     */
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        Lg.e(this.getLocalClassName(), "use toolbar");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp1);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * При клике на Меню всплывает Toast сообщение
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
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
     * Метод опрорисовки Navigation Drawer и переход по пункатам меню
     */
    private void setupDrawer() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);

        bm = RoundImage.getCirle(bm, (int) getResources().getDimension(R.dimen.drawer_header_profile_image_size));
        mImageView.setImageBitmap(bm);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        break;
                    case R.id.drawer_settings:
                        mFragment = new SetingsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_settings).setChecked(true);
                        break;
                }

                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner, mFragment).addToBackStack(null).commit();
                }

                mNavigationDrawer.closeDrawers();
                return false;
            }
        });
    }

    /**
     * При нажатии кнопки "Назад" проверяется состояние шторки.
     * Если шторка открыта, то её закрываем с помощью метода closeDrawer().
     * При попадании на фрагмент "Профиль", приложение завершается.
     */
    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawer.closeDrawers();
        } else {
            super.onBackPressed();
        }

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
          finish();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack();
            mFragment = fragmentManager.findFragmentById(R.id.main_frame_conteiner);
        }
    }



}
