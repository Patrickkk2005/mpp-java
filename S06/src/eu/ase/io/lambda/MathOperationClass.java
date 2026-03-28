package eu.ase.io.lambda;

public class MathOperationClass {
    public int operate(int a,int b, MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }
}
