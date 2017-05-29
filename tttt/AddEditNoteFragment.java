package com.whiteboard.securenotes.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whiteboard.securenotes.R;
import com.whiteboard.securenotes.data.DatabaseQueries;
import com.whiteboard.securenotes.utils.ColorMap;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Stark on 11/05/2017.
 */

public class AddEditNoteFragment extends Fragment{

    public static final int EDIT_SAVE = 1;
    public static final int NEW_SAVE = 2;
    int action;
    TextView title, body;
    ImageView lock, hint;
    View horizontalLine, etHorizontalLine;
    EditText etTitle, etBody;
    int id, enc=0;
    static String color;
    RelativeLayout relativeLayout,hintLayout;
    Button button;

    public void showHint(){
        Context context = getActivity();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstRun = sharedPreferences.getBoolean("ADD_EDIT_NOTES_ACTIVITY_FIRST_HINT", true);

        if(firstRun){
            hintLayout.setVisibility(View.VISIBLE);
            hint.postDelayed(new Runnable() {
                @Override
                public void run() {
                    disableHint();
                    enableEditingMode();
                }
            }, 20000);
            sharedPreferences.edit()
                    .putBoolean("ADD_EDIT_NOTES_ACTIVITY_FIRST_HINT", false)
                    .apply();
        }

    }

