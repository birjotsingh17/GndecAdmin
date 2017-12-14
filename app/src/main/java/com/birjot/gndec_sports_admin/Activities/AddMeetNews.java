package com.birjot.gndec_sports_admin.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.birjot.gndec_sports_admin.Model.newsupload;
import com.birjot.gndec_sports_admin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMeetNews extends AppCompatActivity {

    private Button meetaddnews;

    private EditText meetnewsHead, meetewsdesc;

    private DatabaseReference mDatabase;

    private static String uploaddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }


        setTitle("GNDECsports");

        setContentView(R.layout.activity_add_meet_news);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        meetnewsHead = (EditText) findViewById(R.id.meetnewshead);
        meetaddnews = (Button) findViewById(R.id.meetaddnews);
        meetewsdesc = (EditText) findViewById(R.id.meetnewsdes);

        meetaddnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();

            }
        });

    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("News Posted Successfully!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                meetnewsHead.setText(null);
                meetewsdesc.setText(null);


            }
        });

        builder.setCancelable(false);
        builder.create().show();

    }



    private void submitPost() {
        final String title = meetnewsHead.getText().toString();
        final String body = meetewsdesc.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            meetnewsHead.setError("This field is required");
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            meetewsdesc.setError("This field is required");
            return;
        }


        uploaddate = FindCurrentDate();

        // Disable button so there are no multi-posts
        /*setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();
*/
        // [START single_value_read]
        //final String userId = mDatabase.push().getKey();
        newsupload nload = new newsupload();
        nload.setReg_token(mDatabase.child("news").push().getKey());
        nload.setHeading(meetnewsHead.getText().toString());
        nload.setDescription(meetewsdesc.getText().toString());
        nload.setDatee(uploaddate.toString());
        mDatabase.child("meet_news").child(nload.getReg_token()).setValue(nload);


        showDialog();

        /*ewsupload nload = new newsupload(
                newsHead.getText().toString().trim(),
                ewsdesc.getText().toString().trim(),
                getRegToken());

        Log.d("app", "" + nload);
        mDatabase.child(getString(R.string.DB_Users))
                .child(userId)
                .setValue(nload);*/

    }


    String FindCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(new Date());
        Log.i("test", currentdate);

        return currentdate;}

}
