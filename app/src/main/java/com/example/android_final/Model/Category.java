package com.example.android_final.Model;

import java.io.Serializable;

public class Category implements Serializable {
        private int id;
        private String categoryName;
        private int num;

        public Category(String categoryName){
            this.categoryName=categoryName;

        }
        /*
        public Category(int id ,String categoryName,int num) {
            this.id=id;
            this.categoryName = categoryName;
            this.num=num;
        }*/


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

    }


