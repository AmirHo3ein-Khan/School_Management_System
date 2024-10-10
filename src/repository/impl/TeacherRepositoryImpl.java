package repository.impl;

import database.Database;
import model.Teacher;
import repository.TeacherRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TeacherRepositoryImpl implements TeacherRepository {
    private Database database = new Database();
    //language=SQl
    private static final String CREAT_USER =
            "insert into users (first_name, last_name, national_code, dob, entry_date, username, password, user_type)\n" +
            "values (?,?,?,?,?,?,?,?);";
    //language=SQl
    private static final String UPDATE_USER =
            "update users\n" +
            "set (first_name, last_name, national_code, dob, entry_date, username, password, user_type) = (?,?,?,?,?,?,?,?)\n" +
            "where id = ?;";
    //language=SQl
    private static final String CREATE_TEACHER = "insert into teacher (user_id, course_id) values (?,?);";
    //language=SQl
    private static final String UPDATE_TEACHER = "update teacher set (user_id, course_id) = (?,?) where teacher_id = ?;";
    //language=SQL
    private static final String FIND_TEACHER_BY_ID = "select * from teacher where teacher_id = ?";

    //language=SQl
    private static final String DELETE_TEACHER = "delete from teacher where teacher_id = ?";
    //language=SQl
    private static final String GET_COUNT_OF_TEACHERS = "SELECT count(*) FROM teacher";
    //language=SQL
    private static final String GET_ALL_TEACHERS = "select * from teacher";
    //language=SQL
    private static final String GET_COURSE_TEACHER =
            "select t.* , u.*\n" +
            "from course c\n" +
            "inner join teacher t\n" +
            "on c.course_id = t.course_id\n" +
            "inner join users u\n" +
            "on t.user_id = u.id where t.course_id = ?;";
    //language=SQL
    private static final String GET_TEACHER_USER_DATA =
            "select *\n" +
            "from users u \n" +
            "inner join teacher t on u.id = t.user_id\n";



    @Override
    public void creatOrUpdate(Teacher entity) throws SQLException {
        if (entity.getId() == null) {
            save(entity);
        } else {
            merge(entity);
        }
    }

    private void save(Teacher entity) throws SQLException {
        PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(CREAT_USER);
        preparedStatement.setString(1 , entity.getFirstName());
        preparedStatement.setString(2 , entity.getLastName());
        preparedStatement.setString(3 , entity.getNationalCode());
        preparedStatement.setDate(4 , entity.getDob());
        preparedStatement.setDate(5 , entity.getEntryDate());
        preparedStatement.setString(6 , entity.getUsername());
        preparedStatement.setString(7 , entity.getPassword());
        preparedStatement.setString(8 , entity.getType().name());
        preparedStatement.executeUpdate();
        PreparedStatement pr = database.connectToDatabase().prepareStatement(CREATE_TEACHER);
        pr.setLong(1 , entity.getId());
        pr.setLong(2 , entity.getCourseId());
        pr.executeUpdate();
    }

    private void merge(Teacher entity) throws SQLException {
        PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(UPDATE_USER);
        preparedStatement.setString(1 , entity.getFirstName());
        preparedStatement.setString(2 , entity.getLastName());
        preparedStatement.setString(3 , entity.getNationalCode());
        preparedStatement.setDate(4 , entity.getDob());
        preparedStatement.setDate(5 , entity.getEntryDate());
        preparedStatement.setString(6 , entity.getUsername());
        preparedStatement.setString(7 , entity.getPassword());
        preparedStatement.setString(8 , entity.getType().name());
        preparedStatement.setLong(9 , entity.getId());
        preparedStatement.executeUpdate();
        PreparedStatement pr = database.connectToDatabase().prepareStatement(UPDATE_TEACHER);
        pr.setLong(1 , entity.getId());
        pr.setLong(2 , entity.getCourseId());
        pr.setLong(3 , entity.getId());
        pr.executeUpdate();
    }

    @Override
    public Teacher findById(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(FIND_TEACHER_BY_ID);
        ResultSet userDataResultSet = database.getStatement().executeQuery(GET_TEACHER_USER_DATA);
        pr.setLong(1 , id);
        ResultSet teacherResultSet = pr.executeQuery();
        Teacher teacher = new Teacher();
        while (teacherResultSet.next() && userDataResultSet.next()){
            teacher = new Teacher(
                    teacherResultSet.getLong("teacher_id") ,
                    teacherResultSet.getLong("course_id") ,
                    userDataResultSet.getString("first_name"),
                    userDataResultSet.getString("last_name"),
                    userDataResultSet.getDate("dob"),
                    userDataResultSet.getString("national_code") ,
                    userDataResultSet.getDate("entry_date"),
                    userDataResultSet.getString("username") ,
                    userDataResultSet.getString("password")
            );
        }
        return teacher;
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pr = this.database.connectToDatabase().prepareStatement(DELETE_TEACHER);
        pr.setLong(1, id);
        pr.executeUpdate();
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_COUNT_OF_TEACHERS);
        int teacherCount = 0;
        while (resultSet.next()) {
            teacherCount = resultSet.getInt("count");
        }
        return teacherCount;
    }

    @Override
    public Set<Teacher> getAll()throws SQLException {
        ResultSet teacherResultSet = this.database.getStatement().executeQuery(GET_ALL_TEACHERS);
        ResultSet userDataResultSet = this.database.getStatement().executeQuery(GET_TEACHER_USER_DATA);
        Set<Teacher> teachers = new HashSet<>();
        while (teacherResultSet.next() && userDataResultSet.next()) {
            Teacher teacher = new Teacher(
                    teacherResultSet.getLong("teacher_id") ,
                    teacherResultSet.getLong("course_id") ,
                    userDataResultSet.getString("first_name"),
                    userDataResultSet.getString("last_name"),
                    userDataResultSet.getDate("dob"),
                    userDataResultSet.getString("national_code") ,
                    userDataResultSet.getDate("entry_date"),
                    userDataResultSet.getString("username") ,
                    userDataResultSet.getString("password")
            );
            teachers.add(teacher);
        }
        return teachers;
    }
    @Override
    public Teacher getCourseTeacher(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(GET_COURSE_TEACHER);
        pr.setLong(1 , id);
        ResultSet resultSet = pr.executeQuery();
        Teacher teacher = new Teacher();
        while (resultSet.next()) {
            teacher = new Teacher(
                    resultSet.getLong("teacher_id"),
                    resultSet.getLong("course_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("dob"),
                    resultSet.getString("national_code"),
                    resultSet.getDate("entry_date") ,
                    resultSet.getString("username") ,
                    resultSet.getString("password")
            );
        }
        return teacher;
    }

}