package com.birjot.gndec_sports_admin.Model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.birjot.gndec_sports_admin.Fragments.Games;
import com.birjot.gndec_sports_admin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by birjot on 13/11/17.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {


    Context context;
    int resource;
    ArrayList<GamesModel> userList;


    public GamesAdapter(Context context, int resource, ArrayList<GamesModel> userList){
        this.context = context;
        this.resource = resource;
        this.userList = userList;
    }


    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



  /*  @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }*/

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GamesModel user = userList.get(position);

        holder.txtName.setText(user.getName());
        holder.img.setImageResource(user.getIcon());


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName;
        ImageView img;

        public ViewHolder(View itemView)
        {
            super(itemView);

            img=(ImageView) itemView.findViewById(R.id.imageView4);

            txtName=(TextView) itemView.findViewById(R.id.textView5);
        }
    }

   /* Context context;
    int resource;
    ArrayList<GamesModel> userList;


    public GamesAdapter(Context context, int resource, ArrayList<GamesModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        userList = objects;*/



    }

/*
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        //view should point to list_item
        view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView txtName = (TextView)view.findViewById(R.id.textViewName);

        GamesModel gm = userList.get(position);
        imageView.setBackgroundResource(gm.getIcon());
        txtName.setText(gm.getName());


        return view;
    }*/



