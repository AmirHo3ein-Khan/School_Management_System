package service;
import model.Student;
import model.dto.StudentDto;

import java.sql.SQLException;
import java.util.Set;

public interface StudentService {
    void creatOrUpdate(Student entity);
    StudentDto findById(Long id)throws SQLException;
    void delete(Long id);

    Set<StudentDto> getAll()throws SQLException;
    int getCount()throws SQLException;
}
