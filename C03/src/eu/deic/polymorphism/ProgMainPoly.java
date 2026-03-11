package eu.deic.polymorphism;

// Interface
interface Animal {
    void makeSound();
    double maxSpeed();
}

// Base class
class Feline extends Object implements Animal {
    private float weight;

    public Feline(){}

    public Feline(float weight){
        this.weight=weight;
    }

    @Override
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
    @Override
    public double maxSpeed() {
        System.out.println("Animal has max speed");
        return 0.0;
    }

    public void display() {
        System.out.println("Feline::display() "+this);
    }
}

// Derived class 1
class Cat extends Feline {
    public Cat(){
        super();
    }

    public Cat(float weight){
        super(weight);
    }

    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
    @Override
    public double maxSpeed() {
        System.out.println("Cat has max speed 50km/h");
        return 50.0;
    }
    @Override
    public void display() {
        System.out.println("Cat::display() "+this);
    }
}

// Derived class 2
class Tiger extends Feline {
    @Override
    public void makeSound() {
        System.out.println("Tiger roars");
    }
    @Override
    public double maxSpeed() {
        System.out.println("Tiger has max speed 65km/h");
        return 65.0;
    }
    @Override
    public void display() {
        System.out.println("Tiger::display() "+this);
    }
}

public class ProgMainPoly {
    public static void main(String[] args) {
        // Using "pure" polymorphism/runtime polymorphism
        Animal objA=null;
        Animal cat = new Cat();
        Animal tiger = new Tiger();
        //Tiger objT2= new Tiger();
        //objT2.display(2);
        Cat objCat=new Cat(500);
        objCat.display();


        objA=cat;
        objA.makeSound();
        objA=tiger;
        objA.makeSound();

        cat.makeSound(); // Output: Cat meows
        tiger.makeSound(); // Output: Tiger roars

        // class cast exception:
        Animal a0;
        a0 = tiger; // Tiger -> Animal - up-cast (implicit)
        // 270 lines of code
        try {
            cat = (Cat) a0; // Animal -> Cat - down-cast (explicit)
        } catch(ClassCastException cce) {
            cce.printStackTrace();
        }
    }
}