package com.example.demo8;

public class Artwork {
    private String title;
    private String description;
    private String artist;
    private String price;
    private String imageUrl;
    private int stockAvailable;

    public Artwork(String title, String description, String artist, String price, String imageUrl, int stockAvailable) {
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockAvailable = stockAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public String getTitle() {
        return title;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public String getDescription() {
        return description;
    }

    public String getArtist() {
        return artist;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}