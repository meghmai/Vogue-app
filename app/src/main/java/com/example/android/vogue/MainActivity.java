package com.example.android.vogue;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

            pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(adapter);

            TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
        } catch (Exception e) {
        }
    }
}
