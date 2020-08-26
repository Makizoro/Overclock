package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navProfile:
                String type = getIntent().getStringExtra("def");

                Intent i = new Intent(Navigation.this,Profile.class);
                i.putExtra("def",type);

                startActivity(i);
                break;
            case R.id.navCSI:
                String typeCSI = getIntent().getStringExtra("def");

                Intent iCSI = new Intent(Navigation.this,TabCSILoad.class);
                iCSI.putExtra("def",typeCSI);

                startActivity(iCSI);
                break;
            case R.id.navEvent:
                String typeE = getIntent().getStringExtra("def");

                Intent iEve = new Intent(Navigation.this,Events.class);
                iEve.putExtra("def",typeE);

                startActivity(iEve);
                break;
            case R.id.navForum:
                String typeF = getIntent().getStringExtra("def");

                Intent iForum = new Intent(Navigation.this,Forum.class);
                iForum.putExtra("def",typeF);

                startActivity(iForum);
                break;
            case R.id.navNotification:
                String typeN = getIntent().getStringExtra("def");

                Intent iNotify = new Intent(Navigation.this,LoadNotification.class);
                iNotify.putExtra("def",typeN);

                startActivity(iNotify);
                break;
            case R.id.navRequest:
                String typeR = getIntent().getStringExtra("def");

                if(typeR.equals("Admin")){
                    Intent iRequest = new Intent(Navigation.this,LoadCSIRequest.class);
                    iRequest.putExtra("def",typeR);
                    startActivity(iRequest);
                }else if(typeR.equals("CSI")){
                    Intent iRequest = new Intent(Navigation.this,LoadUserRequest.class);
                    iRequest.putExtra("def",typeR);
                    startActivity(iRequest);
                }else{
                    Toast.makeText(getApplicationContext(),"Unauthorized",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.navSignOut:
                signOut();
                break;



        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {

            super.onBackPressed();
        }
    }

    private void signOut(){
        AuthUI.getInstance().signOut(Navigation.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent(Navigation.this,MainActivity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Couldn't signout",Toast.LENGTH_SHORT).show();
            }
        });
    }
}