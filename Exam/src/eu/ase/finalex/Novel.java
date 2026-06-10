package eu.ase.finalex;

public class Novel extends Book {
    public Novel(String id, String type, String title, String author, int pages, double price, float discount) {
        super(id, "novel", title, author, pages, price, discount);
    }

    @Override
    public double finalPrice() {
        return getPrice() * (1 - getDiscount());
    }
}