    public void disableHint(){
        hintLayout.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_add_new_note, container, false);
        hintLayout = (RelativeLayout) view.findViewById(R.id.linear_layout);
        color = "yellow";
        String fontSize = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getActivity().getResources().getString(R.string.pref_text_size_key), getActivity().getResources().getString(R.string.pref_default_font_size));
        int fontSizeInt = Integer.parseInt(fontSize);
        title = (TextView) view.findViewById(R.id.edit_add_note_title);
        body = (TextView) view.findViewById(R.id.edit_add_note_text);
        lock = (ImageView) view.findViewById(R.id.lock_button);
        horizontalLine = view.findViewById(R.id.horizontal_line);
        etBody = (EditText) view.findViewById(R.id.et_edit_add_note_text);
        etTitle = (EditText) view.findViewById(R.id.et_edit_add_note_title);
        etHorizontalLine = view.findViewById(R.id.et_horizontal_line);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);
        hint = (ImageView) view.findViewById(R.id.hint_img);
        button = (Button) view.findViewById(R.id.got_it_button);

        etTitle.setTextSize(fontSizeInt);
        etBody.setTextSize(fontSizeInt);
        body.setTextSize(fontSizeInt);
        title.setTextSize(fontSizeInt);
        etTitle.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                etBody.requestFocus();
                return true;
            }
        });
        if(getArguments().get("state").equals("edit")){
            action = EDIT_SAVE;
            id = (int) getArguments().get("id");
            title.setText(getArguments().get("head").toString());
            body.setText(getArguments().get("data").toString());
            enc = (int) getArguments().get("enc");
            color = (String) getArguments().get("color");
            //getView().setBackgroundColor();
            relativeLayout.setBackgroundColor(new ColorMap(getContext()).getColorInt(color));
            if(enc==1){
                lock.setImageResource(R.drawable.ic_lock_close);
                DrawableCompat.setTint(lock.getDrawable(), ContextCompat.getColor(getActivity(), R.color.colorAccent));
            }
            else if(enc==0){
                lock.setImageResource(R.drawable.ic_lock_open);
            }
        }else if(getArguments().get("state").equals("new")){
            action = NEW_SAVE;
            showHint();

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableHint();
                enableEditingMode();
            }
        });
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("123", "onClick: isEditing "+isEditing);
                if(isEditing){
                    if(enc == 0){
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String masterPassword = pref.getString("master_password", null);
                        if(masterPassword==null || TextUtils.isEmpty(masterPassword)){
                            Toast.makeText(getContext(), "Master Password Not Set Yet,\nPlease set master password from Settings", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(getContext(), "Note Secured", Toast.LENGTH_LONG).show();
                        enc = 1;
                        lock.setImageResource(R.drawable.ic_lock_close);
                        DrawableCompat.setTint(lock.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorAccent));
                    }else if(enc == 1 ){
                        enc = 0;
                        Toast.makeText(getContext(), "Security Removed", Toast.LENGTH_LONG).show();;
                        lock.setImageResource(R.drawable.ic_lock_open);
                    }
                }
            }
        });
        
        final GestureDetector.OnDoubleTapListener gestureDetector = new GestureDetector.OnDoubleTapListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                enableEditingMode();
                disableHint();
                return true;
            }
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                enableEditingMode();
                disableHint();
                return true;

            }
        };
        body.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onDoubleTap(event);
            }
        });


        return view;
    }


    public boolean isEditing = false;

    public static void updateColor(String mColor){
        color = mColor;
    }



    public void enableEditingMode(){
        getActivity().supportInvalidateOptionsMenu();
        isEditing = true;

        CharSequence titleText = title.getText();
        CharSequence bodyText = body.getText();

        horizontalLine.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        body.setVisibility(View.GONE);

        etHorizontalLine.setVisibility(View.VISIBLE);
        etTitle.setVisibility(View.VISIBLE);
        etBody.setVisibility(View.VISIBLE);

        etBody.setText(bodyText);
        etTitle.setText(titleText);

        etBody.requestFocus();
    }



    public boolean save(CharSequence title, CharSequence body){
        if(isEditing){
            title = etTitle.getText();
            body = etBody.getText();
        }
        if(TextUtils.isEmpty(body.toString().trim())){
            Toast.makeText(getContext(), "Cannot save empty note", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(title.toString().trim())){
            int i = body.toString().trim().length() > 10 ? 9 : body.toString().trim().length();
            title = body.toString().trim().subSequence(0, i);
        }
        Log.e("123", "save: "+action);
        if(action == NEW_SAVE){
            DatabaseQueries.insertNotes(getContext(), title.toString(), body.toString(), enc, color);
            getActivity().finish();
        }else if(action == EDIT_SAVE){
            int b = emptyCheck(title, body);
            disableEditingMode(b);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etBody.getWindowToken(), 0);
            DatabaseQueries.updateNotes(getContext(),id, title.toString(), body.toString(), enc, color);
        }
        Toast.makeText(getActivity(), "Saved!!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void backPressed(){
        if(isEditing){
            CharSequence titleText = etTitle.getText();
            CharSequence bodyText = etBody.getText();
            final int b = emptyCheck(titleText, bodyText);
            boolean f = save(titleText, bodyText);
            disableEditingMode(b);
            getActivity().finish();
        }else{
            getActivity().finish();
        }
    }

    public void disableEditingMode(int b){
        etHorizontalLine.setVisibility(View.GONE);
        etTitle.setVisibility(View.GONE);
        etBody.setVisibility(View.GONE);
        if(b==0 || b == 2) {
            title.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
            horizontalLine.setVisibility(View.VISIBLE);
        }else if(b==1) {
            horizontalLine.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
            title.setText(etTitle.getText());
            body.setText(etBody.getText());

        }
        isEditing = false;
        getActivity().supportInvalidateOptionsMenu();
    }

    /*
    * return 0: discard notes
    * return 1: save and back
    * return 2: directly back
    *
    * */
    public int emptyCheck(CharSequence title, CharSequence body){
        if(title==null && body==null){
            return 2;
        }else if(title!=null && TextUtils.isEmpty(title.toString().trim().toString())){
            int i = body.toString().trim().length() > 10 ? 9 : body.toString().trim().length();
            title = body.toString().trim().subSequence(0, i);
            etTitle.setText(title);
            return 1;
        }else if(body!=null && body.toString().trim().length()==0){
            Toast.makeText(getContext(), "Cannot save empty note", Toast.LENGTH_SHORT).show();
            return 0;
        }else if(body!=null && title!=null && !TextUtils.isEmpty(body.toString().trim()) && !TextUtils.isEmpty(title.toString().trim())){
            return 1;
        }
        return 0;
    }

    public void shareNote() {
        CharSequence text = body.getText();
        if(text!=null && text.length()!=0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, title.getText().toString()+"\n\n"+body.getText().toString());
            intent.setType("text/plain");
            startActivity(intent);
        }else{
            Toast.makeText(getContext(), "Cannot Send Empty Text", Toast.LENGTH_SHORT).show();
        }
    }

}
