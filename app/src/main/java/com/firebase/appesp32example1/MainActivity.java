package com.firebase.appesp32example1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private MyViewPagerAdapter myViewPagerAdapter;

    private BottomNavigationView bottomNavigationView;

    String github = "Đây là dữ liệu mới";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        actionBottomNavigation();

    }

    private void Init() {
        viewPager2 = findViewById(R.id.view_pager2);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

    private void actionBottomNavigation(){
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home){
                    viewPager2.setCurrentItem(0);
                }else if (id == R.id.bottom_warning){
                    viewPager2.setCurrentItem(1);
                }else if (id == R.id.bottom_account){
                    viewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_warning).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_account).setChecked(true);
                        break;
                }
            }
        });
    }
}