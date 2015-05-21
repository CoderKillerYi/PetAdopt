package com.wwm.petadopt;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MoreFragment extends Fragment {
    private String TAG = this.getClass().getSimpleName();

    //private RequestQueue mRequestQueue;
   // private ArrayList<NewsModel> arrNews ;
    //private LayoutInflater lf;
   // private VolleyAdapter va;
    //private ProgressDialog pd;
    private RequestQueue mRequestQueue;
    private ArrayList<NewsModel> arrNews ;
    private LayoutInflater lf;

    private ProgressDialog pd;
    private ListView mListView;
    private VerticalListViewAdapter mAdapter;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mListView = (ListView) view.findViewById(R.id.vertical_listview);
        mContext=view.getContext();
        //Button bt = (Button) view.findViewById(R.id.btn_news);
       // bt.setOnClickListener(new View.OnClickListener() {

           // @Override
           // public void onClick(View v) {
                arrNews = new ArrayList<NewsModel>();
                mAdapter = new VerticalListViewAdapter(mContext);


                mRequestQueue = Volley.newRequestQueue(view.getContext());
                String url = "http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json";
                pd = ProgressDialog.show(view.getContext(), "Please Wait...", "Please Wait...");

                JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        parseJSON(response);
                        mAdapter.notifyDataSetChanged();
                        pd.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, error.getMessage());
                    }
                });
                mRequestQueue.add(jr);
                mAdapter.setArrayNews(arrNews);
                mListView.setAdapter(mAdapter);
                mAdapter.setMode(Attributes.Mode.Single);


                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((SwipeLayout) (mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
                    }
                });

                mListView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.e("ListView", "OnTouch");
                        return false;
                    }
                });

                mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        Log.e("ListView", "onScrollStateChanged");
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });


                mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("ListView", "onItemSelected:" + position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.e("ListView", "onNothingSelected:");
                    }
                });
          //  }
        //});

    return view;

    }
    private void parseJSON(JSONObject json)
    {
        try{
            JSONObject value = json.getJSONObject("value");
            JSONArray items = value.getJSONArray("items");
            for(int i=0;i<items.length();i++){

                JSONObject item = items.getJSONObject(i);
                NewsModel nm = new NewsModel();
                nm.setTitle(item.optString("title"));
                nm.setDescription(item.optString("description"));
                nm.setLink(item.optString("link"));
                nm.setPubDate(item.optString("pubDate"));
                arrNews.add(nm);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
class NewsModel{
    private String title;
    private String link;
    private String description;
    private String pubDate;

    void setTitle(String title) {
        this.title = title;
    }

    void setLink(String link) {
        this.link = link;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    String getLink() {
        return link;
    }

    String getDescription() {
        return description;
    }

    String getPubDate() {
        return pubDate;
    }

    String getTitle() {

        return title;
    }
}