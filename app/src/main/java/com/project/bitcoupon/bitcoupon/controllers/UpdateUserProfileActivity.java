package com.project.bitcoupon.bitcoupon.controllers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.service.ServiceRequest;
import com.project.bitcoupon.bitcoupon.singletons.UserData;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UpdateUserProfileActivity extends BaseActivity {

    private EditText username;
    private String mUsername;
    private EditText surname;
    private String mSurname;
    private EditText dob;
    private String mDob;
    private EditText email;
    private String mEmail;
    private EditText address;
    private String mAddress;
    private EditText city;
    private String mCity;
    private Button mSaveProfileChangres;
    private  int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        userId = UserData.getInstance().getId();

        username = (EditText) findViewById(R.id.editText_updateProfile_username);
        surname = (EditText) findViewById(R.id.editText_updateProfile_surname);
        // dob = (EditText) findViewById(R.id.editText_updateProfil_dob);

        mSaveProfileChangres = (Button) findViewById(R.id.button_updateUserProfile_save);
        mSaveProfileChangres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = username.getText().toString();
                mSurname = surname.getText().toString();
                //   mDob = dob.getText().toString();

                String url = getString(R.string.service_edit_user_profile);
                JSONObject editProfile = new JSONObject();
                try {
                    editProfile.put("id", Integer.toString(userId));

                    editProfile.put("username", mUsername);

                    editProfile.put("surname", mSurname);
                    //  editProfile.put("dob", mDob);

                    Log.d("TAG", "JSON username ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String json = editProfile.toString();
                Log.d("TAG", json);
                ServiceRequest.post(url, json, getProfile());

                Intent i = new Intent(UpdateUserProfileActivity.this, CouponActivity.class);
                startActivity(i);


            }
        });
    }

    private void makeToast(final int messageId){

        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UpdateUserProfileActivity.this,
                                messageId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Callback  getProfile() {
        return new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                makeToast(R.string.toast_try_again);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseJson = response.body().string();

                try {
                    JSONObject editProfile = new JSONObject(responseJson);
                    String msg = editProfile.getString("info");
                    Log.d("PORUKA", msg);
                    //Toast.makeText(UpdateUserProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Intent userProfile = new Intent(UpdateUserProfileActivity.this, CouponActivity.class);
                    startActivity(userProfile);
                } catch (JSONException e) {
                    makeToast(R.string.toast_try_again);
                    e.printStackTrace();
                }
            }
        };
    }
}
