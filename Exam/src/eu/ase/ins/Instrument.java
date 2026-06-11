package eu.ase.ins;

import java.io.Serializable;
import java.util.Objects;

public class Instrument implements Playable, Serializable,Cloneable {
    private float weight;
    private double price;
    private String brand;
    private static final long serialVersionUID = 1L;

    public Instrument(){

    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if(weight < 0){
            throw new IllegalArgumentException();
        }
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws Exception {
        if(brand.length()<1&&brand==null){
            throw new Exception("plm");
        }
        this.brand = brand;
    }

    @Override
    public String getInfo() {
        return brand;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "price: "+price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument instrument = (Instrument) o;
        if (Float.compare(instrument.weight, weight) != 0) return false;
        if (Double.compare(instrument.price, price) != 0) return false;
        return brand.equals(instrument.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, price, brand);
    }

}
