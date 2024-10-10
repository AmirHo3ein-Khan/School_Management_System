package repository;

import model.Course;

import java.sql.SQLException;
import java.util.Set;

public interface CourseRepository {
    void creatOrUpdate(Course entity) throws SQLException;

    Course findById(Long id) throws SQLException;

    void delete(Long id) throws SQLException;

    Set<Course> getAll() throws SQLException;

    int getCount() throws SQLException;
    Course getCourseAndExams(Long id) throws SQLException;
    String getTeacherCourse(Long id ) throws SQLException;
}
