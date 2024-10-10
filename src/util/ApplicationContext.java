package util;
import repository.*;
import repository.impl.CourseRepositoryImpl;
import repository.impl.ExamRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.TeacherRepositoryImpl;
import service.CourseService;
import service.ExamService;
import service.StudentService;
import service.TeacherService;
import service.impl.CourseServiceImpl;
import service.impl.ExamServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;

public class ApplicationContext {
    public static String [] starterMenu = {"Sign In","EXIT"};
    public static String [] menu = {"STUDENT","TEACHER","COURSES","EXAMS","EXIT"};
    public static String [] studentsMenu = {"CREAT ","UPDATE","ALL STUDENTS","COUNT OF STUDENTS","FIND STUDENT BY ID","EXIT"};
    public static String [] teacherMenu = {"CREAT "," UPDATE","ALL TEACHER","COUNT OF TEACHER","FIND TEACHER BY ID","EXIT"};
    public static String [] coursesMenu = {"CREAT "," UPDATE","ALL COURSES","COUNT OF COURSES","FIND COURSES BY ID","EXIT"};
    public static String [] examsMenu = {"CREAT ","UPDATE","ALL EXAMS","COUNT OF EXAMS","FIND EXAMS BY ID","EXIT"};


    private static StudentRepository studentRepository;
    private static TeacherRepository teacherRepository;
    private static CourseRepository courseRepository;
    private static ExamRepository examRepository;
    private static StudentService studentService;
    private static TeacherService teacherService;
    private static CourseService courseService;
    private static ExamService examService;

    static {
        studentRepository = new StudentRepositoryImpl();
        teacherRepository = new TeacherRepositoryImpl();
        courseRepository = new CourseRepositoryImpl();
        examRepository = new ExamRepositoryImpl();
        studentService = new StudentServiceImpl(studentRepository);
        teacherService = new TeacherServiceImpl(teacherRepository  , courseRepository);
        courseService = new CourseServiceImpl(courseRepository , teacherRepository);
        examService = new ExamServiceImpl(examRepository , courseRepository , teacherRepository);
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    public static TeacherService getTeacherService() {
        return teacherService;
    }

    public static CourseService getCourseService() {
        return courseService;
    }

    public static ExamService getExamService() {
        return examService;
    }
}
