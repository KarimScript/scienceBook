package com.example.sciencebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText name,email,password;
    Button register;
    ProgressBar prog2;
    TextView login;

    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    String registerURL="https://bookstorecsci410.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.txtName);
        email=findViewById(R.id.txtEmail);
        password=findViewById(R.id.txtPassword);
        register=findViewById(R.id.btn_register);
        prog2=findViewById(R.id.prog2);
        prog2.setVisibility(View.INVISIBLE);
        login=findViewById(R.id.txtLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
                if(!Validation()){
                    Toast.makeText(RegisterActivity.this, "Please enter All information", Toast.LENGTH_SHORT).show();
                }
                else{
                    SignIn(name.getText().toString(),email.getText().toString(),password.getText().toString());

                }

            }
        });

    }
    private void SignIn( String name, String email,  String password){

        mRequestQueue = Volley.newRequestQueue(RegisterActivity.this);
        prog2.setVisibility(View.VISIBLE);

        mStringRequest = new StringRequest(Request.Method.POST, registerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
                prog2.setVisibility(View.INVISIBLE);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                prog2.setVisibility(View.INVISIBLE);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }

    private boolean Validation(){
        boolean isValidate;
        if(email.getText().toString().equals("") || password.getText().toString().equals("") || name.getText().toString().equals("")) {

            isValidate= false;
        }

        else{

            isValidate= true;

        }
        return isValidate;

    }

}