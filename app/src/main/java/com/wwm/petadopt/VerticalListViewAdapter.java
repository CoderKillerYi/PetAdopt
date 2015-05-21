package com.wwm.petadopt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.ArrayList;

/**
 * Created by maaibin on 2015/5/20.
 */

public class VerticalListViewAdapter extends BaseSwipeAdapter {


    private Context mContext;
    private ArrayList<NewsModel> arrNews ;


    public VerticalListViewAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public void setArrayNews(ArrayList<NewsModel> arrNews ){
        this.arrNews=arrNews;
    }
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.vertical_listview_item;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.vertical_listview_item, null);



        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
      swipeLayout.addSwipeListener(new SimpleSwipeListener() {
           @Override
           public void onOpen(SwipeLayout layout) {
//               YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
       });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.button_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click collection", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.button_adoption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click  adoption", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView t1 = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView t2 = (TextView)convertView.findViewById(R.id.txtDesc);
        TextView t3 = (TextView)convertView.findViewById(R.id.txtDate);
       // t.setText((position + 1) + ".");
        NewsModel nm = arrNews.get(position);
        t1.setText(nm.getTitle());
        t2.setText(nm.getDescription());
        t3.setText(nm.getPubDate());
    }

    @Override
    public int getCount() {
       // return 30;
        return arrNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}


