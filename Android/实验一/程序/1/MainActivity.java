package com.example.day1homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_start;
    Button bt_stop;
    Button bt_back;
    TextView textView2;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.e("MainActivity", "onCreate()");

        /*Intent i = getIntent();
        String uName = i.getStringExtra("uName");
        short uAge = i.getShortExtra("uAge", (short)0);
        System.out.println(uName);
        System.out.println(uAge);*/

        intent = getIntent();
        String s = intent.getStringExtra("position");
        textView2.setText(s);
    }

    public void initView() {
        bt_start = findViewById(R.id.start_button);
        bt_stop = findViewById(R.id.stop_button);
        bt_back = findViewById(R.id.back_button);
        textView2 = findViewById(R.id.textView2);
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        bt_back.setOnClickListener(this);
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
            case R.id.back_button:
                /*Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);*/

                String ss = "addressAA";
                intent.putExtra("position", ss);
                setResult(RESULT_OK, intent);
                finish();

                break;
        }
    }

/*    @Override
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
    }*/

}