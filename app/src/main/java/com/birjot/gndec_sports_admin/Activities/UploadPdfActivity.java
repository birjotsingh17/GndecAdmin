package com.birjot.gndec_sports_admin.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.birjot.gndec_sports_admin.Model.User;
import com.birjot.gndec_sports_admin.R;
import com.birjot.gndec_sports_admin.Services.MyUploadService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UploadPdfActivity extends Progressdialog {


    EditText description;
    Button uploadbtn;
    TextView selectPdf;
    EditText pdfName;
    TextInputLayout textInputPdfName;

    private static final int RC_TAKE_PDF = 101;
    private Uri mFileUri = null;
    private Uri mDownloadUrl = null;
    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";
    //private static String username;
    private static String pdfkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            setContentView(R.layout.activity_upload_pdf);
        }

        setTitle("GNDECsports");

        initViews();
        //getusername();
        restoreInstanceState(savedInstanceState);

    }


    public void onStart() {
        super.onStart();
        pdfkey = getIntent().getStringExtra("pdfkey");
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        out.putParcelable(KEY_FILE_URI, mFileUri);
        out.putParcelable(KEY_DOWNLOAD_URL, mDownloadUrl);
        Log.d(getString(R.string.tag), "onSaveInstanceState");
    }

    public void initViews(){
        description = (EditText)findViewById(R.id.Description);
        uploadbtn   = (Button) findViewById(R.id.UploadButton);
        selectPdf = (TextView) findViewById(R.id.selectPdfTextView);
        pdfName = (EditText) findViewById(R.id.pdfNameEditText);
        textInputPdfName = (TextInputLayout)findViewById(R.id.pdfnameTextInputLayout);
    }


    public void uploadbtnClicked(View v){
        if(validateForm()){
            uploadFromUri(mFileUri, pdfName.getText().toString().trim());
        }
    }

    public String getFileName(String uri){
        int index;
        while(uri.contains(":")){
            index = uri.indexOf(":");
            uri = uri.substring(index+1);
        }
        while (uri.contains("/")){
            index = uri.indexOf("/");
            uri = uri.substring(index+1);
        }
        return uri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getString(R.string.tag), "onActivityResult data : " + data);
        if (requestCode == RC_TAKE_PDF) {
            if (resultCode == RESULT_OK) {
                mFileUri = data.getData();

                Log.d("app","mFileUri"+mFileUri);

                if (mFileUri != null) {
                    selectPdf.setError(null);
                    pdfName.setError(null);
                    textInputPdfName.setVisibility(View.VISIBLE);
                    selectPdf.setText(getFileName(mFileUri.getLastPathSegment()) +" selected");
                    pdfName.setText(getFileName(mFileUri.getLastPathSegment()));
                }
                else {
                    Log.w(getString(R.string.tag), "File URI is null");
                }
            }
            else {
                Toast.makeText(this, "Taking Pdf failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFromUri(Uri fileUri, String pdfname) {
        Log.d(getString(R.string.tag), "uploadFromUri:src(fileuri.tostring):" + fileUri.toString());

        // Save the File URI
        mFileUri = fileUri;
        Log.d("app","uri"+mFileUri.toString());

        // Clear the last download, if any
        mDownloadUrl = null;
        // Start MyUploadService to upload the file, so that the file is uploaded
        // even if this Activity is killed or put in the background

//        Log.d("app", "uploadFromUri: pdf" +pdfkey);

        startService(new Intent(this, MyUploadService.class)
                .putExtra(MyUploadService.EXTRA_FILE_URI, fileUri)
                .putExtra("description", description.getText().toString().trim())
                /*.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid())*/
               /* .putExtra("username",username)*/
                .putExtra("pdfkey", pdfkey)
                .putExtra("pdfname", pdfname)
                .setAction(MyUploadService.ACTION_UPLOAD));

        Toast.makeText(this, getString(R.string.progress_uploading), Toast.LENGTH_LONG).show();

        startActivity(new Intent(UploadPdfActivity.this, HomeActivity.class));
        finish();
    }

  /*  public void getusername(){
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        Log.d("app", "user:" +user);
                        username = user.getUsername();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(getString(R.string.tag), "onCancelled: getusername");
                    }
                });
    }
*/
    public void restoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            mFileUri = savedInstanceState.getParcelable(KEY_FILE_URI);
            mDownloadUrl = savedInstanceState.getParcelable(KEY_DOWNLOAD_URL);
        }
        onNewIntent(getIntent());
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(getString(R.string.tag), "onNewIntent(upload_activity_intent)");
        // Check if this Activity was launched by clicking on an upload notification
        if (intent.hasExtra(MyUploadService.EXTRA_DOWNLOAD_URL)) {
            Log.d(getString(R.string.tag), "onNewIntent has extra_download_url");
            onUploadResultIntent(intent);
        }

    }

    private void onUploadResultIntent(Intent intent) {
        Log.d(getString(R.string.tag), "onUploadResultIntent");
        // Got a new intent from MyUploadService with a success or failure
        mDownloadUrl = intent.getParcelableExtra(MyUploadService.EXTRA_DOWNLOAD_URL);
        mFileUri = intent.getParcelableExtra(MyUploadService.EXTRA_FILE_URI);
    }

    private boolean validateForm(){
        boolean valid = true;
        if(TextUtils.isEmpty(description.getText().toString().trim())){
            description.setError("Required");
            valid = false;
        }
        else if(selectPdf.getText().toString().trim() == getString(R.string.Select_Pdf)){
            selectPdf.setError("Select Pdf");
            valid = false;
        }
        else if(TextUtils.isEmpty(pdfName.getText().toString().trim())){
            pdfName.setError("Please specify name of pdf");
            valid = false;
        }
       /* else if(!(selectPdf.getText().toString().trim().contains(".pdf") || selectPdf.getText().toString().trim().contains(".doc"))){
            Toast toast = Toast.makeText(this,"Only Pdf and Doc format Supported",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

            valid = false;
        }*/
        else{
            description.setError(null);
            selectPdf.setError(null);
            pdfName.setError(null);
        }
        return valid;
    }

    public void selectPdfClicked(View v){
        // Pick a Pdf from storage
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, RC_TAKE_PDF);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
