package model.dto;

import static util.Color.CYAN;
import static util.Color.RESET;

public class CourseDto {
    private String courseTitle;
    private int courseUnit;
    private String teacherFirstName;
    private String teacherLastName;

    public CourseDto(String courseTitle, int courseUnit, String teacherFirstName, String teacherLastName) {
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
    }

    public CourseDto() {
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(int courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-12s | %-15s | %-15s\n", courseTitle, courseUnit, teacherFirstName , teacherLastName);
    }
}
