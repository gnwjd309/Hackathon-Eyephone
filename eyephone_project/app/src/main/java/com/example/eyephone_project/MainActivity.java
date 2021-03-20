package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private TextView Title;
    ListViewAdapter adapter;
    Dao dao=new Dao();
    String[] strTitle = new String[5];
    String[] strKey = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        Title = (TextView) findViewById(R.id.Title);
        dao.LoadDao();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(MainActivity.this, ReadNewsActivity.class);
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                System.out.println(dao.datas.size());
                for(int i=0;i<5;i++) {
                    Bbs b=dao.datas.get(i);
                    strTitle[i] = b.title;
                    strKey[i] = b.key;
                    System.out.println("key : "+strKey[i]);
                }
                intent.putExtra("title", strTitle);
                intent.putExtra("key", strKey);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

    }
}