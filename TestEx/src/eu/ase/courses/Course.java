package eu.ase.courses;

import java.io.Serializable;

public class Course implements Serializable, Comparable<Course>, Cloneable {
    private String name;
    private String description;
    private int credits;
    private int year;

    public Course(String name, String description, int credits, int year) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.year = year;
    }

    public Course() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Course{" + "name=" + name + ", description=" + description + ", credits=" + credits + ", year=" + year + '}';
    }

    @Override
    public int compareTo(Course o) {
        if (this.credits < o.credits) {
            return -1;
        } else if (this.credits > o.credits) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Course other = (Course) o;
        if (this.credits != other.credits) return false;
        if (this.year != other.year) return false;
        if (!this.name.equals(other.name)) return false;
        if (!this.description.equals(other.description)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result =0;
        if(name!=null) result += name.hashCode();
        result += credits;
        return result;
    }

    @Override
    public Course clone() throws CloneNotSupportedException {
        Course clone = (Course) super.clone();
        clone.name = new String(this.name);
        clone.description = new String(this.description);
        clone.credits = this.credits;
        clone.year = this.year;
        return clone;
    }

}
