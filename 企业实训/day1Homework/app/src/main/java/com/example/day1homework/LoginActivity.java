package com.example.day1homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_account;
    EditText et_pwd;
    Button bt_login;

    private String TAG = "LoginActivity";
    Context mContext = LoginActivity.this;
    public static String PREFERENCES_NAME = "userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Log.e("LoginActivity", "onCreate()");
    }

    private void initView() {
        et_account = findViewById(R.id.login_edit_account);
        et_pwd = findViewById(R.id.login_edit_pwd);
        bt_login = findViewById(R.id.login_button);
        bt_login.setOnClickListener(this);

        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCES_NAME , Context.MODE_PRIVATE);
        Log.d("LoginActivity" ,"preferences中取的userId=" + preferences.getString("userId" ,""));
        Log.d("LoginActivity" ,"preferences中取的userPwd=" + preferences.getString("userPwd" ,""));
        et_account.setText(preferences.getString("userId" ,""));
        et_pwd.setText(preferences.getString("userPwd" ,""));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Log.d("LoginActivity", "Login");

                SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCES_NAME , Context.MODE_PRIVATE);
                String s_account = et_account.getText().toString();
                String s_pwd = et_pwd.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userId", s_account);
                editor.putString("userPwd", s_pwd);
                editor.commit();

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LoginActivity", "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("LoginActivity", "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("LoginActivity", "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("LoginActivity", "onStop()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.e("LoginActivity", "onRestart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LoginActivity", "onDestroy()");
    }

}