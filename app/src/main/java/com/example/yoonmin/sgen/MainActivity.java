package com.example.yoonmin.sgen;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTab();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Write.class);
                startActivity(intent);
            }
        });

    }

    private void createTab() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(getLocalActivityManager());

        tabHost.addTab(tabHost.newTabSpec("TAB1").setIndicator("NewsFeed")
                .setContent(new Intent(this, NewsFeed.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB2").setIndicator("F & D")
                .setContent(new Intent(this, Featured_Discover.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB3").setIndicator("Me")
                .setContent(new Intent(this, Me.class)));

    }

}
