package repository.impl;
import database.Database;
import model.Student;
import repository.StudentRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StudentRepositoryImpl implements StudentRepository {
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
    private static final String CREATE_STUDENT =
            "insert into student (user_id, gpu)\n" +
            "values (?,?);";
    //language=SQl
    private static final String UPDATE_STUDENT =
            "update student\n" +
            "set (user_id, gpu) = (?,?)\n" +
            "where student_id = ?;";
    //language=SQL
    private static final String FIND_BY_ID = "select * from student where student_id = ?";
    //language=SQl
    private static final String DELETE_STUDENT = "delete from student where student_id = ?;";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM student";
    //language=SQL
    private static final String GET_ALL_STUDENTS = "select * from student ;";
    //language=SQL
    private static final String GET_STUDENT_USER_DATA =
            "select *\n" +
             "from users u \n" +
             "inner join student s on u.id = s.user_id\n";

    @Override
    public void creatOrUpdate(Student entity) throws SQLException {
        if (entity.getId() == null){
            creat(entity);
        } else {
            update(entity);
        }
    }
    private void creat(Student student) throws SQLException {
        PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(CREAT_USER);
        preparedStatement.setString(1 , student.getFirstName());
        preparedStatement.setString(2 , student.getLastName());
        preparedStatement.setString(3 , student.getNationalCode());
        preparedStatement.setDate(4 , student.getDob());
        preparedStatement.setDate(5 , student.getEntryDate());
        preparedStatement.setString(6 , student.getUsername());
        preparedStatement.setString(7 , student.getPassword());
        preparedStatement.setString(8 , student.getType().name());
        preparedStatement.executeUpdate();
        PreparedStatement pr = database.connectToDatabase().prepareStatement(CREATE_STUDENT);
        pr.setLong(1 , student.getId());
        pr.setDouble(2 , student.getGpu());
        pr.executeUpdate();
    }
    private void update(Student student) throws SQLException {
        PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(UPDATE_USER);
        preparedStatement.setString(1 , student.getFirstName());
        preparedStatement.setString(2 , student.getLastName());
        preparedStatement.setString(3 , student.getNationalCode());
        preparedStatement.setDate(4 , student.getDob());
        preparedStatement.setDate(5 , student.getEntryDate());
        preparedStatement.setString(6 , student.getUsername());
        preparedStatement.setString(7 , student.getPassword());
        preparedStatement.setString(8 , student.getType().name());
        preparedStatement.setLong(9 , student.getId());
        preparedStatement.executeUpdate();
        PreparedStatement pr = database.connectToDatabase().prepareStatement(UPDATE_STUDENT);
        pr.setLong(1 , student.getId());
        pr.setString(2 , student.getFirstName());
        pr.setLong(1 , student.getId());
        pr.executeUpdate();
    }

    @Override
    public Student findById(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(FIND_BY_ID);
        ResultSet userDataResultSet = database.getStatement().executeQuery(GET_STUDENT_USER_DATA);
        pr.setLong(1 , id);
        ResultSet studentResultSet = pr.executeQuery();
        Student student = new Student();
        while (studentResultSet.next() && userDataResultSet.next()){
            student = new Student(
                    studentResultSet.getLong("student_id") ,
                    userDataResultSet.getString("first_name"),
                    userDataResultSet.getString("last_name"),
                    userDataResultSet.getDate("dob"),
                    userDataResultSet.getString("national_code") ,
                    studentResultSet.getDouble("gpu") ,
                    userDataResultSet.getDate("entry_date") ,
                    userDataResultSet.getString("username"),
                    userDataResultSet.getString("password")
            );
        }
        return student;
    }


    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(DELETE_STUDENT);
        pr.setLong(1 , id);
        pr.executeUpdate();
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_COUNT_OF_STUDENTS);
        int studentCount = 0;
        while (resultSet.next()) {
            studentCount = resultSet.getInt("count");
        }
        return studentCount;
    }
    @Override
    public Set<Student> getAll() throws SQLException {
        ResultSet studentResultSet = this.database.getStatement().executeQuery(GET_ALL_STUDENTS);
        ResultSet userDataResultSet = this.database.getStatement().executeQuery(GET_STUDENT_USER_DATA);
        Set<Student> students = new HashSet<>();
        while (studentResultSet.next() && userDataResultSet.next()) {
            Student student = new Student(
                    studentResultSet.getLong("student_id") ,
                    userDataResultSet.getString("first_name"),
                    userDataResultSet.getString("last_name"),
                    userDataResultSet.getDate("dob"),
                    userDataResultSet.getString("national_code") ,
                    studentResultSet.getDouble("gpu") ,
                    userDataResultSet.getDate("entry_date") ,
                    userDataResultSet.getString("username"),
                    userDataResultSet.getString("password")
            );
            students.add(student);
        }
        return students;
    }

}