package eu.ase.poly;

public class Plane extends Vehicle{
    private float capacity;
    private int enginesNo;

    public Plane(int weight, float capacity, int enginesNo){
        super(weight);
        this.capacity=capacity;
        this.enginesNo=enginesNo;
    }

    @Override
    public String display(){
        return new String("Plane - weight: "+this.getWeight()+", capacity: "+this.capacity+" , engines: "+this.enginesNo);
    }
}
