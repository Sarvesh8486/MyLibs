package com.whiteboard.securenotes.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whiteboard.securenotes.R;
import com.whiteboard.securenotes.data.DatabaseContract;
import com.whiteboard.securenotes.data.DatabaseQueries;
import com.whiteboard.securenotes.fragments.AddEditNoteFragment;
import com.whiteboard.securenotes.fragments.AllNotesFragment;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserListAdapters extends RecyclerView.Adapter<UserListAdapters.UserListAdapterHolders> {

    OnClickHandler handler;

    public interface OnClickHandler {
        void onClick(int id);
    }


    Cursor cursor;
    public UserListAdapters(Cursor cursor, OnClickHandler handler){
        this.cursor = cursor;
        this.handler = handler;
    }

    public void updateCursor(Cursor cursor){
        if(this.cursor!=null)
            this.cursor.close();
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public UserListAdapterHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_layout, parent, false);
        UserListAdapterHolders holder = new UserListAdapterHolders(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(UserListAdapterHolders holder, int position) {
        if(cursor!=null && cursor.moveToPosition(position)){
            holder.title.setText(cursor.getString(0));
            String timeStamp = cursor.getString(1);
            String colorStr = cursor.getString(2);
            int id = cursor.getInt(3);
            holder.itemView.setTag(""+id);
            int color= new ColorMap(((AllNotesFragment)handler).getContext()).getColorInt(colorStr);
            holder.cardView.setBackgroundColor(color);
            Timestamp ts = null;
            try {
                ts = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(timeStamp).getTime());
            } catch (ParseException e) {
            }
            String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date(ts.getTime()));
            String time = new SimpleDateFormat("hh:mm a").format(new Date(ts.getTime()));

            holder.date.setText(date);
            holder.time.setText(time);

        }

    }

    @Override
    public int getItemCount() {
        if(cursor==null)
            return 0;
        return cursor.getCount();
    }

    public class UserListAdapterHolders extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView title, date, time;
        CardView cardView;
        View holder;
        public UserListAdapterHolders(View itemView) {
            super(itemView);
            this.holder = itemView;
            cardView = (CardView) itemView.findViewById(R.id.linear_layout);
            title = (TextView) itemView.findViewById(R.id.rv_note_title);
            date = (TextView) itemView.findViewById(R.id.rv_date);
            time = (TextView) itemView.findViewById(R.id.rv_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = Integer.parseInt((String) v.getTag());
            handler.onClick(adapterPosition);
        }
    }
    public void onItemRemove(final RecyclerView.ViewHolder viewHolder, final RecyclerView recyclerView) {
        final int idOfNoteToBeDeleted = Integer.parseInt((String) viewHolder.itemView.getTag());
        final Context context = recyclerView.getContext();
        //backup cursor
        final Cursor backupForDeletion = DatabaseQueries.selectNotesById(context, idOfNoteToBeDeleted);
        if(backupForDeletion.moveToFirst()){
            int enc = backupForDeletion.getInt(2);
            if(enc==1){
                AlertDialog alertDialog = AllNotesFragment.askPasswordForSecuredNotes();
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(AllNotesFragment.PASSWORDS[0]){
                            deleteNoteProcess(context, viewHolder, idOfNoteToBeDeleted, recyclerView, backupForDeletion);
                        }else{

                            Toast.makeText(context, "Incorrect Password!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                deleteNoteProcess(context, viewHolder, idOfNoteToBeDeleted, recyclerView, backupForDeletion);
            }
        }
    }

    public void deleteNoteProcess(final Context context,final RecyclerView.ViewHolder viewHolder, final int idOfNoteToBeDeleted, final RecyclerView recyclerView, final Cursor backupForDeletion) {
        Log.e("123", "onItemRemove: 2 " + backupForDeletion.getCount());

        DatabaseQueries.deleteNote(context, idOfNoteToBeDeleted);
        String sortBy = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_sort_key), context.getString(R.string.pref_default_sort_value));
        // delete cursor row

        Cursor newCursor = DatabaseQueries.selectNotesAll(context, sortBy);
        Log.e("123", "onItemRemove: 3 " + newCursor.getCount());
        updateCursor(newCursor);


        Snackbar snackbar = Snackbar
                .make(recyclerView, "Note Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int mAdapterPosition = viewHolder.getAdapterPosition();
                        //restore backup cursor
                        if (backupForDeletion.moveToFirst()) {

                            Log.e("123", "onClick: " + backupForDeletion.getPosition());
                            String head = backupForDeletion.getString(0);
                            String data = backupForDeletion.getString(1);
                            int id = backupForDeletion.getInt(5);
                            String color = backupForDeletion.getString(4);
                            int enc = backupForDeletion.getInt(2);
                            Log.e("123", "onClick: " + head + " " + data + " " + id + " " + color + " " + enc);
                            /*String head = backupForDeletion.getString(backupForDeletion.getColumnIndex(DatabaseContract.TableColumn.HEADING));
                            String data = backupForDeletion.getString(backupForDeletion.getColumnIndex(DatabaseContract.TableColumn.DATA));
                            int id = backupForDeletion.getInt(backupForDeletion.getColumnIndex(DatabaseContract.TableColumn._ID));
                            String color = backupForDeletion.getString(backupForDeletion.getColumnIndex(DatabaseContract.TableColumn.COLOR));
                            int enc = backupForDeletion.getInt(backupForDeletion.getColumnIndex(DatabaseContract.TableColumn.ENCRYPTION_TOKEN));*/
                            DatabaseQueries.restoreBackupForDeletion(context, id, head, data, enc, color);
                            String sortBy = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_sort_key), context.getString(R.string.pref_default_sort_value));
                            // delete cursor row

                            Cursor newCursor = DatabaseQueries.selectNotesAll(context, sortBy);
                            Log.e("123", "onItemRemove: 4 " + newCursor.getCount());
                            updateCursor(newCursor);
                            Toast.makeText(recyclerView.getContext(), "Note Restored", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        snackbar.show();

    }
}
