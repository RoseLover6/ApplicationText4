package com.example.applicationtext4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView name = (TextView) findViewById(R.id.name);
        name.setText("用户姓名:"+bundle.getString("name"));
        TextView age = (TextView) findViewById(R.id.age);
        age.setText("年龄:"+bundle.getByte("age"));
        TextView hobby = (TextView) findViewById(R.id.hobby);
        hobby.setText("爱好:"+bundle.getSerializable("hobby"));
        TextView height = (TextView) findViewById(R.id.height);
        height.setText("身高:"+bundle.getInt("height"));
    }
}
