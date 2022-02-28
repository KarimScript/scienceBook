package com.example.sciencebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView image;
    TextView bookName, authorName, bookPrice, description;
    Button btn_order, btn_list;

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    String addtolistURL = "https://bookstorecsci410.000webhostapp.com/addToList.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        image = findViewById(R.id.detailImage);
        bookName = findViewById(R.id.detailName);
        authorName = findViewById(R.id.detailAuthor);
        bookPrice = findViewById(R.id.detailPrice);
        description = findViewById(R.id.detailDescription);
        btn_order = findViewById(R.id.btn_order);
        btn_list = findViewById(R.id.btn_list);
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("email");


        bookName.setText(intent.getStringExtra("title"));
        authorName.setText(intent.getStringExtra("author"));
        String price = intent.getStringExtra("price");
        bookPrice.setText(price + "");
        description.setText(intent.getStringExtra("description"));
        String Image = intent.getStringExtra("image");
        Glide.with(BookDetailsActivity.this).load(intent.getStringExtra("image")).into(image);


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(BookDetailsActivity.this, OrderActivity.class);
                in.putExtra("name", intent.getStringExtra("title"));
                in.putExtra("Price", price);
                in.putExtra("Author", intent.getStringExtra("author"));
                in.putExtra("Image", Image);
                in.putExtra("email",userEmail);
                startActivity(in);
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToList(userEmail,intent.getStringExtra("title"));
            }


        });
    }

    private void addToList(String email, String bookName) {

        mRequestQueue = Volley.newRequestQueue(BookDetailsActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, addtolistURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BookDetailsActivity.this, response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(BookDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("bookName", bookName);

                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }
}