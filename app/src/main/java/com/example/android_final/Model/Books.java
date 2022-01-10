package com.example.android_final.Model;

public class Books {
    private int id;
    private int bookImage;
    private String bookName;
    private String authorName;
    private int releaseYear;
    private int pageNumber;
    private String description;

    public Books(int bookImage, String bookName,String authorName, int releaseYear, int pageNumber,String description) {
        //this.id=id;
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.authorName=authorName;
        this.releaseYear = releaseYear;
        this.pageNumber = pageNumber;
        this.description=description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

