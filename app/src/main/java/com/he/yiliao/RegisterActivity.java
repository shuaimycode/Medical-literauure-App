package com.he.yiliao;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private LoginSQLite mSQlite;
    private EditText username;
    private EditText userpassword;
    private EditText userphone;
    private EditText useremail;
    private Button reday;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reday = findViewById(R.id.zhuce);
        back = findViewById(R.id.back);
        username = findViewById(R.id.edit_userName);
        userpassword =findViewById( R.id.edit_password);
        userphone = findViewById(R.id.edit_phone);
        useremail = findViewById(R.id.edit_email);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        reday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                String phone = userphone.getText().toString().trim();
                String email = useremail.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    mSQlite.add(name,password,phone,email);
                    Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(RegisterActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();}
            }
        });
        mSQlite = new LoginSQLite(RegisterActivity.this);
    }
}