package repository;
import model.Teacher;

import java.sql.SQLException;
import java.util.Set;

public interface TeacherRepository {
    void creatOrUpdate(Teacher entity) throws SQLException;

    Teacher findById(Long id) throws SQLException;

    void delete(Long id) throws SQLException;

    Set<Teacher> getAll() throws SQLException;

    int getCount() throws SQLException;
    Teacher getCourseTeacher(Long id) throws SQLException;
}
