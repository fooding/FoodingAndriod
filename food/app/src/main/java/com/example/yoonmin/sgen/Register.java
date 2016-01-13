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
import java.util.HashMap;

import com.fooding.connectserver.userConnect;
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
        String email = Edit_EMAIL.getText().toString().trim();
        String password = Edit_PW.getText().toString().trim();
        String name = Edit_NAME.getText().toString().trim();
        String phone = Edit_PHONE.getText().toString().trim();

        register(email,password,name,phone);
    }

    private void register(String email,String password,String name,String phone) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            userConnect ruc = new userConnect();

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
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("email",params[0]);
                data.put("password",params[1]);
                data.put("name",params[2]);
                data.put("phone",params[3]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(email,password,name,phone);
    }
}
