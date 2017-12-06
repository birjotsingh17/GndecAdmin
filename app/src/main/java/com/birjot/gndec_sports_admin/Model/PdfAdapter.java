package com.birjot.gndec_sports_admin.Model;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.birjot.gndec_sports_admin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by birjot on 6/12/17.
 */

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    ProgressDialog mProgressDialog;

    private Context context;
    private ArrayList<Pdf> nloads;
    protected DatabaseReference mDatabase;

    public PdfAdapter(Context context, ArrayList<Pdf> nloads) {
        this.nloads = nloads;
        this.context = context;
        Log.d("app", "PdfAdapter: ");
    }


    @Override
    public PdfAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pdfresult, parent, false);

        PdfAdapter.ViewHolder viewHolder = new PdfAdapter.ViewHolder(itemView);
        Log.d("app", "onCreateViewHolder: ");
        return new PdfAdapter.ViewHolder(itemView);
//        return null;
    }

    @Override
    public void onBindViewHolder(PdfAdapter.ViewHolder holder, int position) {

        final Pdf newss = nloads.get(position);
        holder.heading.setText(newss.getPdfname());
        holder.datee.setText(newss.getUploaddate());
        holder.description.setText(newss.getDescription());
        Log.d("app", "onBindViewHolder: ");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(newss);

            }

        });


    }

    void showDialog(final Pdf newss){
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

    public void del(final Pdf newss) {

        showCaptionProgressDialog("Deleting...");

        // String xyx = getIntent().getExtras().getString("keyName");

        // String storageUrl = "1510554381738.jpg";
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("pdfupload")
                .child(newss.getPdfKey())
                .child(newss.getPdfname());
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
                //Log.d(TAG, "onSuccess: deleted file");
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference globalRef = mDatabase.child("Pdfs").child(newss.getPdfKey());
                Log.d("app", "onSuccess: "+globalRef.toString());
                //DatabaseReference userPdfRef = mDatabase.child(getString(R.string.DB_user_pdfs)).child(pdf.getUid()).child(upload.getKey());
                deleteOnFirebaseDB(globalRef);
                // deletePdfOnFirebaseDB(userPdfRef);



            /*    Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);*/
                hideProgressDialog();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {


                hideProgressDialog();
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                // Uh-oh, an error occurred!
                // Log.d(TAG, "onFailure: did not delete file");
            }
        });


        //Toast.makeText(this,"file : " + xyx, Toast.LENGTH_LONG).show();

    }

    public void deleteOnFirebaseDB(DatabaseReference Ref){
        Ref.removeValue();
        this.notifyDataSetChanged();
        hideProgressDialog();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    public void showCaptionProgressDialog(String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(caption);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
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
            heading = (TextView) itemView.findViewById(R.id.headpdf);
            datee = (TextView) itemView.findViewById(R.id.pdfdate);
            description = (TextView) itemView.findViewById(R.id.descpdf);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayoutpdf);
        }
    }
}
