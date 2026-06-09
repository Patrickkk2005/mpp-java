package eu.ase.secondex;

public class Studio extends Apartament{
    public Studio() {
        super();
    }
    public Studio(String id, String city, String zone,
                     int area, int price_sqm, float discount) {
        super(id, "studio", city, zone, area, price_sqm, discount);
    }
}
