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
public class Dao {
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    DatabaseReference nextRef;
    Pattern p = Pattern.compile("(?<=[(\\[])[^()\\[\\]]*(?=[)\\]])");
    static List<Bbs> datas = new ArrayList<>();
    static List<String> ndatas = new ArrayList<>();
    static String [][] number =new String[1000][10];
    static float [][] percent =new float[1000][10];

    /*
        예를 들어서 내가 본 기사의 키가 1이라고 치면
        관련된 기사를 가져오려면 nember[1][5개]
        percent[1][5개]
     */

    public void LoadDao() {
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("news");
        nextRef = database.getReference("next");
        bbsRef.addListenerForSingleValueEvent(postListener);
        nextRef.addListenerForSingleValueEvent(postListener1);
        System.out.println(datas.size());
    }

    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // 위에 선언한 저장소인 datas를 초기화하고
            datas.clear();
            // bbs 레퍼런스의 스냅샷을 가져와서 레퍼런스의 자식노드를 바복문을 통해 하나씩 꺼내서 처리.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                String key  = snapshot.getKey();
                System.out.println(key);

                Bbs bbs = snapshot.getValue(Bbs.class); // 컨버팅되서 Bbs로........
                bbs.key = key;
                datas.add(bbs);
            }

            //adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };

    ValueEventListener postListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // 위에 선언한 저장소인 datas를 초기화하고
            ndatas.clear();
            // bbs 레퍼런스의 스냅샷을 가져와서 레퍼런스의 자식노드를 바복문을 통해 하나씩 꺼내서 처리.
            for( DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                String key  = snapshot.getKey();
                //ndatas.add(snapshot.getValue().toString());
                Matcher m = p.matcher(snapshot.getValue().toString());
                int io=0;
                while (m.find()) {
                    String[] asd = m.group().split(",");
                    String nkey = asd[0];
                    float per = Float.parseFloat(asd[1]) * 100;
                    if ( Integer.parseInt(asd[0]) + 1 != Integer.parseInt(key)) {
                        number[Integer.parseInt(key) - 1][io] = nkey;
                        percent[Integer.parseInt(key) - 1][io] = per;
                        io++;
                    }
                }
            }
            System.out.println("+++++++++++++"+number[999][8]);
            //adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
}
