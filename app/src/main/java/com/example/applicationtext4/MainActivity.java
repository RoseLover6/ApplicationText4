package com.example.applicationtext4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mUsernumber, mPwd;
    private Button login, register;
    private static final String LOGINNUM = "17641241979";
    private static final String LOGINPWD = "521314";
    private IntentFilter intentFilter;
    private NetworkChangeRecever networkChangeRecever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//添加广播
        networkChangeRecever = new NetworkChangeRecever();
        registerReceiver(networkChangeRecever, intentFilter);
        mUsernumber = findViewById(R.id.editText1);
        mPwd = findViewById(R.id.editText2);
        login = findViewById(R.id.button1);
        register = findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsernumber.getText().toString().equals(LOGINNUM) && mPwd.getText().toString().equals(LOGINPWD)) {
                    Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MyToolAcyivity.class);
                    intent.putExtra("userNumber", mUsernumber.getText().toString());
                    intent.putExtra("pwd", mPwd.getText().toString());
                    startActivity(intent);
                    sendBroadcast(intent);
                } else {
                    Toast.makeText(MainActivity.this, "登录失败，账号或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeRecever);
    }

    public class NetworkChangeRecever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connecttivityManager;
            connecttivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connecttivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Log.i("ToF","networks is available");
            } else {
                Log.i("ToF","networks is unavailable");

            }
        }
    }
}

