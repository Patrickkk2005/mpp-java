package eu.ase.poly;

import java.util.concurrent.ExecutionException;

public class ProgMainVehicle {
    public static void main(String[] args) {
        Vehicle v=null;
        Auto a=null;
        try{
            a=new Auto(1500,5);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        System.out.println(a.display());
        Plane p=new Plane(1500,12,2);
        System.out.println(p.display());

        Movement m0=null;
        try{
            m0=new Auto(2900,4);
        } catch (Exception e){
            throw new RuntimeException();
        }

        //m0.display();
        m0.startEngine();

    }

}
