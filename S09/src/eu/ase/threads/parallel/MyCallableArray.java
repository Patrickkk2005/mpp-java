package eu.ase.threads.parallel;

import java.util.concurrent.Callable;

public class MyCallableArray implements Callable<Long> {
    private int[] v;
    private int startIdx;
    private int stopIdx;
    private Long sum;

    public MyCallableArray(int[] v, int startIdx, int stopIdx) {
        this.v = v;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
    }


    @Override
    public Long call() {
        long s = 0;
        for (int i = startIdx; i <= stopIdx; i++) {
            s += this.v[i];
        }
        this.sum = s;
        return this.sum;
    }
}
