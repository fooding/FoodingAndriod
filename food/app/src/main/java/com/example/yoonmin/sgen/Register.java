package com.example.yoonmin.sgen;

import android.app.ProgressDialog;
import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yoonm on 2016-01-12.
 */
public class Register extends Activity{

    EditText Edit_NAME, Edit_EMAIL, Edit_PW, Edit_PHONE;

    Button Btn_Result;

    private static final String REGISTER_URL = "http://foodingtest.azurewebsites.net/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Edit_NAME = (EditText) findViewById(R.id.Edit_NAME);
        Edit_EMAIL = (EditText) findViewById(R.id.Edit_EMAIL);
        Edit_PW = (EditText) findViewById(R.id.Edit_PW);
        Edit_PHONE =(EditText) findViewById(R.id.Edit_PHONE);

        Btn_Result = (Button) findViewById(R.id.Btn_Result);


        Btn_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = Edit_EMAIL.getText().toString().trim().toLowerCase();
        String password = Edit_PW.getText().toString().trim().toLowerCase();
        String name = Edit_NAME.getText().toString().trim().toLowerCase();
        String phone = Edit_PHONE.getText().toString().trim().toLowerCase();

        register(email,password,name,phone);
    }

    private void register(String email,String password,String name,String phone) {
        String urlSuffix = "?email="+email+"&name="+name+"&password="+password+"&phone="+phone;
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}
