package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fooding.connectserver.Config;

import static com.fooding.connectserver.Config.*;

/**
 * Created by yoonmin on 2015-11-22.
 */
public class Me extends Activity{

    Button Btn_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        Btn_Logout = (Button) findViewById(R.id.Btn_Logout);


        Btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이벤트 들어가는 곳

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Config.LOGGEDIN_SHARED_PREF);
                editor.remove(Config.EMAIL_SHARED_PREF);

                editor.remove(SHARED_PREF_NAME);

                editor.commit();
                loggedIn=false;
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}