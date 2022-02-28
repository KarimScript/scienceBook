package com.example.sciencebook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class OrderActivity extends AppCompatActivity {
    ImageView image;
    TextView Price;
    EditText username,phone,address,zipcode;
    Button btn_confirm;

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    String addOrderURL="https://bookstorecsci410.000webhostapp.com/addOrder.php";
    boolean added;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        image=findViewById(R.id.Order_Image);
        Price=findViewById(R.id.Order_book_Price);
        username=findViewById(R.id.username);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        zipcode=findViewById(R.id.zipcode);
        btn_confirm=findViewById(R.id.btn_confirm);
        Intent intent =getIntent();
        Glide.with(OrderActivity.this).load(intent.getStringExtra("Image")).into(image);
        String price=intent.getStringExtra("Price");
        Price.setText(price+"");
        String email=intent.getStringExtra("email");
        int id=intent.getIntExtra("id",0);
        String bookName=intent.getStringExtra("name");




    btn_confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Validation();
        if(!Validation()){
            Toast.makeText(OrderActivity.this, "Please enter All information", Toast.LENGTH_SHORT).show();

        }
        else{
            addOrder(email,bookName,username.getText().toString(),price,phone.getText().toString(),zipcode.getText().toString(),address.getText().toString());


        }




        }
    });

    }
    private void addOrder(String Email,String bookname,String uname,String Price,String Phone,String ZipCode,String Address){

        mRequestQueue = Volley.newRequestQueue(OrderActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, addOrderURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(OrderActivity.this, response, Toast.LENGTH_LONG).show();
                finish();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OrderActivity.this, error.toString(), Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", Email);
                params.put("bookName",bookname);
                params.put("username", uname);
                params.put("amount", Price);
                params.put("phone", Phone);
                params.put("zipcode", ZipCode);
                params.put("address", Address);

                return params;
            }
        };

        mRequestQueue.add(mStringRequest);

    }


    private boolean Validation(){
          boolean isValidate;
        if(username.getText().toString().equals("") || phone.getText().toString().equals("")
                ||address.getText().toString().equals("") || zipcode.getText().toString().equals("")){

        isValidate= false;
        }

        else{

            isValidate= true;

        }
        return isValidate;

    }
}