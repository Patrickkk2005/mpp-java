package eu.ase.firstex;

public class SmartPhone extends Phone {
    private int batteryDuration;

    public SmartPhone() {
    }

    public void setBatteryDuration(int batteryDuration) throws Exception {
        if (batteryDuration < 0) {
            throw new Exception("Battery must be above 0");
        }
        this.batteryDuration = batteryDuration;
    }

    public int getBatteryDuration() {
        return batteryDuration;
    }

    @Override
    public String infoDevice() {
        return String.valueOf(batteryDuration);
    }
}
