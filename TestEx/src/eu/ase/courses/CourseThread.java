package eu.ase.courses;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseThread extends Thread {
    private static final Object lock = new Object();
    private Map<String, List<Course>> coursesByDesc;
    private Map<String, Double> averageCredits;
    private List<Course> courses;
    private String mode;

    public CourseThread(Map<String, List<Course>> coursesByDesc, String mode, Map<String, Double> averageCredits, List<Course> courses) {
        this.coursesByDesc = coursesByDesc;
        this.mode = mode;
        this.averageCredits = averageCredits;
        this.courses = courses;
    }

    public void desc() {
        synchronized (lock) {
            coursesByDesc = new HashMap<>();
            for (Course course : courses) {
                if (!coursesByDesc.containsKey(course.getDescription())) {
                    coursesByDesc.put(course.getDescription(), new ArrayList<>());
                }
                coursesByDesc.get(course.getDescription()).add(course);
            }
        }
    }

    public void avgCredit() {
        synchronized (lock) {
            CourseOperation avgCred = courseList -> {
                int sum = 0;
                for (Course course : courseList) {
                    sum += course.getCredits();
                }
                return (double) sum / courseList.size();
            };

            averageCredits = new HashMap<>();
            for (Map.Entry<String, List<Course>> entry : coursesByDesc.entrySet()) {
                String courseName = entry.getKey();
                List<Course> courseList = entry.getValue();
                averageCredits.put(courseName, avgCred.process(courseList));
            }
        }
    }

    public void report() throws IOException {
        synchronized (lock) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("courses_out.txt")));
            writer.write(courses.toString());
            writer.newLine();
            writer.close();
        }
    }

    @Override
    public void run() {
        if (mode.equals("stats")) this.desc();
        else if (mode.equals("top")) this.avgCredit();
        else if (mode.equals("report")) {
            try {
                this.report();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
