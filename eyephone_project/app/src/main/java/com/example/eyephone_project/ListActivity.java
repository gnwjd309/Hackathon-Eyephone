package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private TextView dateText;
    private ListView listview ;
    private ListViewAdapter adapter;
    private Button btnmode;
    List<Bbs> datas = new ArrayList<>();
    private String[] SimilarTitle = new String[7];
    private String[] SimilarKey = new String[7];
    private String[] titleArray = new String[7];
    private String[] keyArray = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.safebar=100;
        MainActivity.pagenum=1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btnmode = (Button) findViewById(R.id.button1);
        MainActivity.viewPoint = findViewById(R.id.view_point);

        Intent intent = getIntent();
        titleArray = intent.getExtras().getStringArray("title");
        keyArray = intent.getExtras().getStringArray("key");
        String keyvalue = intent.getExtras().getString("key2");
        int type = intent.getExtras().getInt("type");

        adapter = new ListViewAdapter();

        dateText = (TextView) findViewById(R.id.dateText);
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd, ");
        dateText.setText(Html.fromHtml("<b>"+simpleDate.format(mDate) + " Mar</b>"));

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        if(type == 1){
            for(int i=0; i<7; i++)
                adapter.addItem(titleArray[i]);
            adapter.setKey(keyArray);
        }
        else{
            for(int i=0; i<7; i++){
                SimilarKey[i] = Dao.number[Integer.parseInt(keyvalue)-1][i];
                Bbs b = Dao.datas.get(Integer.valueOf(SimilarKey[i]));
                SimilarTitle[i] = b.title;
            }
            for(int i=0; i<7; i++){
                adapter.addItem(SimilarTitle[i]);
                SimilarKey[i] = String.valueOf(Integer.parseInt(SimilarKey[i]) + 1);
            }
            adapter.setKey(SimilarKey);
        }

        adapter.notifyDataSetChanged();
        btnmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mode==0){
                    MainActivity.mode=1;
                }
                else if (MainActivity.mode==1){
                    MainActivity.mode=0;
                }
            }

        });
    }

    public void setarray(List<Bbs> datas) {
        System.out.println("**************************************");
        this.datas = datas;
    }
}