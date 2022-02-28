package com.example.sciencebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button showBook,myOrder,myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBook=findViewById(R.id.btn_show_book);
        myOrder=findViewById(R.id.btn_myOrder);
        myList=findViewById(R.id.btn_myList);
        Intent i=getIntent();
        String email=i.getStringExtra("email");



        showBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AllBooksActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
       myList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent next=new Intent(MainActivity.this,myListActivity.class);
               next.putExtra("email",email);
               startActivity(next);
           }
       });
       myOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent next=new Intent(MainActivity.this,myOrderActivity.class);
               next.putExtra("email",email);
               startActivity(next);
           }
       });







    }

}
