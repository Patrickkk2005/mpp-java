package eu.ase.threads;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadPlaygorund {
    static int numberOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }

    static void concurrentMorningRoutineUsingExecutorsWithName(){
        final ThreadFactory factory = Thread.ofVirtual().name("routine - ",0).factory();

        try(var executor = Executors.newThreadPerTaskExecutor(factory)){
            var bathTime = executor.submit(()->{
                System.out.printf("\n %s - Im going to take a bath ", Thread.currentThread().getName());
                try{
                    Thread.sleep((Duration.ofMillis(500)));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            });

            var boilingWaater = executor.submit(()->{
                System.out.printf("\n %s - Im going to boil some water ", Thread.currentThread().getName());
                try{
                    Thread.sleep((Duration.ofSeconds(1L)));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.printf("\n %s - Im done with the water ", Thread.currentThread().getName());

            });
            try{
                bathTime.get();
                boilingWaater.get();
            }catch(InterruptedException | ExecutionException e){
                e.printStackTrace();
            }

        }
    }
}
