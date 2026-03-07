package eu.ase.poly;

public class Auto extends Vehicle{
    private int doorsNo;

    public Auto(){
        super();
    }

    public Auto(int weight, int doorsNo) throws Exception{
        super(weight);
        if(doorsNo<0){
            throw new Exception("Doors number can not be less than 0");
        }
        this.doorsNo=doorsNo;
    }

    public void setDoorsNo(int doorsNo){
        if(doorsNo<0){
            throw new RuntimeException();
        }
        this.doorsNo=doorsNo;
    }

    public int getDoorsNo(){
        return this.doorsNo;
    }

    @Override
    public String display(){
        return new String("Auto - weight: "+this.getWeight()+" , doors: "+this.doorsNo);
    }
}
