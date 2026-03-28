package eu.ase.io.lambda;

public class MainLambda {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        MathOperation addition2 = (a, b)->a+b;
        System.out.println(addition.operation(10,5));

        MathOperation substraction = (int a ,int b)->a-b;
        System.out.println(substraction.operation(10,5));

        MathOperationClass tester = new MathOperationClass();
        System.out.println("10 + 5 = "+tester.operate(10,5,addition));

        MathOperation additionVerbose = (a,b) -> {
            int result = a+b;
            return result;
        };
        System.out.println(additionVerbose.operation(10,5));
    }
}
