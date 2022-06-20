package com.example.day1homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_start;
    Button bt_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.e("MainActivity", "onCreate()");
    }

    public void initView() {
        bt_start = findViewById(R.id.start_button);
        bt_stop = findViewById(R.id.stop_button);
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                startService(new Intent(this, MyService.class));
                Log.d("MainActivity.MyService", "Start Service");
                break;
            case R.id.stop_button:
                stopService(new Intent(this, MyService.class));
                Log.d("MainActivity.MyService", "Stop Service");
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy()");
    }

}