package com.example.android_final.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_final.Model.Books;
import com.example.android_final.R;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<Books> booksList;
    Context context;
    public BookAdapter(Context context, List<Books> booksList) {
    this.context=context;
    this.booksList=booksList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(BookAdapter.BookViewHolder holder, int position) {

        holder.bookImage.setImageResource(booksList.get(position).getBookImage());
        holder.bookName.setText(booksList.get(position).getBookName());
        holder.authorName.setText(booksList.get(position).getAuthorName());

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookName;
        TextView authorName;
        ImageView bookImage;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
         bookName=itemView.findViewById(R.id.bookname);
         authorName=itemView.findViewById(R.id.authorname);
         bookImage=itemView.findViewById(R.id.bookImg);
        }
    }
}
