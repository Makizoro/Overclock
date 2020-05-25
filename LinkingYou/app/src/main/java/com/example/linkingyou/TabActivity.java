package com.example.linkingyou;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.linkingyou.ui.main.SectionsPagerAdapter;

public class TabActivity extends AppCompatActivity {

    Button ButtonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        ButtonCreate = (Button) findViewById(R.id.ButtonCreate);
        ButtonCreate.setVisibility(View.INVISIBLE);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        TabLayout.Tab firstTab = tabs.newTab(); // Create a new Tab names
        firstTab.setText("First Tab"); // set the Text for the first Tab
        //firstTab.setIcon(R.drawable.ic_launcher); // set an icon for the first tab
        tabs.addTab(firstTab);
        tabs.setupWithViewPager(viewPager);
        final FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonCreate.setVisibility(View.VISIBLE);
                ButtonCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent TabsIntent = new Intent(TabActivity.this, GatheringDescription.class);
                        //TabsIntent.putExtra("userS",username);
                        startActivity(TabsIntent);
                    }
                });
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ButtonCreate.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}