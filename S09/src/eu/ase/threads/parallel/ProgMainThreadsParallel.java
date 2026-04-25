package eu.ase.threads.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProgMainThreadsParallel {
    private static final int NTHREADS = 4;

    public static void main(String[] args) {
        int dimVect = 40_000_000;
        int[] v = new int[dimVect];
        Long sum = Long.valueOf(0);

        for (int i = 0; i < dimVect; i++) {
            v[i] = i + 1;
        }

        long startTime = 0;
        long stopTime = 0;
        int startIndex = 0;
        int stopIndex = 0;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < dimVect; i++) {
            sum += v[i];
        }
        stopTime = System.currentTimeMillis();
        System.out.println("1.seq time= " + (stopTime - startTime) + " sum= " + sum);

        Thread[] vectThreads = new Thread[NTHREADS];
        MyMultiThreadArray[] vectTasks = new MyMultiThreadArray[NTHREADS];
        Long[] vectSum = new Long[NTHREADS];

        startTime = System.currentTimeMillis();
        for (int i = 0; i < NTHREADS; i++) {
            startIndex = i * (dimVect / NTHREADS);
            stopIndex = (i + 1) * (dimVect / NTHREADS) - 1;
            vectTasks[i] = new MyMultiThreadArray(v, startIndex, stopIndex);
            vectThreads[i] = new Thread(vectTasks[i]);
        }

        for (int i = 0; i < NTHREADS; i++) {
            vectThreads[i].start();
        }

        for (int i = 0; i < NTHREADS; i++) {
            try {
                vectThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sum = 0L;
        for (int i = 0; i < NTHREADS; i++) {
            sum += vectTasks[i].getSum();
        }
        stopTime = System.currentTimeMillis();
        System.out.println("2.multi thread std time = " + (stopTime - startTime) + " sum= " + sum);

        startTime = System.currentTimeMillis();
        ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);
        MyMultiThreadArray[] workerTasks = new MyMultiThreadArray[NTHREADS];

        for (int i = 0; i < NTHREADS; i++) {
            startIndex = i * (dimVect / NTHREADS);
            stopIndex = (i + 1) * (dimVect / NTHREADS) - 1;
            vectSum[i] = 0L;
            workerTasks[i] = new MyMultiThreadArray(v, startIndex, stopIndex);
            execThreadPool.execute(workerTasks[i]);
        }

        try {
            execThreadPool.shutdown();
            execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sum = 0L;
        for (int i = 0; i < NTHREADS; i++) {
            vectSum[i] = workerTasks[i].getSum();
            sum += vectSum[i];
        }
        stopTime = System.currentTimeMillis();

        System.out.println("3. multi thread exec-service = " + (stopTime - startTime) + " sum= " + sum);

        startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < NTHREADS; i++) {
            startIndex = i * (dimVect / NTHREADS);
            stopIndex = (i + 1) * (dimVect / NTHREADS) - 1;
            Callable<Long> worker = new MyCallableArray(v, startIndex, stopIndex);
            Future<Long> submit = executor.submit(worker);
            futures.add(submit);
        }

        sum = 0L;
        for (Future<Long> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MIN_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stopTime = System.currentTimeMillis();
        System.out.println("4. array multi thread= " + (stopTime - startTime) + " sum= " + sum);

        sum = 0L;
        startTime = System.currentTimeMillis();
        sum = SumForkJoin.sumArrays(v);
        stopTime = System.currentTimeMillis();
        System.out.println("5. fork join array = " + (stopTime - startTime) + " sum= " + sum);

        sum = 0L;
        startTime = System.currentTimeMillis();
        MyMultiThreadArray[] vectVirtualThreads = new MyMultiThreadArray[NTHREADS];
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NTHREADS; i++) {
                startIndex = i * (dimVect / NTHREADS);
                stopIndex = (i + 1) * (dimVect / NTHREADS) - 1;
                vectVirtualThreads[i] = new MyMultiThreadArray(v, startIndex, stopIndex);
                executorService.execute(vectVirtualThreads[i]);
            }
        }
        for (int i = 0; i < NTHREADS; i++) {
            sum += vectVirtualThreads[i].getSum();
        }
        
        stopTime = System.currentTimeMillis();
        System.out.println("6. virtual threads executor = " + (stopTime - startTime) + " sum= " + sum);
    }
}
