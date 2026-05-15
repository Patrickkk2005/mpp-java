package eu.ase.courses;

import java.util.List;

public class CourseTask implements Runnable {
    private List<Course> v;
    String department;
    Course topCourse;

    public CourseTask(List<Course> v, String department) {
        this.v = v;
        this.department = department;
    }

    @Override
    public void run() {
        Course max = new Course();
        for (Course course : v) {
            if(course.getCredits()>max.getCredits()){
                max = course;
            }
        }
        topCourse = max;
    }

    public Course getTopCourse() {
        return topCourse;
    }
}
