package com.whiteboard.securenotes.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whiteboard.securenotes.AddEditNoteActivity;
import com.whiteboard.securenotes.PreferencesActivity;
import com.whiteboard.securenotes.R;
import com.whiteboard.securenotes.data.DatabaseQueries;
import com.whiteboard.securenotes.utils.UserListAdapters;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Stark on 11/05/2017.
 */

public class AllNotesFragment extends Fragment implements UserListAdapters.OnClickHandler{

    public static final String DETAILED_NOTE_INTENT = "detailed_note_intent";
    public static final String NEW_NOTE_INTENT = "new_note_intent";
    public static final String EDIT_NOTE_INTENT = "edit_note_intent";
    static SharedPreferences sharedPreferences;
    UserListAdapters adapters;
    RecyclerView recyclerView;
    static View viewById;
    static Context context;

    private String getSortByColumn(){
        return sharedPreferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_default_sort_value));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_all_notes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_all_notes);
        context = view.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String sortBy = getSortByColumn();

        boolean first_run = sharedPreferences.getBoolean("first_run", true);
        if(first_run) {
            sharedPreferences.edit()
                    .putBoolean("first_run", false)
                    .apply();

            String message = "Master password will be the password on your notes.\nYou can edit this any time from setting";
            setMasterPassword(context, message, true, false);
        }
        recyclerView.setHasFixedSize(true);
        adapters = new UserListAdapters(DatabaseQueries.selectNotesAll(context, sortBy), this);
        recyclerView.setAdapter(adapters);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapters.onItemRemove(viewHolder, recyclerView);
            }
        }).attachToRecyclerView(recyclerView);

            viewById = getActivity().findViewById(android.R.id.content);
        return view;
    }

    public static void setMasterPassword(final Context context, final String message,final boolean cancelable, @Nullable boolean isError){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View inflate = inflater.inflate(R.layout.password_prompt, null, false);

        final EditText passwordEdt = ((EditText) inflate.findViewById(R.id.password_text));
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                .setView(inflate)
                .setCancelable(cancelable)
                .setTitle("Enter Master Password")
                .setMessage(message)
                .setPositiveButton("Set Password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password =     passwordEdt.getText().toString();
                        setPasswordToPref(password);

                    }
                });
        if(cancelable){
            dialog.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        passwordEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (s==null || TextUtils.isEmpty(s.toString().trim())) {
                    // Disable ok button
                    ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    // Something into edit text. Enable the button.
                    ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        String sortBy = getSortByColumn();
        Cursor cursor = DatabaseQueries.selectNotesAll(context, sortBy);
        adapters.updateCursor(cursor);
    }

    @Override
    public void onClick(final int id) {
        Cursor cursor = DatabaseQueries.selectNotesById(context, id);
        if (cursor.moveToFirst()) {

            final String databaseHeading = cursor.getString(0);
            final String databaseData = cursor.getString(1);
            final int databaseEncryption = cursor.getInt(2);
            final String lastModifiedTs = cursor.getString(3);
            final String color = cursor.getString(4);
            Timestamp ts = null;
            try {
                ts = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(lastModifiedTs).getTime());
            } catch (ParseException e) {
            }
            final String lastModified = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a").format(new Date(ts.getTime()));
            if (databaseEncryption == 1) {
                LayoutInflater inflater = LayoutInflater.from(context);
                final View inflate = inflater.inflate(R.layout.password_prompt, null, false);
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Enter Password")
                        .setView(inflate)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText password = (EditText) inflate.findViewById(R.id.password_text);
                                String masterPassword = sharedPreferences.getString("master_password", null);
                                if (password.getText().toString().equals(masterPassword)) {
                                    nextActivity(lastModified, DETAILED_NOTE_INTENT, id, databaseHeading, databaseData, databaseEncryption, color);
                                } else {
                                    Toast.makeText(context, "Invalid Password", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(true)
                        .create();
                alertDialog.show();
            } else {
                nextActivity(lastModified, DETAILED_NOTE_INTENT, id, databaseHeading, databaseData, databaseEncryption, color);
            }
        }
    }

    public static boolean setPasswordToPref(String password){
        if((password==null || TextUtils.isEmpty(password.trim()))){
            Snackbar.make(viewById, "Invalid Password. Please set valid password from settings", Snackbar.LENGTH_LONG)
                    .setAction("Set Password", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PreferencesActivity.class);
                            context.startActivity(intent);
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
                return true;
        }
        if(sharedPreferences==null){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("master_password", password);
        editor.apply();
        Toast.makeText(context, "Master Password Set Successfully", Toast.LENGTH_SHORT).show();
        Snackbar.make(viewById, "Please set the security question", Snackbar.LENGTH_LONG)
                .setAction("Set Question", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PreferencesActivity.class);
                        context.startActivity(intent);
                    }
                })
                .setActionTextColor(Color.RED)
                .show();
        return false;
    }

    public static void nextActivity(String lastModified, String action, int id, String databaseHeading, String databaseData, int databaseEncryption, String color){
        Intent intent = new Intent(context, AddEditNoteActivity.class);
        intent.setAction(action);
        intent.putExtra("id", id);
        intent.putExtra("head", databaseHeading);
        intent.putExtra("data", databaseData);
        intent.putExtra("enc", databaseEncryption);
        intent.putExtra("lastModified", lastModified);
        intent.putExtra("color", color);
        context.startActivity(intent);
    }

}
