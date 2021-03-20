package com.example.eyephone_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private String[] keyvalue;
    private TextView titleTextView;
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(300, 0, 0, 0);
        Animation alphaAnimation = new AlphaAnimation(0, 1);
        translateAnimation.setDuration(500);
        alphaAnimation.setDuration(1300);
        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(translateAnimation);
        animation.addAnimation(alphaAnimation);
        convertView.setAnimation(animation);

        titleTextView = (TextView) convertView.findViewById(R.id.newstitle);
        titleTextView.getShadowRadius();
        //titleTextView.setSingleLine(true);
        //titleTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //titleTextView.setSelected(true);

        ListViewItem listViewItem = listViewItemList.get(position);

        titleTextView.setText(listViewItem.getTitle());

        LinearLayout clickLayout = (LinearLayout) convertView.findViewById(R.id.clickLayout);
        clickLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "key value : " + keyvalue[pos], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ReadNewsActivity.class);
                intent.putExtra("key", keyvalue[pos]);
                //v.setBackgroundColor(Color.parseColor("#D5D5D5"));

                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }


    public void addItem(String title) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);

        // 여기서 title만 리스트에 추가하고 키값은 변수에 저장
        listViewItemList.add(item);
    }

    public void setKey(String[] keyArray) { keyvalue = keyArray; }
}
