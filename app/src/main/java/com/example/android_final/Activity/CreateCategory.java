package com.example.android_final.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android_final.Controller.DataBase;
import com.example.android_final.Model.Category;
import com.example.android_final.databinding.ActivityCreateCategoryBinding;

public class CreateCategory extends AppCompatActivity {
        ActivityCreateCategoryBinding binding;
        DataBase dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding=ActivityCreateCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!binding.categoryName.getText().toString().equals("")) {
                    String str = binding.categoryName.getText().toString();
                    dataBaseHandler=new DataBase(getApplicationContext());

               boolean res=dataBaseHandler.insertCategory(new Category(str));
               if(res){
                   Toast.makeText(getApplicationContext(), "Category added done ", Toast.LENGTH_LONG).show();
               }
               else {
                   Toast.makeText(getApplicationContext(), "Category added faild", Toast.LENGTH_LONG).show();

               }

               }
            }
        });


    }
}