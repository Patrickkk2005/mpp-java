package ooparrays;

import java.util.Arrays;

public class Student {
    public String name;
    public short[] marks;
    public float avgMark;
    public static int noStud;

    public Student(){

    }

    public Student(String name, short[] marks){
        this.name=name;
        this.marks=marks;
        Student.noStud++;
        this.avgMark=calcAvgMarks();
    }

    public void setMarks(short[] marks){
        this.marks=marks;
        this.avgMark=calcAvgMarks();
    }

    public float getAvgMarks(){
        return avgMark;
    }

    public float calcAvgMarks(){
        float result = 0.0f;
        if(marks==null||marks.length==0){
            return 0.0f;
        }

        for(int i=0;i<marks.length;i++){
            result+=this.marks[i];
        }

        result=result/marks.length;

        return result;
    }

    @Override
    public String toString(){
        return "Student: name="+name+", marks="+marks+", avgMark="+avgMark;
    }

    @Override
    public int hashCode(){
        int result=0;
        if(name!=null){
            result+=name.hashCode();
        }
        if(marks!=null){
            result+= Arrays.hashCode(marks);
        }

        result+= Float.floatToIntBits(avgMark);

        return result;
    }

}
