package com.whiteboard.securenotes.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.whiteboard.securenotes.PreferencesActivity;
import com.whiteboard.securenotes.R;

public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    boolean flag = true;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
        updateTextOfPref(false);
    }

    private void updateTextOfPref(boolean flag){

        SharedPreferences preference = getPreferenceScreen().getSharedPreferences();
        String masterPassword = preference.getString(getString(R.string.pref_password_key), null);
        String securityQuestion = preference.getString(getContext().getString(R.string.pref_question_key), null);
        Log.e("123", "masterPassword: "+masterPassword+" securityQuestion:"+securityQuestion);

        if(masterPassword==null || TextUtils.isEmpty(masterPassword)){
            // if master password empty

            //set title and summary of master password
            addMasterPassword();
            //disable security question
            disableSecurityQuestion("Question Not Set");
            //disable reset password
            disableResetPassword();
        }else{
            // if master password exists
            //set password editing
            editMasterPassword();
            //security question empty
            if(securityQuestion==null || TextUtils.isEmpty(securityQuestion)){
                //enable security question
                enableSecurityQuestion("Question Not Set");
                //disable reset password
                disableResetPassword();
            }
            //security question not empty
            else if(securityQuestion!=null && !TextUtils.isEmpty(securityQuestion)){
                //disable security question
                disableSecurityQuestion("Question Set");
                //enable reset password
                enableResetPassword();
            }
        }

        if(flag){
            getActivity().finish();
            startActivity(getActivity().getIntent());
        }

    }

    private void editMasterPassword(){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(0).setSummary("Master Password Set");
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(0).setTitle("Edit Master Password");
    }

    private void addMasterPassword(){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(0).setTitle("Set Master Password");
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(0).setSummary("Master Password Not Set");
    }

    private void enableSecurityQuestion(String value){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(1).setEnabled(true);
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(1).setSummary(value);
    }

    private void disableSecurityQuestion(String value){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(1).setEnabled(false);
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(1).setSummary(value);
    }

    private void enableResetPassword(){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(2).setEnabled(true);
    }

    private void disableResetPassword(){
        ((PreferenceCategory) getPreferenceScreen().getPreference(1)).getPreference(2).setEnabled(false);
    }

    public boolean masterPasswordProcess(final Preference preference, final String value){
        //setting new password
        if (value == null) {
            String message = "Enter New Master password";
            AllNotesFragment.setMasterPassword(getContext(), message, false, false);
            return true;
        } else {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            final View view = inflater.inflate(R.layout.add_password, null, false);
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setTitle("Edit Master Password")
                    .setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etCurrentPassword = (EditText) view.findViewById(R.id.current_password);
                            EditText etNewPassword = (EditText) view.findViewById(R.id.new_password);
                            EditText etConfirmNewPassword = (EditText) view.findViewById(R.id.confirm_new_password);

                            if (value.equals(etCurrentPassword.getText().toString())) {
                                if (etConfirmNewPassword.getText().toString().equals(etNewPassword.getText().toString())) {
                                    String password = etConfirmNewPassword.getText().toString();
                                    if(TextUtils.isEmpty(password.trim())){
                                        Toast.makeText(getContext(), "Master Password Cannot be Empty", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    SharedPreferences.Editor editor = preference.getSharedPreferences().edit();
                                    editor.putString("master_password", password);
                                    editor.apply();
                                    Toast.makeText(getActivity(), "Password Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Password Does Not Match", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Master Password Is Not Correct", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create();
            alertDialog.show();
            return true;
        }
    }
    final String[] questionAnswer = {null, null};
    public AlertDialog giveSecurityQuestion(Context context){

        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_security_question, null, false);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setTitle("Set Security Question")
                .setPositiveButton("Set Question", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Spinner question = (Spinner) view.findViewById(R.id.spinner_select_question);
                        EditText answer = (EditText) view.findViewById(R.id.sec_answer);

                        if(question.getSelectedItemPosition()==-1){
                            Toast.makeText(getContext(), "Select Question", Toast.LENGTH_SHORT).show();
                        }else if(answer.getText()==null || TextUtils.isEmpty(answer.getText().toString())){
                            Toast.makeText(getContext(), "Enter Answer", Toast.LENGTH_SHORT).show();
                        }else if(question.getSelectedItemPosition()!=-1 && answer.getText()!=null && !TextUtils.isEmpty(answer.getText().toString())){
                            String questionStr = (String) question.getSelectedItem();
                            String answerStr = answer.getText().toString();
                            if(TextUtils.isEmpty((answerStr).trim())){
                                Toast.makeText(getContext(), "Answer Cannot be null", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(TextUtils.isEmpty((questionStr).trim())){
                                Toast.makeText(getContext(), "Please select question", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            questionAnswer[0] = questionStr.trim()+":"+answerStr.trim();

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        //dialog.show();
        return dialog;
    }


    public boolean securityQuestionProcess(final Preference preference, final String value){
        Context baseContext = getActivity();
        AlertDialog alertDialog = giveSecurityQuestion(baseContext);
        alertDialog.show();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SharedPreferences.Editor editor = preference.getSharedPreferences().edit();
                editor.putString(getContext().getString(R.string.pref_question_key), questionAnswer[0]);
                editor.apply();
                Toast.makeText(getContext(), "Security Question Updated!!", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }


    public boolean resetPasswordProcess(final Preference preference, final String value){
        final Context baseContext = getActivity();
        AlertDialog alertDialog = giveSecurityQuestion(baseContext);
        alertDialog.show();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String sharedPref = preference.getSharedPreferences().getString(getContext().getString(R.string.pref_question_key), null);
                if(sharedPref!=null && sharedPref.equals(questionAnswer[0])){
                    flag = false;
                    preference.getSharedPreferences().edit().remove(getContext().getString(R.string.pref_question_key)).commit();
                    preference.getSharedPreferences().edit().remove(getContext().getString(R.string.pref_password_key)).commit();
                    flag = true;
                    AllNotesFragment.setMasterPassword(baseContext, "Enter New Master Password", false, false);
                    Toast.makeText(baseContext, "Password Reset. Please set your new password", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "Wrong Question Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }

    public boolean sendFeedback(){

        String mailTo = "mailto:sarvesh8481@example.org" +
                "?cc=" + "sarvesh8481@gmail.com" +
                "&subject=" + Uri.encode("Feedback");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailTo));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
        return true;
    }

    @Override
    public boolean onPreferenceTreeClick(final Preference preference) {
        String key = preference.getKey();
        final String value = preference.getSharedPreferences().getString(key, null);
        boolean returnValue = false;
        if(key.equals(getString(R.string.pref_password_key))){
            returnValue = masterPasswordProcess(preference, value);
        }else if(key.equals(getContext().getString(R.string.pref_question_key))){
            returnValue = securityQuestionProcess(preference, value);
        }else if(key.equals(getContext().getString(R.string.pref_reset_password_key))){
            returnValue = resetPasswordProcess(preference, value);
        }else if(key.equals(getString(R.string.pref_feedback_key))){
            returnValue = sendFeedback();
        }

        if(returnValue!=false){
            return returnValue;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.e("123", "onSharedPreferenceChanged: "+key+" "+ flag);
        if(flag) {
            updateTextOfPref(true);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


}
