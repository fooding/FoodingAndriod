package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아이디, 비밀번호
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
}
