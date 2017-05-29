package com.whiteboard.securenotes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.whiteboard.securenotes.data.DatabaseQueries;
import com.whiteboard.securenotes.fragments.AddEditNoteFragment;
import com.whiteboard.securenotes.fragments.AllNotesFragment;
import com.whiteboard.securenotes.utils.ChooseColorDialogBox;

/**
 * Created by Stark on 12/05/2017.
 */

public class AddEditNoteActivity extends AppCompatActivity{
    AddEditNoteFragment fragobj;
    int id;
    String color;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null && intent.getAction().equals(AllNotesFragment.DETAILED_NOTE_INTENT)) {
            id = intent.getIntExtra("id", -1);
            String head = intent.getStringExtra("head");
            String body = intent.getStringExtra("data");
            int enc = intent.getIntExtra("enc", -1);
            String lastModified = intent.getStringExtra("lastModified");
            color = intent.getStringExtra("color");
            Bundle bundle = new Bundle();
            bundle.putString("state", "edit");
            bundle.putInt("id", id);
            bundle.putString("head", head);
            bundle.putString("data", body);
            bundle.putInt("enc", enc);
            bundle.putString("lastModified", lastModified);
            bundle.putString("color", color);

            fragobj = new AddEditNoteFragment();
            fragobj.setArguments(bundle);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.detailed_note, fragobj)
                    .commit();
            //int colorInt = AddEditNoteFragment.getColor(color);
            //fragobj.getView().setBackgroundColor(colorInt);
            //AddEditNoteFragment.updateColor(colorInt);

        }else if(intent != null && intent.getAction().equals(AllNotesFragment.NEW_NOTE_INTENT)){
            fragobj = new AddEditNoteFragment();

            Bundle bundle = new Bundle();
            bundle.putString("state", "new");

            fragobj.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.detailed_note, fragobj)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(fragobj.isEditing){
            getMenuInflater().inflate(R.menu.menu_edit_notes, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_add_notes, menu);
        }
        for(int i=0;i<menu.size();i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.color_for_note){
            ChooseColorDialogBox dialogBox = new ChooseColorDialogBox(this, R.style.DialogBoxTheme, fragobj);
            dialogBox.show();
        }else if(item.getItemId() == R.id.save_note){
            boolean saved = fragobj.save(null, null);
            if(saved)
            finish();
            return true;
        }else if(item.getItemId() == R.id.edit){
            fragobj.enableEditingMode();
            return true;
        }else if(item.getItemId() == R.id.share){
            fragobj.shareNote();
            return true;
        }else if(item.getItemId() == R.id.delete) {
            if (id != 0) {
                AlertDialog builder = new AlertDialog.Builder(this)
                        .setTitle("Delete Note")
                        .setMessage("Are You Sure You Want To Delete It")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseQueries.deleteNote(AddEditNoteActivity.this, id);
                                finish();
                                Toast.makeText(AddEditNoteActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create();
                builder.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        fragobj.backPressed();
    }



}
