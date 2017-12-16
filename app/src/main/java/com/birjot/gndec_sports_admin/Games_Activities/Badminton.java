package com.birjot.gndec_sports_admin.Games_Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.birjot.gndec_sports_admin.R;

public class Badminton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setContentView(R.layout.activity_badminton);

        WebView web = (WebView) findViewById(R.id.badweb);
        String text =  "Only one outdoor cemented court is available for students to play.";
        web.loadData("<p style=\" text-align: justify\">"+ text +"</p>", "text/html", "UTF-8");
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
