package com.example.sarveshtank.securenotes.util;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sarveshtank.securenotes.R;

/**
 * Created by Sarvesh.Tank on 3/29/2017.
 */

public class UserListAdapters extends RecyclerView.Adapter<UserListAdapters.UserListAdapterHolders>{

    Cursor cursor;
    public UserListAdapters(Cursor cursor){
        this.cursor = cursor;
    }

    public void setHeaderData(Cursor cursor){
        if(this.cursor!=null)
            this.cursor.close();
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public UserListAdapterHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_layout, parent, false);
        UserListAdapterHolders holder = new UserListAdapterHolders(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserListAdapterHolders holder, int position) {
        holder.heading.setText(cursor.getString(position));
    }

    @Override
    public int getItemCount() {
        if(cursor==null)
            return 0;
        return cursor.getCount();
    }

    public class UserListAdapterHolders extends RecyclerView.ViewHolder{
        TextView heading;

        public UserListAdapterHolders(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.heading);
        }

    }

}
    
