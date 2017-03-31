package com.example.sarveshtank.securenotes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sarveshtank.securenotes.data.DatabaseQueries;

public class AddNotesData extends AppCompatActivity {

    int noteId;
    TextView heading, data;
    ImageButton encrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes_data);
        heading = (TextView) findViewById(R.id.notes_heading);
        data = (TextView) findViewById(R.id.notes_text);
        encrypt = (ImageButton) findViewById(R.id.encrypt);

        Intent intent = getIntent();
        if(intent!=null)
            noteId =  intent.getIntExtra("id", -1);
    }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.add_notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            item.setVisible(false);
            invalidateOptionsMenu();
            MenuItem save = menu.findItem(R.id.save);
            save.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return true;
        }else if(item.getItemId() == R.id.save){
            getMenuInflater().inflate(R.menu.add_notes_menu, menu);
            return true;
        }else if(item.getItemId() == R.id.share){


            return true;
        }else if(item.getItemId() == R.id.delete){
            if(noteId!=0)
                DatabaseQueries.deleteNote(this, noteId);
            finish();
            return true;
        }else if(item.getItemId() == R.id.check){

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if(data.getText()==null)
            super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setMessage("Do You Want To Save This Note")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseQueries.saveNotes(AddNotesData.this, heading.getText().toString(), data.getText().toString(), null);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        alertDialog.show();
    }
}
