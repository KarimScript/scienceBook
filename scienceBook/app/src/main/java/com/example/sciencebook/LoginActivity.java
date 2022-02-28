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

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button btn_login;
    TextView register;
    ProgressBar prog;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
     String loginURL="https://bookstorecsci410.000webhostapp.com/getuser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        register=findViewById(R.id.txtRegister);
        prog=findViewById(R.id.prog);
        prog.setVisibility(View.INVISIBLE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
                if(!Validation()){
                    Toast.makeText(LoginActivity.this, "Please enter All information", Toast.LENGTH_SHORT).show();
                }
                else{
                   LogIn(email.getText().toString(),password.getText().toString());

                }
            }
        });


    }
    private void LogIn( String email, String password) {

        prog.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(LoginActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST,
                loginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("1")) {

                        prog.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                        // Finish
                        finish();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }
                    if (success.equals("0")) {

                        prog.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    prog.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                prog.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,
                        error.toString(),
                        Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }



    private boolean Validation(){
        boolean isValidate;
        if(email.getText().toString().equals("") || password.getText().toString().equals("")) {

            isValidate= false;
        }

        else{

            isValidate= true;

        }
        return isValidate;

    }
}