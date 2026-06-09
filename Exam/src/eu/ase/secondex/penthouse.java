package eu.ase.secondex;

public class penthouse extends Apartament {
    public penthouse(){
        super();
    }
    public penthouse(String id, String city, String zone,
                  int area, int price_sqm, float discount) {
        super(id, "penthouse", city, zone, area, price_sqm, discount);
    }
}
