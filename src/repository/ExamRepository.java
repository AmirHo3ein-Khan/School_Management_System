package repository;

import model.Course;
import model.Exam;
import model.dto.ExamDto;

import java.sql.SQLException;
import java.util.Set;

public interface ExamRepository {
    void creatOrUpdate(Exam entity) throws SQLException;

    Exam findById(Long id) throws SQLException;

    void delete(Long id) throws SQLException;

    Set<Exam> getAll() throws SQLException;

    int getCount() throws SQLException;
}
