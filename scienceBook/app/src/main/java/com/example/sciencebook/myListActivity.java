package com.example.sciencebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myListActivity extends AppCompatActivity {
    RecyclerView listRV;
    ArrayList<Book> books;
    bookAdapter adapter;
    ProgressBar listProg;


    String mylistURL="https://bookstorecsci410.000webhostapp.com/myList.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Intent in=getIntent();
        String email=in.getStringExtra("email");
        listProg=findViewById(R.id.listProg);
        listProg.setVisibility(View.INVISIBLE);
        listRV=findViewById(R.id.listRV);
        listRV.setLayoutManager(new LinearLayoutManager(myListActivity.this));
        books=new ArrayList<Book>();
        adapter=new bookAdapter(myListActivity.this,books,email);
        listRV.setAdapter(adapter);
        getmyList(email);

    }
    private void getmyList( String Email){
        listProg.setVisibility(View.VISIBLE);
         StringRequest mStringRequest;
         RequestQueue mRequestQueue;

        mRequestQueue = Volley.newRequestQueue(myListActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, mylistURL, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                listProg.setVisibility(View.INVISIBLE);

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONArray jsonArray=new JSONArray(response);

                            JSONObject object = jsonArray.getJSONObject(i);

                            String title = object.getString("title");
                            String author = object.getString("author");
                            String image = object.getString("image");
                            double price = object.getDouble("price");
                            String description = object.getString("description");
                            Book book = new Book(title, author, price, description, image);
                            books.add(book);


                    } catch (JSONException e) {
                        listProg.setVisibility(View.INVISIBLE);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listProg.setVisibility(View.INVISIBLE);




            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email",Email);


                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }

}