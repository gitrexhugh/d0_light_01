package com.example.rex_h.d0_light_01;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class main_Activity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        getSupportActionBar().hide();//Hide default APP title(only for AppCompatActivity)
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new backlight_torch(), "Torch");
        adapter.addFragment(new backlight_cam_frame(), "Camera");
        adapter.addFragment(new screenlight_cam_frame(), "Sifle");
        adapter.addFragment(new screenlight(), "Screen Light");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        int[] tabIcons = {
                R.mipmap.backlight0_xxxhdpi,
                R.mipmap.backlight_camera0_xxxhdpi,
                R.mipmap.screenlight_camera0_xxxhdpi,
                R.mipmap.screenlight0_xxxhdpi
                //R.drawable.backlight0_48,
                //R.drawable.backlight_camera_48,
                //R.drawable.screenlight0_48,
                //R.drawable.screenlight_camera0_48
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

}
