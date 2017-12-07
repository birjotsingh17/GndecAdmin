package com.birjot.gndec_sports_admin.Games_Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.birjot.gndec_sports_admin.R;

public class Football extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setContentView(R.layout.activity_football);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
