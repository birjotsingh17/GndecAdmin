package com.birjot.gndec_sports_admin.Model;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.birjot.gndec_sports_admin.Activities.HomeActivity;
import com.birjot.gndec_sports_admin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by birjot on 26/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    ProgressDialog mProgressDialog;

    private Context context;
    private ArrayList<newsupload> nloads;
    protected DatabaseReference mDatabase;


    public NewsAdapter(Context context, ArrayList<newsupload> nloads) {
        this.nloads = nloads;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_news, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final newsupload newss = nloads.get(position);
        holder.heading.setText(newss.getHeading());
        holder.datee.setText(newss.getDatee());
        holder.description.setText(newss.getDescription());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(newss);

            }

        });


    }


    void showDialog(final newsupload newss){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete: " );
        builder.setMessage("Are you Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                del(newss);


            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    public void del(final newsupload newss) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference globalRef = mDatabase.child("news").child(newss.getReg_token());
        DatabaseReference globalRef1 = mDatabase.child("meet_news").child(newss.getReg_token());
        Log.d("app", "onSuccess: "+globalRef.toString());
        //DatabaseReference userPdfRef = mDatabase.child(getString(R.string.DB_user_pdfs)).child(pdf.getUid()).child(upload.getKey());
        deleteOnFirebaseDB(globalRef);
        deleteOnFirebaseDB(globalRef1);
        // deletePdfOnFirebaseDB(userPdfRef);



       /* Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);*/

    }

    public void deleteOnFirebaseDB(DatabaseReference Ref){
        Ref.removeValue();
        this.notifyDataSetChanged();
        //hideProgressDialog();
    }

    @Override
    public int getItemCount() {

        return nloads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView heading, description, datee;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.textnewsh);
            datee = (TextView) itemView.findViewById(R.id.textnewsdate);
            description = (TextView) itemView.findViewById(R.id.textnewsd);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout2);


        }
    }




}
