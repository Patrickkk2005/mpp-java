package eu.ase.exam;

import java.io.Serializable;
import java.util.Objects;

public abstract class Car implements Rentable, Serializable, Cloneable, Comparable<Car> {
    private String id;
    private String type;
    private String brand;
    private String city;
    private int year;
    private double price_day;
    private float discount;

    public Car(String id, String type, String brand, String city, int year, double price_year, float discount) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.city = city;
        this.year = year;
        this.price_day=price_year;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public double getPrice_day() {
        return price_day;
    }

    public float getDiscount() {
        return discount;
    }

    @Override
    public double rentalPrice() {
        return price_day * (1 - discount);
    }

    @Override
    public String toString() {
        return brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price_day, brand, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.brand, ((Car) o).brand) && this.discount == ((Car) o).discount;
    }

    @Override
    public int compareTo(Car other) {
        return Double.compare(this.price_day, other.getPrice_day());
    }

}
