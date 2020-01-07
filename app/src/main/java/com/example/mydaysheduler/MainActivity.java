package com.example.mydaysheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView myview = findViewById(R.id.bottom_nav);
        myview.setOnNavigationItemSelectedListener(mylist);
//if frag is empty then it will go to directly home
        if (getIntent().getStringExtra("frag") != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new alarm()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
        }
    }

    //for selecting the multiple fragment class

    private BottomNavigationView.OnNavigationItemSelectedListener mylist = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.home:
                    selectedFragment = new Home();
                    break;
                case R.id.history:
                    selectedFragment = new history();
                    break;
                case R.id.time:
                    selectedFragment = new alarm();
                    break;

            }
            //for switch between fragment layout with the help of selectFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
}
