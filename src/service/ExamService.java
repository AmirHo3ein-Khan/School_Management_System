package service;

import model.Exam;
import model.dto.ExamDto;

import java.sql.SQLException;
import java.util.Set;

public interface ExamService {
    void creatOrUpdate(Exam entity) ;
    ExamDto findById(Long id)throws SQLException;
    void delete(Long id);
    Set<ExamDto> getAll()throws SQLException;
    int getCount()throws SQLException;
}
