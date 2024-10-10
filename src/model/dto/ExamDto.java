package model.dto;
import java.sql.Date;

public class ExamDto {
    private String teacherFistName;
    private String teacherLastName;
    private String courseTitle;
    private Date examDate;

    public ExamDto(String teacherFistName, String teacherLastName, String courseTitle, Date examDate) {
        this.teacherFistName = teacherFistName;
        this.teacherLastName = teacherLastName;
        this.courseTitle = courseTitle;
        this.examDate = examDate;
    }

    public ExamDto() {
    }

    public String getTeacherFistName() {
        return teacherFistName;
    }

    public void setTeacherFistName(String teacherFistName) {
        this.teacherFistName = teacherFistName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-12s | %-12s\n", teacherFistName,teacherLastName, courseTitle, examDate);
    }
}
