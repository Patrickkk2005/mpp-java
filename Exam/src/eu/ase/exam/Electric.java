package eu.ase.exam;

public class Electric extends Car {
    public Electric(String id, String brand, String city, int year, double price_year, float discount) {
        super(id, "electric", brand, city, year, price_year, discount);
    }

    @Override
    public boolean compareTo() {
        return false;
    }
}

