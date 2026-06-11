package eu.ase.exam;

public class SUV extends Car {
    public SUV(String id, String brand, String city, int year, double price_year, float discount) {
        super(id, "suv", brand, city, year, price_year, discount);
    }

    @Override
    public boolean compareTo() {
        return false;
    }
}

