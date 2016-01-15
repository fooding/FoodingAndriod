package com.example.yoonmin.sgen;

import android.annotation.TargetApi;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        setContentView(R.layout.activity_main);

        createTab();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Example.class);
                startActivity(intent);
            }
        });

    }

    private void createTab() {

        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(getLocalActivityManager());

        tabHost.addTab(tabHost.newTabSpec("TAB1").setIndicator("", getResources().getDrawable(R.drawable.newsfeed))
                .setContent(new Intent(this, NewsFeed.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB2").setIndicator("", getResources().getDrawable(R.drawable.compass))
                .setContent(new Intent(this, Featured_Discover.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB3").setIndicator("", getResources().getDrawable(R.drawable.me))
                .setContent(new Intent(this, Me.class)));

    }

}
