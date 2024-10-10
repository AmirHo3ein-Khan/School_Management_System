package model.dto;

import java.sql.Date;

public class TeacherDto {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Date dob;
    private Date entryDate;
    private String courseTitle;

    public TeacherDto(String firstName, String lastName, String nationalCode,
                      Date dob, Date entryDate, String courseTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.dob = dob;
        this.entryDate = entryDate;
        this.courseTitle = courseTitle;
    }

    public TeacherDto() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-12s | %-14s | %-12s | %-12s | %-12s\n",
                firstName , lastName , nationalCode , dob , entryDate, courseTitle);
    }
}
