package com.kenzie.iteratorsanddynamodb.wishlist;

public class WishListItem {

    private String title;
    private String category;
    private Double price;

    /**
     * WishListItem constructor that initializes a new instance with title, category, and price.
     * @param title the title of the item
     * @param category the category the item belongs to
     * @param price the price of the item
     */
    public WishListItem(String title, String category, Double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
