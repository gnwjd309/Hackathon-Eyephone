package com.example.eyephone_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Magnifier;
import android.widget.TextView;
import android.widget.Toast;

public class ReadNewsActivity extends AppCompatActivity {

    private int count=0;
    private int count1 = 0;
    int[] viewPosition = new int[2];
    private Button btnbottom, btntop;
    private TextView newstext1, newstext2,newstext3, newstext4, newstext5;
    //private String[] keyvalue;
    static private String keyvalue;

    setNewsString setnewsstring = new setNewsString();

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readnews);

        Intent intent = getIntent();
        keyvalue = intent.getExtras().getString("key");
        System.out.println(Dao.datas.size());

        Bbs b=Dao.datas.get(Integer.parseInt(keyvalue)-1);
        String contents = b.content;

        newstext1 = (TextView) findViewById(R.id.newstext1);
        newstext2 = (TextView) findViewById(R.id.newstext2);
        newstext3 = (TextView) findViewById(R.id.newstext3);
        newstext4 = (TextView) findViewById(R.id.newstext4);
        newstext5 = (TextView) findViewById(R.id.newstext5);

        btnbottom = (Button) findViewById(R.id.btnbottom);
        btntop = (Button) findViewById(R.id.btntop);

        setNewsString.setContent(contents);
        String[] newsContents = setNewsString.getContent();
        count = setNewsString.getCount();

        newstext1.setText(newsContents[0]);
        newstext2.setText(newsContents[1]);
        newstext3.setText(newsContents[2]);
        newstext4.setText(newsContents[3]);
        newstext5.setText(newsContents[4]);

        btnbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count1 > 0) {
                    count1 = count1 - 5;
                    newstext1.setText(newsContents[count1]);
                    newstext2.setText(newsContents[count1+1]);
                    newstext3.setText(newsContents[count1+2]);
                    newstext4.setText(newsContents[count1+3]);
                    newstext5.setText(newsContents[count1+4]);

                    newstext1.setTextSize(24);
                    newstext2.setTextSize(24);
                    newstext3.setTextSize(24);
                    newstext4.setTextSize(24);
                    newstext5.setTextSize(24);
                }
                else if(count1 == 0){ //if(count == 0){
                    Toast.makeText(getApplicationContext(), "처음 글입니다.", Toast.LENGTH_SHORT).show();

                    newstext1.setTextSize(24);
                    newstext2.setTextSize(24);
                    newstext3.setTextSize(24);
                    newstext4.setTextSize(24);
                    newstext5.setTextSize(24);
                }
            }
        });

        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count1 < count-5) {
                    count1 = count1 + 5;
                    newstext1.setText(newsContents[count1+1]);
                    newstext2.setText(newsContents[count1+2]);
                    newstext3.setText(newsContents[count1+3]);
                    newstext4.setText(newsContents[count1+4]);
                    newstext5.setText(newsContents[count1+5]);

                    newstext1.setTextSize(24);
                    newstext2.setTextSize(24);
                    newstext3.setTextSize(24);
                    newstext4.setTextSize(24);
                    newstext5.setTextSize(24);
                }

                else {//if (count >= newsArray.length-1){
                    newstext1.setTextSize(24);
                    newstext2.setTextSize(24);
                    newstext3.setTextSize(24);
                    newstext4.setTextSize(24);
                    newstext5.setTextSize(24);

                    Toast.makeText(getApplicationContext(), "마지막 글입니다.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "목록으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), ListActivity.class);
                    intent.putExtra("key2", keyvalue);
                    intent.putExtra("type", 2);
                    v.getContext().startActivity(intent);
                }
            }
        });

        newstext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newstext1.setTextSize(33);
                newstext2.setTextSize(24);
                newstext3.setTextSize(24);
                newstext4.setTextSize(24);
                newstext5.setTextSize(24);
            }
        });
        newstext1.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                item.isEnabled();
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) { }
        });
        newstext1.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                // 이벤트 강제 실행 performClick();
                String text = newstext1.getText().toString().substring(newstext1.getSelectionStart(), newstext1.getSelectionEnd());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        newstext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newstext1.setTextSize(24);
                newstext2.setTextSize(30);
                newstext3.setTextSize(24);
                newstext4.setTextSize(24);
                newstext5.setTextSize(24);
            }
        });
        newstext2.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                item.isEnabled();
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) { }
        });
        newstext2.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                // 이벤트 강제 실행 performClick();
                String text = newstext2.getText().toString().substring(newstext2.getSelectionStart(), newstext2.getSelectionEnd());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        newstext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newstext1.setTextSize(24);
                newstext2.setTextSize(24);
                newstext3.setTextSize(30);
                newstext4.setTextSize(24);
                newstext5.setTextSize(24);
            }
        });
        newstext3.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                item.isEnabled();
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) { }
        });
        newstext3.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                // 이벤트 강제 실행 performClick();
                String text = newstext3.getText().toString().substring(newstext3.getSelectionStart(), newstext3.getSelectionEnd());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        newstext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newstext1.setTextSize(24);
                newstext2.setTextSize(24);
                newstext3.setTextSize(24);
                newstext4.setTextSize(30);
                newstext5.setTextSize(24);
            }
        });
        newstext4.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                item.isEnabled();
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) { }
        });
        newstext4.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                // 이벤트 강제 실행 performClick();
                String text = newstext4.getText().toString().substring(newstext4.getSelectionStart(), newstext4.getSelectionEnd());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        newstext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newstext1.setTextSize(24);
                newstext2.setTextSize(24);
                newstext3.setTextSize(24);
                newstext4.setTextSize(24);
                newstext5.setTextSize(30);
            }
        });
        newstext5.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                item.isEnabled();
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) { }
        });
        newstext5.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                // 이벤트 강제 실행 performClick();
                String text = newstext5.getText().toString().substring(newstext5.getSelectionStart(), newstext5.getSelectionEnd());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}