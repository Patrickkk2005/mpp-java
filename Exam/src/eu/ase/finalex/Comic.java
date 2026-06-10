package eu.ase.finalex;

public class Comic extends Book {
    public Comic(String id, String type, String title, String author, int pages, double price, float discount) {
        super(id, "comic", title, author, pages, price, discount);
    }

    @Override
    public double finalPrice() {
        return getPrice() * (1 - getDiscount());
    }
}