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

public class AddLiteratureActivity extends AppCompatActivity {

    private LiteratureSQLite mSQlite;
    private EditText topic;
    private EditText time;
    private EditText author;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_literature);

        add = findViewById(R.id.add);
        topic = findViewById(R.id.edit_topic);
        time =findViewById( R.id.edit_time);
        author = findViewById(R.id.edit_author);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic2 = topic.getText().toString().trim();
                String time2 = time.getText().toString().trim();
                String author2 = author.getText().toString().trim();
                if(!TextUtils.isEmpty(topic2)&&!TextUtils.isEmpty(time2)){
                    mSQlite.add(topic2,time2,author2);
                    Toast.makeText(AddLiteratureActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(AddLiteratureActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();}
            }
        });
        mSQlite = new LiteratureSQLite(AddLiteratureActivity.this);
    }

}