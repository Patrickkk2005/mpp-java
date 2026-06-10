package eu.ase.finalex;

public class Textbook extends Book {
    public Textbook(String id, String type, String title, String author, int pages, double price, float discount) {
        super(id, "textbook", title, author, pages, price, discount);
    }

    @Override
    public double finalPrice() {
        return getPrice() * (1 - getDiscount());
    }
}
