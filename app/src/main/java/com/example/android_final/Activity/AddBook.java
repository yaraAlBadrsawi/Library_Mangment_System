package com.example.android_final.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_final.Controller.DataBase;
import com.example.android_final.Model.Books;
import com.example.android_final.Model.Category;
import com.example.android_final.R;
import com.example.android_final.databinding.ActivityAddBookBinding;

import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity {

    private boolean storagePermission = false;
    private ActivityAddBookBinding binding;
    private final int PERMISSION_REQUEST_CODE = 1203;
    MainActivity activity;
    private boolean isAllFieldsChecked = false;
    private List<Category> categoriesList;
    List <String>categoriesName;
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        //---------- to make window full screen ----------
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setCategoriesInSpinner();
        dataBase=new DataBase(this);

        // Validation
        binding.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    Toast.makeText(AddBook.this, "Books " + binding.bookName.getText().toString() + " is added ",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddBook.this, MainActivity.class));
                    dataBase.insertBook(new Books(R.drawable.bookwallepwe2,binding.bookName.getText().toString(),
                            binding.authorName.getText().toString(),
                            Integer.valueOf(binding.pageNumber.getText().toString()),
                            Integer.valueOf(binding.releaseYear.getText().toString()),
                            binding.description.getText().toString()));
                }
            }
        });


        int permissionStatus= ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionStatus== PackageManager.PERMISSION_DENIED){
            String []permission=new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(AddBook.this,permission,PERMISSION_REQUEST_CODE);
        }else
        {
            storagePermission=true;
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                storagePermission=false;
            }else
                storagePermission=true;
        }
    }

    public boolean CheckAllFields () {
        if (binding.bookName.length() == 0) {
            binding.bookName.setError("This filed is required ");
            return false;
        }
        if (binding.authorName.length() == 0) {
            binding.authorName.setError("This filed is required");
            return false;
        }
        if (binding.releaseYear.length() == 0) {
            binding.authorName.setError("This filed is required");
            return false;
        }
        if (binding.pageNumber.length() == 0) {
            binding.authorName.setError("This filed is required");
            return false;
        }
        return true;
    }

    // method to show categories we created in spinner to add books to it
    void setCategoriesInSpinner(){
        this.categoriesList=new ArrayList<>();
        this.dataBase=new DataBase(getApplicationContext()); // cursor to database
        this.categoriesList=this.dataBase.getAllCategories();
        this.categoriesName=new ArrayList<>();
        for(Category category:categoriesList){
            categoriesName.add(category.getCategoryName());
        }
        binding.categoriesName.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,this.categoriesName));

    }


}
