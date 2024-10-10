package model.dto;

public class StudentCourseDto {
    private String fullName;
    private String courseTitle;
    private int courseUnit;

    public StudentCourseDto(String fullName, String courseTitle, int courseUnit) {
        this.fullName = fullName;
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return String.format("%-20s | %-12s | %-12s\n",
                fullName , courseTitle , courseUnit);
    }
}
