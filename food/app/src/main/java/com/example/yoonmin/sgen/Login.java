package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.fooding.connectserver.Configure;
import java.util.HashMap;
import java.util.Map;

import static com.fooding.connectserver.Configure.SHARED_PREF_NAME;
import static com.fooding.connectserver.Configure.loggedIn;

/**
 * Created by yoonm on 2016-01-12.
 */
public class Login extends Activity{

    EditText Edit_ID, Edit_PW;
    Button Btn_Login, Btn_Register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Edit_ID = (EditText) findViewById(R.id.Edit_ID);
        Edit_PW = (EditText) findViewById(R.id.Edit_PW);

        Btn_Login = (Button) findViewById(R.id.Btn_Login);
        Btn_Register = (Button) findViewById(R.id.Btn_Register);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Configure.LOGGEDIN_SHARED_PREF);
        editor.remove(Configure.EMAIL_SHARED_PREF);

        editor.remove(SHARED_PREF_NAME);

        editor.commit();
        loggedIn=false;

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아이디, 비밀번호
                login();
            }
        });

        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Configure.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Configure.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(Login.this, NewsFeed.class);
            startActivity(intent);
        }
    }

    private void login() {
        Configure.email = Edit_ID.getText().toString().trim();
        Configure.password = Edit_PW.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configure.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(Configure.LOGIN_SUCCESS)){
                            //Creating a shared preference

                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Configure.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Configure.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Configure.EMAIL_SHARED_PREF, Configure.email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Configure.KEY_EMAIL, Configure.email);
                params.put(Configure.KEY_PASSWORD, Configure.password);

                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
