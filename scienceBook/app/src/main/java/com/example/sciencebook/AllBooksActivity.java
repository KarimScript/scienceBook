package com.example.sciencebook;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AllBooksActivity extends AppCompatActivity {

    RecyclerView RV;
    ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books);
        Intent i=getIntent();
        String email=i.getStringExtra("email");
        RV=findViewById(R.id.bookRecView);
        RV.setLayoutManager(new LinearLayoutManager(AllBooksActivity.this));
        books=new ArrayList<Book>();
        bookAdapter adapter=new bookAdapter(AllBooksActivity.this,books,email);
        RV.setAdapter(adapter);





        RequestQueue queue= Volley.newRequestQueue(AllBooksActivity.this);

        final String AllBooksURL="https://bookstorecsci410.000webhostapp.com/getbooks.php";

        JsonArrayRequest request=new JsonArrayRequest(AllBooksURL, new Response.Listener<JSONArray>() {
              @SuppressLint("NotifyDataSetChanged")
              @Override
              public void onResponse(JSONArray response) {
                  for (int i = 0; i < response.length(); i++) {
                      try {
                          JSONObject object = response.getJSONObject(i);
                          String title = object.getString("title");
                          String author = object.getString("author");
                          String image = object.getString("image");
                          double price = object.getDouble("price");
                          String description=object.getString("description");
                          Book book = new Book(title,author,price,description,image);
                          books.add(book);


                      } catch (JSONException e) {
                          Toast.makeText(AllBooksActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                      }
                  }

                   adapter.notifyDataSetChanged();


              }
         }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(AllBooksActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
              }
          });
           queue.add(request);

    }





}

