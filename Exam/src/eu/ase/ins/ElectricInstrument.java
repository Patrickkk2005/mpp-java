package eu.ase.ins;

public class ElectricInstrument extends Instrument {
    private int wattage;

    public ElectricInstrument() {
        super();
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        if(wattage < 0) {
            throw new IllegalArgumentException("Wattage can't be negative");
        }
        this.wattage = wattage;
    }

    @Override
    public String getInfo() {
        return String.valueOf(wattage);
    }

}
