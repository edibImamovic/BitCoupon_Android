package com.project.bitcoupon.bitcoupon.controllers;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends BaseActivity {

    private SharedPreferences mSharedPreferences;
    public static final String TAG = "MainActivity_TAG";
    public static final String SHARED_PREFERENCES = "ba.bitcoupon.shared_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // On create check if user was logged in last time

        mSharedPreferences = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String email = mSharedPreferences.getString(
                getString(R.string.key_user_email),
                null
        );

        String password = mSharedPreferences.getString(
                getString(R.string.key_user_password),
                null
        );

        if (email != null && password != null) {
            setUserData(email, password);
            Log.d("LOGIN", "email> " + email + " password> " + password);
            loginUser();
        }

        //If user is not logged in already, then take username and password from user input
        final EditText editEmail = (EditText) findViewById(R.id.edit_text_email);
        final EditText editPassword = (EditText) findViewById(R.id.edit_text_password);

        Button buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                setUserData(email, password);
                loginUser();
            }
        });

        Button buttonContinueWithoutLogging = (Button) findViewById(R.id.button_continue_without_logging);
        buttonContinueWithoutLogging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueWithoutLogging = new Intent(MainActivity.this, CouponActivity.class);
                startActivity(continueWithoutLogging);
            }
        });
    }


public void notification(){

    Resources r = getResources();
    PendingIntent pi = PendingIntent
            .getActivity(this, 0, new Intent(this, CouponActivity.class), 0);
    Notification notification = new NotificationCompat.Builder(this)
            .setTicker(r.getString(R.string.ticker))
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(r.getString(R.string.titleofnotification))
            .setContentText(r.getString(R.string.contentofnotification))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build();
    NotificationManager notificationManager = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(0,notification);
}


    /**
     * Send post request
     */

    private void loginUser(){
        String url = getString(R.string.service_login);
        Callback callback = loginVerification();
        String json = UserData.getInstance().toJson();

        ServiceRequest.post(url, json, callback);
    }

    /**
     * This is response for our JSON request
     * onFailure - if JSON don't successe
     * onResponse - if JSON success we expect this attributes
     * @return
     */
    private Callback loginVerification(){
        return new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                makeToast(R.string.toast_try_again);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseJson = response.body().string();
                try {
                    JSONObject user = new JSONObject(responseJson);
                    int id = user.getInt("id");
                    if(id > 0){
                        Log.d(TAG, response.body().toString());

                        String username = user.getString("name");
                        String updates = user.getString("updates");
                        if (updates.equals("true")){
                            notification();
                        }
                            Log.d(TAG, response.body().toString());
                            UserData userData = UserData.getInstance();
                            userData.setId(id);
                            userData.setUsername(username);
                            saveUserCredentials();
                            goToPosts();

                    }
                } catch (JSONException e) {
                    makeToast(R.string.toast_try_again);
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * After Loging in set email and password in shearedPreferences
     */
    private void saveUserCredentials(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        UserData userData = UserData.getInstance();

        editor.putString(
                getString(R.string.key_user_email),
                userData.getEmail()
        );

        editor.putString(
                getString(R.string.key_user_password),
                userData.getPassword()
        );
        editor.commit();
    }

    /**
     * This is method for making message on user mobile display - Toast
     * @param messageId
     */
    private void makeToast(final int messageId){
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,
                                messageId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Go to another activity
     */
    private void goToPosts(){
        Intent test = new Intent(this, CouponActivity.class);
        startActivity(test);
    }

    /**
     * Set emial and Password in UserData
     * @param email
     * @param password
     */
    private void setUserData(String email, String password){
        UserData userData = UserData.getInstance();
        userData.setEmail(email);
        userData.setPassword(password);

    }
}
