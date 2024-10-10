package service;

import model.Course;
import model.dto.CourseDto;

import java.sql.SQLException;
import java.util.Set;

public interface CourseService {
    void creatOrUpdate(Course entity);
    CourseDto findById(Long id)throws SQLException;
    void delete(Long id);
    Set<CourseDto> getAll()throws SQLException;
    int getCount()throws SQLException;
}
