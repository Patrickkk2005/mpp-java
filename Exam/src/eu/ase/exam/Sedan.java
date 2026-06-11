package eu.ase.exam;

public class Sedan extends Car {
    public Sedan(String id, String brand, String city, int year, double price_year, float discount) {
        super(id, "sedan", brand, city, year, price_year, discount);
    }

    @Override
    public boolean compareTo() {
        return false;
    }
}
