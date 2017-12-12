package com.birjot.gndec_sports_admin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.birjot.gndec_sports_admin.R;

public class Developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setTitle("GNDECsports");

        setContentView(R.layout.activity_developers);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
