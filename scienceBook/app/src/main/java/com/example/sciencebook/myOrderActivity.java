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

public class myOrderActivity extends AppCompatActivity {
    RecyclerView orderRV;
    OrderAdapter adapter;
    ArrayList<Order> orders;
    ProgressBar orderProg;
    String myOrderURL="https://bookstorecsci410.000webhostapp.com/getOrder.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        orderProg=findViewById(R.id.orderProg);
        orderRV=findViewById(R.id.orderRV);
        orderRV.setLayoutManager(new LinearLayoutManager(myOrderActivity.this));
        orders=new ArrayList<Order>();
        adapter=new OrderAdapter(myOrderActivity.this,orders);
        orderRV.setAdapter(adapter);
        Intent in=getIntent();
        String Email=in.getStringExtra("email").toString();


        orderProg.setVisibility(View.VISIBLE);
        StringRequest mStringRequest;
        RequestQueue mRequestQueue;

        mRequestQueue = Volley.newRequestQueue(myOrderActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, myOrderURL, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                orderProg.setVisibility(View.INVISIBLE);

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONArray jsonArray=new JSONArray(response);

                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String amount = object.getString("amount");
                        String phone = object.getString("phone");
                        String username = object.getString("username");
                        String address = object.getString("useraddress");
                        String zipcode = object.getString("zipCode");
                        String bookname = object.getString("bookname");
                        String email = object.getString("user_email");
                        Order order = new Order(id,amount,phone,username,address,zipcode,bookname,email);
                        orders.add(order);


                    } catch (JSONException e) {
                        orderProg.setVisibility(View.INVISIBLE);
                    }
                }
                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                orderProg.setVisibility(View.INVISIBLE);




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