package repository;
import model.Student;
import model.dto.StudentCourseDto;
import model.dto.StudentDto;
import java.sql.SQLException;
import java.util.Set;

public interface StudentRepository {
    void creatOrUpdate(Student entity) throws SQLException;

    Student findById(Long id) throws SQLException;

    void delete(Long id) throws SQLException;

    Set<Student> getAll() throws SQLException;

    int getCount() throws SQLException;

}
