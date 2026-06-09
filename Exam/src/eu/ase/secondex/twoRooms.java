package eu.ase.secondex;

public class twoRooms extends Apartament {
    public twoRooms() {
        super();
    }
    public twoRooms(String id, String city, String zone,
                  int area, int price_sqm, float discount) {
        super(id, "2rooms", city, zone, area, price_sqm, discount);
    }
}
