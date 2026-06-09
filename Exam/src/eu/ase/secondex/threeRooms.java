package eu.ase.secondex;

public class threeRooms extends Apartament {
    public threeRooms() {
        super();
    }
    public threeRooms(String id, String city, String zone,
                  int area, int price_sqm, float discount) {
        super(id, "3rooms", city, zone, area, price_sqm, discount);
    }
}
