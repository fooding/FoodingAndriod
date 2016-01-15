package com.fooding.connectserver;

import android.widget.EditText;

/**
 * Created by davinnovation on 2016-01-13.
 */
public class Configure {
    //URL to our login.php file
   // public static final String LOGIN_URL = "http://foodingtest.azurewebsites.net/login.php";

    public static final String LOGIN_URL = "http://211.202.243.45/login.php";
    public static final String UPLOAD_URL = "http://211.202.243.45/write.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php

    //private static final String REGISTER_URL = "http://foodingtest.azurewebsites.net/register.php";

    public static final String REGISTER_URL = "http://211.202.243.45/register.php";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TAGS = "tag";
    public static final String KEY_TEXT = "text";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
    public static boolean loggedIn = false;
    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";


    public static String email=null;
    public static String password = null;



}