package eu.ase.finalex;

import java.io.Serializable;

public abstract class Book implements Evaluabil, Serializable {
    private String id;
    private String type;
    private String title;
    private String author;
    private int pages;
    private double price;
    private float discount;
    private static final long serialVersionUID = 1L;

    public Book(String id, String type, String title, String author, int pages, double price, float discount) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return title;
    }
}
