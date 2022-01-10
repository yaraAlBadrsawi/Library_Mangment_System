package com.example.android_final.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android_final.Adapter.BookAdapter;
import com.example.android_final.Adapter.CategoryAdapter;
import com.example.android_final.Controller.DataBase;
import com.example.android_final.Model.Books;
import com.example.android_final.Model.Category;
import com.example.android_final.R;
import com.example.android_final.databinding.ActivityLibraryBinding;
import com.example.android_final.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class Library extends AppCompatActivity {

    ActivityLibraryBinding binding;

    private List<Category> categoriesList;
    private List<Books> booksList;
    private DataBase dataBase;
    private Spinner categoriesName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.binding= ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        //-------------------------- initialize Objects -------------------------------------
        this.dataBase=new DataBase(this);
        this.categoriesList=new ArrayList<>();
        this.booksList=new ArrayList<>();
        //-----------------------------Add categories to library------------------------------

        categoriesList=dataBase.getAllCategories(); // set all categories we created in list to add them to the recycler
        this.binding.categoryRecycler.setLayoutManager(new LinearLayoutManager(Library.this,
                LinearLayoutManager.HORIZONTAL,false));
        this.binding.categoryRecycler.setAdapter(new CategoryAdapter(this,categoriesList));

        //-----------------------------Add Books to library-----------------------------------------

        this.binding.booksRecycler.setAdapter(new BookAdapter(this,booksList));









    }
}