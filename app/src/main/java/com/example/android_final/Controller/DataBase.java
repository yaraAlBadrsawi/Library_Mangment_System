package com.example.android_final.Controller;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

import com.example.android_final.Model.Books;
import com.example.android_final.Model.Category;
import com.example.android_final.Utils.Utils;

import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.List;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //------------------------------CREATE CATEGORY TABLE -------------------------------------------------
        sqLiteDatabase.execSQL("CREATE TABLE " + Utils.C_TABLE_NAME + " (" +
                Utils.C_KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," + Utils.C_KEY_CATEGORY_NAME +
                " TEXT  ); ");

        //-----------------------------CREATE BOOKS TABLE -------------------------------------------------------
        sqLiteDatabase.execSQL("CREATE TABLE " + Utils.B_TABLE_NAME + " ( " + Utils.B_ID + " INTEGER PRIMARY KEY ,"
                + Utils.B_BOOK_NAME + " TEXT NOT NULL , " + Utils.B_AUTHOR_NAME + " TEXT NOT NULL , " +
                Utils.B_BOOK_IMAGE + " INTEGER NOT NULL );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.C_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.B_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //--------------------------------- insert category-----------------------------------------------

    public boolean insertCategory(Category category) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.C_KEY_CATEGORY_NAME, category.getCategoryName());

        long res = sqLiteDatabase.insert(Utils.C_TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return res >0;

              }
    //------------------------------------ update category-------------------------------------------------
    public boolean updateCategory(Category category) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.C_KEY_CATEGORY_NAME, category.getCategoryName());

        String[] arg = {String.valueOf(category.getId())};

        return sqLiteDatabase.update(Utils.C_TABLE_NAME, contentValues, Utils.C_KEY_ID + " = ?", arg) != -1;
    }

    //------------------------------------- get Category -------------------------------------------------------
    @SuppressLint("Range")
    public Category getCategory(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(Utils.C_TABLE_NAME, null,
                "id = ?", new String[] {String.valueOf(Utils.C_KEY_ID)},
                null, null, null );

        Category category = null;
        if (cursor!= null && cursor.moveToFirst()){

            int id=cursor.getInt(cursor.getColumnIndex(Utils.C_KEY_ID));
            String categoryName=cursor.getString(cursor.getColumnIndex(Utils.C_KEY_CATEGORY_NAME));

            category= new Category(categoryName);
            category.setId(id);
        }
        cursor.close();
        sqLiteDatabase.close();
        return category;

    }
    //----------------------------------- Show All categories ---------------------------------------------------------------
    //------------------------------------ get all categories ---------------------------------------------------------------
    @SuppressLint("Range")
    public List<Category> getAllCategories() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Utils.C_TABLE_NAME, null);

        List<Category> categoryList = new ArrayList<>();

        if(cursor!=null&&cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Utils.C_KEY_CATEGORY_NAME));
                String categoryName = cursor.getString(cursor.getColumnIndex(Utils.C_KEY_CATEGORY_NAME));
                categoryList.add(new Category(categoryName));

            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();
        return categoryList;

    }
    //----------------------------------- delete category -------------------------------------------
    public boolean deleteCategory(int categoryID) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String[] arg = new String[]{String.valueOf(categoryID)};
        return sqLiteDatabase.delete(Utils.C_TABLE_NAME, Utils.C_KEY_ID + " = ? ",
                null) != -1;
    }
    //------------------------------------------ count categories ---------------------------------------------------

        public long getCategoriesCount(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,Utils.C_TABLE_NAME);

    }
    //------------------------------------<<<< END category table method >>>>------------------------------------------------

    //*****************************************<< START Book table method >>****************************************
    //-------------------------------------------- insert book-----------------------------------------------------
    public boolean insertBook(Books books) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); // مؤشر على الداتا بيز
        ContentValues contentValues = new ContentValues(); // وضع العناصر داخل الكلاس contentValues
        contentValues.put(Utils.B_BOOK_IMAGE, books.getBookImage()); //add book image
        contentValues.put(Utils.B_BOOK_NAME, books.getBookName()); // add book name
        contentValues.put(Utils.B_AUTHOR_NAME, books.getAuthorName()); // add book author name
        contentValues.put(Utils.B_BOOK_DESCRIPTION, books.getDescription()); // add description of book
        long result = sqLiteDatabase.insert(Utils.B_TABLE_NAME, null, contentValues);
        return result > 0;
    }

    //------------------------------------------- update book-------------------------------------------------
    public boolean updateBook(Books books) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.B_BOOK_IMAGE, books.getBookImage());
        contentValues.put(Utils.B_BOOK_NAME, books.getBookName());
        contentValues.put(Utils.B_AUTHOR_NAME, books.getAuthorName());
        contentValues.put(Utils.B_BOOK_DESCRIPTION, books.getDescription());
        long result = sqLiteDatabase.update(Utils.B_TABLE_NAME, contentValues, Utils.B_ID + " =? "
                , new String[]{String.valueOf(books.getId())});
        return result > 0;
    }

    //--------------------------------------------- get Book ----------------------------------------------------

    @SuppressLint("Range")
    public Books getBook(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(Utils.B_TABLE_NAME, null,
                "id = ?", new String[] {String.valueOf(Utils.B_ID)}, null,
                null, null );
        Books book = null;
        if (cursor!= null && cursor.moveToFirst()){

            int id=cursor.getInt(cursor.getColumnIndex(Utils.B_ID)); //get id
            int bookImage=cursor.getInt(cursor.getColumnIndex(Utils.B_BOOK_IMAGE));
            String bookName=cursor.getString(cursor.getColumnIndex(Utils.B_BOOK_NAME)); //get categoryName
            String authorName=cursor.getString(cursor.getColumnIndex(Utils.B_AUTHOR_NAME)); // get author name
            int releaseYear=cursor.getInt(cursor.getColumnIndex(Utils.B_RELEASE_YEAR));// get releaseYear
            int pageNumber=cursor.getInt(cursor.getColumnIndex(Utils.B_PAGE_NUMBER));
            String description=cursor.getString(cursor.getColumnIndex(Utils.B_BOOK_DESCRIPTION));
            book= new Books(bookImage,bookName,authorName,releaseYear,pageNumber,description);
        }
        cursor.close();
        sqLiteDatabase.close();
        return book;

    }

    ///------------------------------------------- show all Books -----------------------------------
    @SuppressLint("Range")
    public List<Books> getAllBooks() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Books> booksList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(Utils.B_TABLE_NAME, null, null, null, null, null, null);


        if(cursor!=null&&cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getColumnIndex(Utils.B_ID)); //get id
                int bookImage=cursor.getInt(cursor.getColumnIndex(Utils.B_BOOK_IMAGE));
                String bookName = cursor.getString(cursor.getColumnIndex(Utils.B_BOOK_NAME)); //get bookName
                String authorName=cursor.getString(cursor.getColumnIndex(Utils.B_AUTHOR_NAME));//get authorName
                int releaseYear=cursor.getInt(cursor.getColumnIndex(Utils.B_RELEASE_YEAR));// get releaseYear
                int pageNumber=cursor.getInt(cursor.getColumnIndex(Utils.B_PAGE_NUMBER));
                String description=cursor.getString(cursor.getColumnIndex(Utils.B_BOOK_DESCRIPTION));
                booksList.add(new Books(bookImage,bookName,authorName,releaseYear,pageNumber,description)); // add all data (columns) in book table into List
            } while (cursor.moveToNext());
            cursor.close();
        }
        sqLiteDatabase.close();
        return booksList;
    }

    //----------------------------------- delete book -------------------------------------------
    public boolean deleteBook(int bookID) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] arg = new String[]{String.valueOf(bookID)};
        return sqLiteDatabase.delete(Utils.B_TABLE_NAME, Utils.C_KEY_ID + " = ? ",
                null) != -1;
    }
    // -------------------------------------------- count of books in category-----------------------------------------

     public long getBooksCount(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,Utils.B_TABLE_NAME);
    }


    //---------------------------------------Search book-----------------------------------------

    public List<Books> SearchAllBooks(String book)
    {
        List<Books> booksList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*Cursor cursor = sqLiteDatabase.query();

        if (cursor!= null && cursor.moveToFirst()){
            do{


            }
            while (cursor.moveToNext());
        }*/
        return booksList;
    }

//--------------------------------------------------- END Books table method --------------------------------------
///// -----------*************************************<<<< END DATABASE CLASS >>>>***********************-----------------------------



}
