package com.example.eyephone_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        titleTextView = (TextView) convertView.findViewById(R.id.newstitle);

        ListViewItem listViewItem = listViewItemList.get(position);

        titleTextView.setText(listViewItem.getTitle());

        LinearLayout clickLayout = (LinearLayout) convertView.findViewById(R.id.clickLayout);
        clickLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "key value : " + keyvalue[pos], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ReadNewsActivity.class);
                intent.putExtra("key", keyvalue[pos]);
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
