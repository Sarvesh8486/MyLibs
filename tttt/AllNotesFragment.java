package com.whiteboard.securenotes.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    static SharedPreferences sharedPreferences;
    UserListAdapters adapters;
    RecyclerView recyclerView;
    static View viewById;
    static Context context;
    RelativeLayout hintLayout;
    Button button;

    public void showHint(){
        boolean first_run = sharedPreferences.getBoolean("ALL_NOTES_HINT", true);
        if(first_run) {
            hintLayout.setVisibility(View.VISIBLE);
            hintLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    disableHint();
                }
            }, 20000);
            sharedPreferences.edit()
                    .putBoolean("first_run", false)
                    .apply();
        }
    }

    public void disableHint(){
        hintLayout.setVisibility(View.GONE);
    }

    public void askForPwd(){
        boolean first_run = sharedPreferences.getBoolean("first_run", true);
        if(first_run) {
            sharedPreferences.edit()
                    .putBoolean("first_run", false)
                    .apply();

            String message = "Master password will be the password on your notes.\nYou can edit this any time from setting";
            setMasterPassword(context, message, true, false);
        }
    }

    private String getSortByColumn(){
        return sharedPreferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_default_sort_value));
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_all_notes, container, false);
        hintLayout = (RelativeLayout) view.findViewById(R.id.linear_layout);
        button = (Button) view.findViewById(R.id.got_it_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableHint();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_all_notes);
        context = view.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String sortBy = getSortByColumn();
        askForPwd();
        showHint();
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
                disableHint();
                if(direction == ItemTouchHelper.RIGHT)
                    newActivityTransfer(Integer.parseInt((String) viewHolder.itemView.getTag()));
                else if(direction == ItemTouchHelper.LEFT)
                    adapters.onItemRemove(viewHolder, recyclerView);
                adapters.notifyDataSetChanged();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Paint p = new Paint();
                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = drawableToBitmap(getResources().getDrawable(R.drawable.ic_edit));
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = drawableToBitmap(getResources().getDrawable(R.drawable.ic_delete));
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        viewById = getActivity().findViewById(android.R.id.content);
        return view;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
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
                        String password = passwordEdt.getText().toString();
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
        activatePositiveButton(passwordEdt, alertDialog);
    }

    public static void activatePositiveButton(EditText field, final AlertDialog alertDialog){
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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
    public final static boolean[] PASSWORDS = {false};
    public static AlertDialog askPasswordForSecuredNotes(){
        PASSWORDS[0] = false;
        LayoutInflater inflater = LayoutInflater.from(context);
        final View inflate = inflater.inflate(R.layout.password_prompt, null, false);
        final EditText password = (EditText) inflate.findViewById(R.id.password_text);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle("Enter Password")
                .setView(inflate)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String masterPassword = sharedPreferences.getString("master_password", null);
                        PASSWORDS[0] = masterPassword.equalsIgnoreCase(password.getText().toString())?true:false;
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(true);
        AlertDialog alert = alertDialog.create();
        activatePositiveButton(password, alert);
        return alert;
    }


    public void newActivityTransfer(final int id){
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
                AlertDialog alertDialog = askPasswordForSecuredNotes();
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(PASSWORDS[0]){
                            nextActivity(lastModified, DETAILED_NOTE_INTENT, id, databaseHeading, databaseData, databaseEncryption, color);
                        }else{
                            Toast.makeText(context, "Invalid Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                nextActivity(lastModified, DETAILED_NOTE_INTENT, id, databaseHeading, databaseData, databaseEncryption, color);
            }
        }
    }


    @Override
    public void onClick(final int id) {
        disableHint();
        newActivityTransfer(id);
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
