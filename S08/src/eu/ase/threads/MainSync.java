package eu.ase.threads;

public class MainSync {
    public static void main(String[] args) {
        //ThreadsNonSync t1 = new ThreadsNonSync("t1");
        //ThreadsNonSync t2 = new ThreadsNonSync("t2");
        ThreadSync t1 = new ThreadSync("t1");
        ThreadSync t2 = new ThreadSync("t2");
        t1.start();
        t2.start();
        System.out.println("main program finished!");

    }
}
