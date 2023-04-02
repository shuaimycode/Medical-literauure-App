package com.he.yiliao;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {

    private ImageView iv_searchBack;
    private Button btn_search;
    private EditText et_searchText;
//    private ListViewForScrollView listViewForScrollView;
    private TextView tv_historyText,tv_clearHistory;
    private List<String> searchList=new ArrayList<>();
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        setListeners();
    }

    /**
     * 获取相对应的控件
     */
    private void initView() {
        iv_searchBack=findViewById(R.id.iv_searchback);
        btn_search=findViewById(R.id.btn_search);
        et_searchText=findViewById(R.id.et_searchtext);
//        listViewForScrollView=findViewById(R.id.search_listview);
        tv_historyText=findViewById(R.id.tv_searchhistory);
        tv_clearHistory=findViewById(R.id.tv_clearsearch);
    }

    /**
     * 实现搜索功能
     */
    private void setListeners() {

        /**
         * 存放搜索历史的表
         */
        SQLiteOpenHelper helper=SearchSQLiteOpenHelper.getmInstance(SearchActivity.this);

        /**
         * 返回服务页面
         */
        iv_searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 给搜索历史传入空
         */
        tv_historyText.setText(" ");

        /**
         * 搜索按钮的监听
         */
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String obtain=et_searchText.getText().toString().trim();
                /**
                 * 下一次点击搜索按钮时清空前一次搜索列表
                 */
                count++;
                if(count%1==0){
                    searchList.clear();
                }
                /**
                 * 将搜索框内容放入到搜索历史当中去
                 */
                tv_historyText.append(obtain+" ");
                /**
                 * 将搜索框内容放入到搜索历史表当中去
                 */
                SQLiteDatabase db_history=helper.getWritableDatabase();
                if(db_history.isOpen()){
                    String add_historysearchname_sql="insert into historysearch(historyname) values(?);";
                    db_history.execSQL(add_historysearchname_sql,new Object[]{obtain});
                    Toast.makeText(SearchActivity.this,"增加成功",Toast.LENGTH_SHORT).show();
                }
                db_history.close();

                /**
                 * 判断搜索框是否为空
                 */
                if(obtain.isEmpty()){
                    Toast.makeText(SearchActivity.this,"搜索框为空",Toast.LENGTH_SHORT).show();
                    searchList.clear();
                }else{
                    /**
                     * 获取数据库中的表，取出搜索框中的首字符放入查询语句进行查询相匹配的内容
                     */
                    SQLiteDatabase db_search=helper.getReadableDatabase();
                    if(db_search.isOpen()){
                        String firstChar=obtain.substring(0,1);
                        String query_sql="select * from search where searchname like '"+firstChar+"%'";
                        Cursor cursor = db_search.rawQuery(query_sql,null);
                        if(cursor.getCount()==0){
                            Toast.makeText(SearchActivity.this,"没有该服务",Toast.LENGTH_SHORT).show();
                        }else{
                            cursor.moveToFirst();
                            String searchname=cursor.getString(cursor.getColumnIndexOrThrow("searchname"));
                            searchList.add(searchname);
                        }
                        while(cursor.moveToNext()){
                            String searchname1=cursor.getString(cursor.getColumnIndexOrThrow("searchname"));
                            searchList.add(searchname1);
                        }
                        cursor.close();
                    }
                    db_search.close();
                }
                /**
                 * 自定义搜索适配器，将适配器放入自定义的listview当中
                 */
                //SearchBaseAdapter searchBaseAdapter = new SearchBaseAdapter();
                //listViewForScrollView.setAdapter(searchBaseAdapter);

            }
        });



        SQLiteDatabase db_get_history=helper.getReadableDatabase();
        if(db_get_history.isOpen()){
            String sql_history_query="select * from historysearch;";
            Cursor cursor = db_get_history.rawQuery(sql_history_query, null);
            if(cursor.getCount()==0){
                Toast.makeText(SearchActivity.this,"没有搜索历史",Toast.LENGTH_SHORT).show();
            }else{
                cursor.moveToFirst();
                String history_name=cursor.getString(cursor.getColumnIndexOrThrow("historyname"));
                tv_historyText.append(history_name+" ");
            }
            while (cursor.moveToNext()){
                String history_name=cursor.getString(cursor.getColumnIndexOrThrow("historyname"));
                tv_historyText.append(history_name+" ");
            }
            cursor.close();
        }
        db_get_history.close();



        tv_clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db_delete_history=helper.getWritableDatabase();
                if(db_delete_history.isOpen()){
                    String sql_delete_history="delete  from historysearch;";
                    db_delete_history.execSQL(sql_delete_history);
                    Toast.makeText(SearchActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                }
                tv_historyText.setText(" ");
            }
        });
    }


    class ViewHolder{
        TextView tv_searchLisItem;
    }
}