package com.birjot.gndec_sports_admin.Games_Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.birjot.gndec_sports_admin.R;

public class BasketBall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setContentView(R.layout.activity_basket_ball);

        WebView web = (WebView) findViewById(R.id.basketweb);
        String text =  " Our Campus has one standard cemented courts (28m * 15m) that serves athletic training, competition and leisure needs. "
                ;
        web.loadData("<p style=\" text-align: justify\">"+ text +"</p>", "text/html", "UTF-8");
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
