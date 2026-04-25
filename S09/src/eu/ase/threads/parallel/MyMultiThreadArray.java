package eu.ase.threads.parallel;

public class MyMultiThreadArray implements Runnable {
    private int[]v;
    private int startIdx;
    private int stopIdx;
    private long sum;
    public MyMultiThreadArray(int[]v,int startIdx,int stopIdx){
        this.v=v;
        this.startIdx=startIdx;
        this.stopIdx=stopIdx;
    }

    @Override
    public void run(){
        long s=0;
        for(int idx=this.startIdx;idx<=stopIdx;idx++){
            s+=this.v[idx];
        }
        this.sum=s;
    }

    public Long getSum(){
        return this.sum;
    }
}
