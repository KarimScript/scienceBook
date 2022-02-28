package com.example.sciencebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private Context context;
    private ArrayList<Order> orderList;


    public OrderAdapter(Context context , ArrayList<Order> orderList){
        this.context = context;
        this.orderList=orderList;

    }
    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderview , parent , false);
        return new OrderHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {

        Order order=orderList.get(position);
        holder.orderid.setText(order.getId().toString());
        holder.amount.setText(order.getAmount().toString());
        holder.bookname.setText(order.getBookname().toString());
        holder.username.setText(order.getUsername().toString());
        holder.email.setText(order.getEmail().toString());
        holder.phone.setText(order.getPhone().toString());
        holder.address.setText(order.getAddress().toString());
        holder.zipcode.setText(order.getZipcode().toString());


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder{

        TextView orderid,amount,bookname,username,email,phone,address,zipcode;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            orderid=itemView.findViewById(R.id.Orderid);
            amount=itemView.findViewById(R.id.Amount);
            bookname=itemView.findViewById(R.id.bookName);
            username=itemView.findViewById(R.id.Uname);
            email=itemView.findViewById(R.id.userEmail);
            phone=itemView.findViewById(R.id.OrderPhone);
            address=itemView.findViewById(R.id.OrderAddress);
            zipcode=itemView.findViewById(R.id.OrderZipcode);

        }
    }
}
