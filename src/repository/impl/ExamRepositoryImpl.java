package repository.impl;

import database.Database;
import model.Exam;
import model.dto.ExamDto;
import repository.ExamRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ExamRepositoryImpl implements ExamRepository {
    private Database database = new Database();
    //language=SQl
    private static final String CREATE_EXAM =
            "insert into exam (teacher_id , course_id, exam_date)\n" +
            "values (?,?,?);";
    //language=SQl
    private static final String UPDATE_EXAM =
            "update exam\n" +
            "set (teacher_id , course_id, exam_date) = (?,?,?)\n" +
            "where exam_id = ?;";
    //language=SQl
    private static final String FIND_BY_ID = "select * from exam where exam_id = ?;";
    //language=SQl
    private static final String DELETE_EXAM = "delete from exam where exam_id = ?;";
    //language=SQl
    private static final String GET_ALL_EXAMS = "select * from exam;";
    //language=SQL
    private static final String GET_COUNT_OF_EXAMS = "SELECT count(*) FROM exam";

    @Override
    public void creatOrUpdate(Exam entity) throws SQLException {
        if (entity.getId() == null){
            save(entity);
        } else {
            merge(entity);
        }
    }
    private void save(Exam exam) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(CREATE_EXAM);
        pr.setLong(1 , exam.getTeacherId());
        pr.setLong(2 , exam.getCourseId());
        pr.setDate(3 , exam.getDate());
        pr.executeUpdate();
    }
    private void merge(Exam exam) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(UPDATE_EXAM);
        pr.setLong(1 , exam.getTeacherId());
        pr.setLong(2 , exam.getCourseId());
        pr.setDate(3 , exam.getDate());
        pr.setLong(4 , exam.getId());
        pr.executeUpdate();
    }

    @Override
    public Exam findById(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(FIND_BY_ID);
        pr.setLong(1 , id);
        ResultSet resultSet = pr.executeQuery();
        Exam exam = new Exam();
        while (resultSet.next()){
            exam = new Exam(
                    resultSet.getLong("exam_id"),
                    resultSet.getDate("exam_date"),
                    resultSet.getLong("teacher_id"),
                    resultSet.getLong("course_id")
            );
        }
        return exam;
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(DELETE_EXAM);
        pr.setLong(1 , id);
        pr.executeUpdate();
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_COUNT_OF_EXAMS);
        int examCount = 0;
        while (resultSet.next()) {
            examCount = resultSet.getInt("count");
        }
        return examCount;
    }
    @Override
    public Set<Exam> getAll () throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_ALL_EXAMS);
        Set<Exam> exams = new HashSet<>();
        while (resultSet.next()) {
            Exam exam = new Exam(
                    resultSet.getLong("exam_id"),
                    resultSet.getDate("exam_date"),
                    resultSet.getLong("teacher_id"),
                    resultSet.getLong("course_id")
            );
            exams.add(exam);
        }
        return exams;
    }
}
