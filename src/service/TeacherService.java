package service;

import model.Teacher;
import model.dto.TeacherDto;

import java.sql.SQLException;
import java.util.Set;

public interface TeacherService {
    void creatOrUpdate(Teacher entity);
    TeacherDto findById(Long id)throws SQLException;
    void delete(Long id);
    Set<TeacherDto> getAll()throws SQLException;
    int getCount()throws SQLException;
}
