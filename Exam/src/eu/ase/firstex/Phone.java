package eu.ase.firstex;

import java.io.Serializable;

public class Phone implements ElectronicDevices, Serializable, Cloneable {
    private float weight;
    private double diagonal;
    private String producer;
    private static final long serialVersionUID = 1L;

    public Phone() {
    }

    public float getWeight() {
        return weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public String getProducer() {
        return producer;
    }

    public void setWeight(float weight) throws Exception {
        if (weight <= 0) {
            throw new Exception("Weight must be greater than 0.");
        }
        this.weight = weight;
    }

    public void setDiagonal(double diagonal) throws Exception {
        if (diagonal <= 0) {
            throw new Exception("Diagonal must be greater than 0.");
        }
        this.diagonal = diagonal;
    }

    public void setProducer(String producer) throws Exception {
        if (producer == null || producer.length() <= 1) {
            throw new Exception("Producer must not be null and length must be greater than 1.");
        }
        this.producer = producer;
    }

    @Override
    public String infoDevice() {
        return producer;
    }

    @Override
    public Phone clone() throws CloneNotSupportedException {
        return (Phone) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Phone phone = (Phone) obj;
        if (weight != phone.weight) return false;
        if (diagonal != phone.diagonal) return false;
        if (producer != null ? !producer.equals(phone.producer) : phone.producer != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = Float.floatToIntBits(weight);
        result = 31 * result + (int) (Double.doubleToLongBits(diagonal) ^ (Double.doubleToLongBits(diagonal) >>> 32));
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        return result;
    }


}
