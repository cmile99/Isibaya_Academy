package com.threeklines.isibayaacademyclient;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;

public class MainContainer extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private SelectGrade selectGrade;
    private SelectSubject selectSubject;
    SelectTopic selectTopic;
    private static Fragment currentFragment;
    private ImageView forwardImg;
    private ImageView backImg;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        selectGrade = new SelectGrade();
        selectSubject = new SelectSubject();
        selectTopic = new SelectTopic();
        forwardImg = findViewById(R.id.foward_arrow);
        backImg = findViewById(R.id.back_arrow);
        frameLayout = findViewById(R.id.main_frame);
        replaceFragment(selectGrade);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.drawable.menu_48px);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        forwardImg.setOnClickListener(v -> {
            if (currentFragment == selectGrade) {
                switch (selectGrade.getSelected()) {
                    case R.id.grade_3:
                        Toast.makeText(this, "Grade 3 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.grade_4:
                        Toast.makeText(this, "Grade 4 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.grade_5:
                        Toast.makeText(this, "Grade 5 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.grade_6:
                        Toast.makeText(this, "Grade 6 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.grade_7:
                        Toast.makeText(this, "Grade 7 selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:

                }
                replaceFragment(selectSubject);
            } else if (currentFragment == selectSubject) {
                switch (selectSubject.getSelected()) {
                    case R.id.eng_sub:
                        Toast.makeText(this, "Engilsh Subject Selected", Toast.LENGTH_SHORT).show();
                        replaceFragment(selectTopic);
                        break;
                    case R.id.vpa_sub:
                        Toast.makeText(this, "VPA Subject Selected", Toast.LENGTH_SHORT).show();
                        replaceFragment(selectTopic);
                        break;
                    case R.id.nde_sub:
                        Toast.makeText(this, "Ndebele Subject Selected", Toast.LENGTH_SHORT).show();
                        replaceFragment(selectTopic);
                        break;
                    case R.id.snt_sub:
                        Toast.makeText(this, "Science & Technology Subject Selected", Toast.LENGTH_SHORT).show();
                        replaceFragment(selectTopic);
                        break;
                    default:

                }
            }

        });

        backImg.setOnClickListener(v -> {
            if (currentFragment == selectSubject){
                replaceFragment(selectGrade);
            } else if (currentFragment == selectTopic){
                replaceFragment(selectSubject);
            }
        });
    }


    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment).commit();
        if (fragment != selectGrade){
            backImg.setVisibility(View.VISIBLE);
        }else backImg.setVisibility(View.INVISIBLE);
        currentFragment = fragment;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }

    public static void setCurrentFragment(Fragment fragment){
        currentFragment = fragment;
    }
}