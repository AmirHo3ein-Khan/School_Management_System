package repository.impl;
import database.Database;
import model.Course;
import model.dto.CourseDto;
import repository.CourseRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseRepositoryImpl implements CourseRepository {
    private Database database = new Database();
    //language=SQl
    private static final String CREATE_COURSE = "insert into course (course_unit, course_title) values (?,?)";
    //language=SQl
    private static final String UPDATE_COURSE =
            "update course\n" +
            "set (course_unit, course_title) = (?,?)\n" +
            "where course_id = ?";
    //language=SQl
    private static final String FIND_BY_ID = "select * from course where course_id = ?";
    //language=SQl
    private static final String DELETE_COURSE = "delete from course where course_id = ?;";
    //language=SQl
    private static final String GET_ALL_COURSES = "select * from course";
    //language=SQl
    private static final String GET_COUNT_OF_COURSES = "SELECT count(*) FROM course";
    //language=SQl
    private static final String GET_COURSE_AND_EXAMS =
            "select t.*,c.*,e.*\n" +
            "from exam e\n" +
            "inner join course c\n" +
            "on e.course_id = c.course_id\n" +
            "inner join teacher t\n" +
            "on c.course_id = t.course_id\n" +
            "inner join users u on t.user_id = u.id\n" +
            "where e.course_id = ?;";

    //language=SQl
    private static final String GET_COURSE_AND_TEACHERS =
            "select c.course_title\n" +
            "from course c\n" +
            "inner join teacher t\n" +
            "on t.course_id = c.course_id\n" +
            "where t.course_id = ?;";

    @Override
    public void creatOrUpdate(Course entity) throws SQLException {
        if (entity.getId() == null){
            save(entity);
        } else {
            merge(entity);
        }
    }
    private void save(Course course) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(CREATE_COURSE);
        pr.setInt(1 , course.getUnite());
        pr.setString(2 , course.getTitle());
        pr.executeUpdate();
    }
    private void merge(Course course) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(UPDATE_COURSE);
        pr.setInt(1 , course.getUnite());
        pr.setString(2 , course.getTitle());
        pr.setLong(3 , course.getId());
        pr.executeUpdate();
    }

    @Override
    public Course findById(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(FIND_BY_ID);
        pr.setLong(1,id);
        pr.executeQuery();
        ResultSet resultSet = pr.executeQuery();
        Course course = new Course();
        while (resultSet.next()){
            course = new Course(
            resultSet.getLong("course_id"),
            resultSet.getString("course_title"),
            resultSet.getInt("course_unit")
            );
        }
        return course;
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(DELETE_COURSE);
        pr.setLong(1 , id);
        pr.executeUpdate();
    }

    @Override
    public int getCount () throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_COUNT_OF_COURSES);
        int courseCount = 0;
        while (resultSet.next()) {
            courseCount = resultSet.getInt("count");
        }
        return courseCount;
    }

    @Override
    public Set<Course> getAll () throws SQLException {
        ResultSet resultSet = this.database.getStatement().executeQuery(GET_ALL_COURSES);
        Set<Course> courses = new HashSet<>();
        while (resultSet.next()) {
            Course course = new Course(
                    resultSet.getLong("course_id"),
                    resultSet.getString("course_title"),
                    resultSet.getInt("course_unit")
            );
            courses.add(course);
        }
        return courses;
    }
    @Override
    public Course getCourseAndExams(Long id) throws SQLException{
        PreparedStatement pr = database.connectToDatabase().prepareStatement(GET_COURSE_AND_EXAMS);
        pr.setLong(1 , id);
        ResultSet resultSet = pr.executeQuery();
        Course course = new Course();
        while (resultSet.next()) {
            course = new Course(
                    resultSet.getLong("course_id"),
                    resultSet.getString("course_title"),
                    resultSet.getInt("course_unit")
            );
        }
        return course;
    }

    @Override
    public String getTeacherCourse(Long id) throws SQLException {
        PreparedStatement pr = database.connectToDatabase().prepareStatement(GET_COURSE_AND_TEACHERS);
        pr.setLong(1 , id);
        ResultSet resultSet = pr.executeQuery();
        String string = null;
        while (resultSet.next()){
            string = resultSet.getString("course_title");
        }
        return string;
    }
}
