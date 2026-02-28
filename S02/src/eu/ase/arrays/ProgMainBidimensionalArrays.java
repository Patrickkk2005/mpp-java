package eu.ase.arrays;

public class ProgMainBidimensionalArrays {
    public static void main(String[] args) {
        int studentNo=2;
        int lectNo=3;

        short[][] studMarks= new short[][] {{5,5,9},{9,10,9}};
        float[] avgMarks=new float[studentNo];

        for(int i=0;i<studentNo;i++){
            avgMarks[i]=0;
            for(int j=0;j<lectNo;j++) {
                avgMarks[i] = avgMarks[i] + studMarks[i][j];
            }
            avgMarks[i]/=lectNo;
        }

        for(int i=0;i<studentNo;i++){
            System.out.println("student: "+i+" avg: "+avgMarks[i]);
        }
    }
}
