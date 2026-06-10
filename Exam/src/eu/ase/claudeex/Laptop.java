package eu.ase.claudeex;

import java.io.Serializable;
import java.util.Objects;

public class Laptop implements Printable, Serializable, Cloneable {
    private String brand;
    private double price;
    private float weight;
    private static final long serialVersionUID = 1L;

    public Laptop() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand != null && brand.length() > 1) {
            this.brand = brand;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }

    @Override
    public String getInfo() {
        return brand;
    }

    @Override
    public Laptop clone() throws CloneNotSupportedException {
        return (Laptop) super.clone();
    }

    @Override
    public String toString() {
        return "brand: " + brand + ", price: " + price + ", weight: " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(price, laptop.price) == 0 && Float.compare(weight, laptop.weight) == 0 && Objects.equals(brand, laptop.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price, weight);
    }
}
