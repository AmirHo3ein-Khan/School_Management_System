package model;
import java.sql.*;

public class Student extends User{
    private Double gpu;

    public Student(Long id, String firstName, String lastName, Date dob, String nationalCode,
                   Double gpu, Date entryDate, String username, String password) {
        super(id, firstName, lastName, dob, nationalCode, entryDate, username, password);
        this.gpu = gpu;
    }

    public Student() {
    }

    @Override
    public UserType getType() {
        return UserType.STUDENT;
    }

    public Double getGpu() {
        return gpu;
    }

    public void setGpu(Double gpu) {
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        return String.format("%-8d | %-12s | %-12s | %-12s | %-14s | %-12s | %-12s | %-12s | %-12s\n",
                getId() , getFirstName(), getLastName(), getDob() , getNationalCode(), getGpu(),
                getEntryDate() , getUsername() , getPassword());
    }
}