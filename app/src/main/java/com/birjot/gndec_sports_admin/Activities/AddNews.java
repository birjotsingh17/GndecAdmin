package com.birjot.gndec_sports_admin.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.birjot.gndec_sports_admin.Model.newsupload;
import com.birjot.gndec_sports_admin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNews extends Progressdialog {


    private Button addnews;

    private EditText newsHead, ewsdesc;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        newsHead = (EditText) findViewById(R.id.newshead);
        addnews = (Button) findViewById(R.id.addnews);
        ewsdesc = (EditText) findViewById(R.id.newsdes);

        addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();

            }
        });
    }

    private void submitPost() {
        final String title = newsHead.getText().toString();
        final String body = ewsdesc.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            newsHead.setError("This field is required");
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            ewsdesc.setError("This field is required");
            return;
        }



        // Disable button so there are no multi-posts
        /*setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();
*/
        // [START single_value_read]
        //final String userId = mDatabase.push().getKey();
        newsupload nload = new newsupload();
        nload.setReg_token(mDatabase.child("news").push().getKey());
        nload.setHeading(newsHead.getText().toString());
        nload.setDescription(ewsdesc.getText().toString());
        mDatabase.child("news").child(nload.getReg_token()).setValue(nload);
        newsHead.setText(null);
        ewsdesc.setText(null);

        /*ewsupload nload = new newsupload(
                newsHead.getText().toString().trim(),
                ewsdesc.getText().toString().trim(),
                getRegToken());

        Log.d("app", "" + nload);
        mDatabase.child(getString(R.string.DB_Users))
                .child(userId)
                .setValue(nload);*/

    }



   /* public String getRegToken() {
        Log.d("app", "getRegToken: before token");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("app", "getRegToken: " + token);
        return token;
    }*/

       /* mDatabase.child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e("app", "User " + userId + " is unexpectedly null");
                            Toast.makeText(AddNews.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username, title, body);
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("app", "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });*/
    // [END single_value_read]


  /*  private void setEditingEnabled(boolean enabled) {
        newsHead.setEnabled(enabled);
        ewsdesc.setEnabled(enabled);
        if (enabled) {
            addnews.setVisibility(View.VISIBLE);
        } else {
            addnews.setVisibility(View.GONE);
        }
    }*/


}
