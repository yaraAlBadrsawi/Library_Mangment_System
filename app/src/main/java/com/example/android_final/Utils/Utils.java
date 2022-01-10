package com.example.android_final.Utils;

public class Utils {
    //------------------------------- DataBase static Info -----------------------------------------
    public static final int DATABASE_VERSION=9;
    public static final String DATABASE_NAME="libraryManagementSystemDB.db";


    // ---------------------------------- Books Table Info --------------------------------------
    public static final String B_TABLE_NAME="Books";
    public static final String B_ID="id";
    public static final String B_BOOK_NAME="bookName";
    public static final String B_BOOK_IMAGE="bookImage";
    public static final String B_AUTHOR_NAME="authorName";
    public static final String B_RELEASE_YEAR="releaseYear";
    public static final String B_PAGE_NUMBER="pageNumber";
    public static final String B_BOOK_DESCRIPTION="description";

    // ---------------------------------------- Category Table info ---------------------------------
    public static final String  C_TABLE_NAME="CATEGORY";
    public static final String C_KEY_ID="id";
    public static final String C_KEY_CATEGORY_NAME="categoryName";
    public static final String C_CATEGORY_NUMBER="categoryNumber";

    // END


}
