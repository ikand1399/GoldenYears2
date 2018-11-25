package com.gold.goldenyears;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView.OnNavigationItemSelectedListener navSelectListener = new NavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(MenuItem menuItem) {

            Fragment fragment;
            switch(menuItem.getItemId()) {
                case R.id.nav_home:
                    fragment = new Home();
                    break;
                case R.id.nav_profile:
                    fragment = new Profile();
                    break;
                case R.id.nav_settings:
                    fragment = new Settings();
                    break;
                case R.id.nav_legal:
                    fragment = new Legal_Privacy();
                    break;

                default:
                    return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

            menuItem.setChecked(true);
            drawerLayout.closeDrawers();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Home()).commit();

        ActionBar appActionBar = getSupportActionBar();
        if (null != appActionBar) {
            appActionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navSelectListener);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (toggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return false;
    }

    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Welcome")
                .setMessage("This should only be shown once")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}


