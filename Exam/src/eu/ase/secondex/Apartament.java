package eu.ase.secondex;

public abstract class Apartament implements Evaluabil {
    private String id;
    private String type;
    private String city;
    private String zone;
    private int area;
    private int price_sqm;
    private float discount;

    public Apartament(String id, String type, String city, String zone, int area, int price_sqm, float discount) {
        this.id = id;
        this.type = type;
        this.city = city;
        this.zone = zone;
        this.area = area;
        this.discount = discount;
        this.price_sqm = price_sqm;
    }

    public Apartament() {

    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCity() {
        return city;
    }

    public String getZone() {
        return zone;
    }

    public int getArea() {
        return area;
    }

    public int getPrice_sqm() {
        return price_sqm;
    }

    public float getDiscount() {
        return discount;
    }

    @Override
    public double pretFinal() {
        return area * price_sqm * (1 - discount);
    }
}
