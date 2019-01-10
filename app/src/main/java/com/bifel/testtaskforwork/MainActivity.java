package com.bifel.testtaskforwork;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), 3);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);

        pagerTabStrip = findViewById(R.id.pageMonitor);
        pagerTabStrip.setTabIndicatorColor(Color.BLACK);
    }
}
