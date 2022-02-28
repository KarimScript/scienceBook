package com.example.sciencebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.bookHolder> {

    private Context context;
    private ArrayList<Book> BookList;
    String email;

    public bookAdapter(Context context , ArrayList<Book> books,String email){
        this.context = context;
        this.BookList = books;
        this.email=email;
    }
    @NonNull
    @Override
    public bookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card_view , parent , false);
        return new bookHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull bookHolder holder, int position) {

        Book book = BookList.get(position);
        holder.author.setText(book.getAuthor().toString());
        holder.title.setText(book.getName().toString());
        holder.price.setText(book.getPrice()+"$");
        Glide.with(context).load(book.getImage()).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , BookDetailsActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("title",book.getName().toString());
                intent.putExtra("author",book.getAuthor().toString());
                intent.putExtra("price",book.getPrice()+"$");
                intent.putExtra("image",book.getImage().toString());
                intent.putExtra("description",book.getDescription().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

    public static class bookHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title , author , price;
        CardView cardView;

        public bookHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            price = itemView.findViewById(R.id.price);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}