package eu.ase.courses;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProgMainCourses {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Course> courses = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("courses.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            String courseName = tokens[0];
            String description = tokens[1];
            int credits = Integer.parseInt(tokens[2]);
            int year = Integer.parseInt(tokens[3]);
            Course course = new Course(courseName, description, credits, year);
            courses.add(course);
        }

        Map<String, List<Course>> coursesByDesc = new HashMap<>();
        for (Course course : courses) {
            if (!coursesByDesc.containsKey(course.getDescription())) {
                coursesByDesc.put(course.getDescription(), new ArrayList<>());
            }
            coursesByDesc.get(course.getDescription()).add(course);
        }

        CourseOperation avgCred = courseList -> {
            int sum = 0;
            for (Course course : courseList) {
                sum += course.getCredits();
            }
            return (double) sum / courseList.size();
        };

        Map<String, Double> averageCredits = new HashMap<>();
        for (Map.Entry<String, List<Course>> entry : coursesByDesc.entrySet()) {
            String courseName = entry.getKey();
            List<Course> courseList = entry.getValue();
            averageCredits.put(courseName, avgCred.process(courseList));
        }

        Predicate<Course> highCredit = (Course c) -> c.getCredits() > 5;
        courses.stream().filter(highCredit).collect(Collectors.toList());

        IntSummaryStatistics stats = courses.stream().mapToInt(c -> c.getCredits()).summaryStatistics();
        System.out.println("Max credits: " + stats.getMax());
        System.out.println("Avg credits: " + stats.getAverage());

        ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream("courses_test.txt"));
        sout.writeObject(courses);
        sout.close();

        try (ObjectInputStream sin = new ObjectInputStream(new FileInputStream("courses_test.txt"))) {
            courses = (List<Course>) sin.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<Course, String> treeMap = new TreeMap<Course, String>();
        for (Course course : courses) {
            treeMap.put(course, course.getDescription());
        }

        for (Map.Entry<Course, String> entry : treeMap.entrySet()) {
            Course curs = entry.getKey();
            String cursDesc = entry.getValue();
            System.out.println(curs + " -> " + cursDesc);

        }

        CourseTask[] tasks = new CourseTask[3];
        Thread[] threads = new Thread[3];

        int i = 0;
        for (Map.Entry<String, List<Course>> entry : coursesByDesc.entrySet()) {
            tasks[i] = new CourseTask(entry.getValue(), entry.getKey());
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
            i++;
        }

        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }

        CourseThread t1 = new CourseThread(coursesByDesc, "stats", averageCredits, courses);
        CourseThread t2 = new CourseThread(coursesByDesc, "top", averageCredits, courses);
        CourseThread t3 = new CourseThread(coursesByDesc, "report", averageCredits, courses);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }
}
