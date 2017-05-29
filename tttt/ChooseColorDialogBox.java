package com.whiteboard.securenotes.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.whiteboard.securenotes.R;
import com.whiteboard.securenotes.fragments.AddEditNoteFragment;

/**
 * Created by sarvesh.tank on 5/29/2017.
 */

public class ChooseColorDialogBox extends Dialog implements View.OnClickListener{

    View greenColor, brickColor, skinColor, greyColor;

    Context context;
    Fragment fragment;
    public ChooseColorDialogBox(@NonNull Context context, @StyleRes int themeResId, Fragment fragment) {
        super(context, themeResId);
        setCancelable(true);
        setTitle("Choose Color");
        this.context = context;
        this.fragment = fragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_view_pager);

        greenColor = findViewById(R.id.note_color_green);
        brickColor = findViewById(R.id.note_color_brick);
        skinColor = findViewById(R.id.note_color_skin);
        greyColor = findViewById(R.id.note_color_grey);

        greenColor.setBackgroundColor(getContext().getResources().getColor(R.color.note_color_green_code));
        brickColor.setBackgroundColor(getContext().getResources().getColor(R.color.note_color_brick_code));
        skinColor.setBackgroundColor(getContext().getResources().getColor(R.color.note_color_skin_code));
        greyColor.setBackgroundColor(getContext().getResources().getColor(R.color.note_color_grey_code));

        greenColor.setOnClickListener(this);
        brickColor.setOnClickListener(this);
        skinColor.setOnClickListener(this);
        greyColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Hello: "+v.getId(), Toast.LENGTH_SHORT).show();
        if(v.getId() == R.id.note_color_green){
            updateColor("green", R.color.note_color_green_code);
        }else if(v.getId() == R.id.note_color_brick){
            updateColor("brick", R.color.note_color_brick_code);
        }else if(v.getId() == R.id.note_color_skin){
            updateColor("skin", R.color.note_color_skin_code);
        }else if(v.getId() == R.id.note_color_grey){
            updateColor("grey", R.color.note_color_grey_code);
        }else{
            updateColor("yellow", R.color.note_color_yellow_code);
        }
    }

    private void updateColor(String skin, int noteColor) {
        fragment.getView().setBackgroundColor(context.getResources().getColor(noteColor));
        AddEditNoteFragment.updateColor(skin);
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        dismiss();
    }

}
