package com.bifel.testtaskforwork;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), 3);
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);

        PagerTabStrip pagerTabStrip = findViewById(R.id.pageMonitor);
        pagerTabStrip.setTabIndicatorColor(Color.BLACK);
    }
}
