package com.birjot.gndec_sports_admin.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.birjot.gndec_sports_admin.Model.resultupload;
import com.birjot.gndec_sports_admin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAthResult extends Progressdialog {

    private Button addresult;

    private EditText firstprize, secondprize, thirdprize, uniroll1, uniroll2, uniroll3;

    private DatabaseReference mDatabase;

    private static String uploaddate;

   private Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setTitle("GNDECsports");

        setContentView(R.layout.activity_add_ath_result);



        spin = (Spinner)findViewById(R.id.spinnerEvent);

        String[] Field = getResources().getStringArray(R.array.Events);

        ArrayAdapter<String> Field_adapter1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Field);
        spin.setAdapter(Field_adapter1);

        spin.getDrawingCacheBackgroundColor();


        mDatabase = FirebaseDatabase.getInstance().getReference();


        firstprize = (EditText) findViewById(R.id.firstprize);
        secondprize = (EditText) findViewById(R.id.secondprize);
        thirdprize = (EditText) findViewById(R.id.thirdprize);
        uniroll1 = (EditText) findViewById(R.id.uniroll);
        uniroll2 = (EditText) findViewById(R.id.uniroll2);
        uniroll3 = (EditText) findViewById(R.id.uniroll3);
       /* addresult = (Button) findViewById(R.id.addmeetresults);

        addresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitPost();
            }
        });*/



    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public void submitPost(View view) {
        final String firstname = firstprize.getText().toString();
        final String secondname = secondprize.getText().toString();
        final String thirdname = secondprize.getText().toString();

        if (TextUtils.isEmpty(firstname)) {
            firstprize.setError("This field is required");
            return;
        }

        if (TextUtils.isEmpty(secondname)) {
            secondprize.setError("This field is required");
            return;
        }

        if (TextUtils.isEmpty(thirdname)) {
            thirdprize.setError("This field is required");
            return;
        }


        uploaddate = FindCurrentDate();

        // Disable button so there are no multi-posts
        /*setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();
*/
        String text =spin.getSelectedItem().toString();


        resultupload rload = new resultupload();
        rload.setReg_token(mDatabase.child("meetresults").push().getKey());
       // rload.setSpin(spin.getSelectedItem().toString());
        rload.setFirstname(firstprize.getText().toString());
        rload.setSecondname(secondprize.getText().toString());
        rload.setThirdname(thirdprize.getText().toString());
        rload.setUniroll1(uniroll1.getText().toString());
        rload.setUniroll2(uniroll2.getText().toString());
        rload.setUniroll3(uniroll3.getText().toString());


        rload.setDatee(uploaddate.toString());
        mDatabase.child("meetresults").child(rload.getReg_token()).setValue(rload,text);


        showDialog();



    }

    String FindCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(new Date());
        Log.i("test", currentdate);

        return currentdate;}



    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Result Posted Successfully!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                firstprize.setText(null);
               secondprize.setText(null);
                 thirdprize.setText(null);
               uniroll1.setText(null);
                 uniroll3.setText(null);
               uniroll2.setText(null);

            }
        });

        builder.setCancelable(false);
        builder.create().show();

    }

}
