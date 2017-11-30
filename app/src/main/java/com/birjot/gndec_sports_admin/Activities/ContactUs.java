package com.birjot.gndec_sports_admin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.birjot.gndec_sports_admin.R;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        setContentView(R.layout.activity_contact_us);
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}

