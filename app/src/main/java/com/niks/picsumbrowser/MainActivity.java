package com.niks.picsumbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemPicsum> picsumArrayList  = new ArrayList<>();
    private String url="https://picsum.photos/list";;
    RecyclerView recyclerView;
    RecyclerAdapterPicsum recyclerAdapterPicsum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyc);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapterPicsum = new RecyclerAdapterPicsum(MainActivity.this,picsumArrayList);
        recyclerView.setAdapter(recyclerAdapterPicsum);
        fill_array_list();

    }

    private void fill_array_list() {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                traversethrough(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONerror", "onErrorResponse: "+error);
                Toast.makeText(MainActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);

    }

    private void traversethrough(JSONArray response) {
        try {
            for(int i=0;i<response.length();++i){
                JSONObject object = (JSONObject) response.get(i);
                String author = object.getString("author");
                String id = object.getString("id");
                String imgurl = "https://picsum.photos/300/300?image="+id;
                ItemPicsum item = new ItemPicsum();
                item.setAuthor(author);
                item.setImgurl(imgurl);
                picsumArrayList.add(item);
            }
            recyclerAdapterPicsum.notifyDataSetChanged();
        }catch (Exception e){
            Log.d("error", "parseresponse: "+e);
        }

    }
}