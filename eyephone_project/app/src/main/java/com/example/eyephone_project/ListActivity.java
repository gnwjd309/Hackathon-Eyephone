package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listview ;
    private ListViewAdapter adapter;
    List<Bbs> datas = new ArrayList<>();
    private String[] SimilarTitle = new String[5];
    private String[] SimilarKey = new String[5];
    private String[] titleArray = new String[5];
    private String[] keyArray = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        titleArray = intent.getExtras().getStringArray("title");
        keyArray = intent.getExtras().getStringArray("key");
        String keyvalue = intent.getExtras().getString("key2");
        int type = intent.getExtras().getInt("type");

        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        if(type == 1){
            for(int i=0; i<5; i++)
                adapter.addItem(titleArray[i]);
            adapter.setKey(keyArray);
        }
        else{
            for(int i=0; i<5; i++){
                SimilarKey[i] = String.valueOf(Dao.number[Integer.parseInt(keyvalue)-1][i]);
                Bbs b = Dao.datas.get(Integer.valueOf(SimilarKey[i]));
                SimilarTitle[i] = b.title;
            }
            for(int i=0; i<5; i++)
                adapter.addItem(SimilarTitle[i]);
            adapter.setKey(SimilarKey);
        }

        adapter.notifyDataSetChanged();
    }
    public void setarray(List<Bbs> datas) {
        System.out.println("**************************************");
        this.datas = datas;
    }
}