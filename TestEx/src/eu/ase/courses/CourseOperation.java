package eu.ase.courses;

import java.util.List;

@FunctionalInterface
public interface CourseOperation {
    double process(List<Course> courses);
}
