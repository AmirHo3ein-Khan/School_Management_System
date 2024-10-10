package ui;

import model.Course;
import model.Manager;
import model.Student;
import model.dto.CourseDto;
import model.dto.ExamDto;
import model.dto.StudentDto;
import model.dto.TeacherDto;
import util.ApplicationContext;
import util.Printer;
import util.Utility;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static util.Color.*;

public class Menu {
    public static void startProject() {
        Printer.printMenu(ApplicationContext.starterMenu, "WELCOME");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                singIn();
                break;
            case 2:
                break;
        }
    }

    private static void singIn() {
        String username = Utility.getString("Enter your username : ");
        String password = Utility.getString("Enter your password : ");
        if (username.equals(Manager.getUsername()) && password.equals(Manager.getPassword())) {
            managerAccess();
        } else {
            Printer.printError("Username or password is wrong !!!");
            Printer.printError("Please try again :) ");
            startProject();
        }
    }

    private static void managerAccess() {
        Printer.printMenu(ApplicationContext.menu, "WELCOME");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                studentAccess();
                break;
            case 2:
                teacherAccess();
                break;
            case 3:
                coursesAccess();
                break;
            case 4:
                examAccess();
                break;
            case 5:
                startProject();
                break;
        }
        managerAccess();
    }

    private static void studentAccess() {
        Printer.printMenu(ApplicationContext.studentsMenu, "STUDENT");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                creatStudent();
                break;
            case 2:
                updateStudent();
                break;
            case 3:
                printAllStudent();
                break;
            case 4:
                printCountOfStudent();
                break;
            case 5:
                printStudentFindById();
                break;
            case 6:
                managerAccess();
                break;
        }
        studentAccess();
    }


    private static void creatStudent() {

    }
    private static void updateStudent() {
    }

    private static void printStudentFindById() {
        try {
            Long id = Utility.getLong("Enter id of student you want find : ");
            StudentDto student = ApplicationContext.getStudentService().findById(id);
            System.out.println(String.format(CYAN + "*** Student *** \n" + "%-12s | %-12s | %-14s | %-12s | %-12s | %-12s",
                    "firstName", "lastName", "nationalCode", "dob", "entryDate", "gpu"));
            Printer.printLine("====================================================================================");
            System.out.println(BLUE + student + RESET);
            Printer.printLine("====================================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printCountOfStudent() {
        try {
            int count = ApplicationContext.getStudentService().getCount();
            Printer.printLine("~~~~~~~~~~~~~~~");
            Printer.printDescription("#Students : " + count);
            Printer.printLine("~~~~~~~~~~~~~~~");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printAllStudent() {
        try {
            Set<StudentDto> all = ApplicationContext.getStudentService().getAll();
            System.out.println(String.format(CYAN + "*** Student *** \n" + "%-12s | %-12s | %-14s | %-12s | %-12s | %-12s",
                    "firstName", "lastName", "nationalCode", "dob", "entryDate", "gpu"));
            Printer.printLine("====================================================================================");
            Printer.printSet(all);
            Printer.printLine("====================================================================================");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


    private static void teacherAccess() {
        Printer.printMenu(ApplicationContext.teacherMenu, "TEACHER");
        int choice = Utility.getInt("Enter your choice :");
        switch (choice) {
            case 1:
                creatTeacher();
                break;
            case 2:
                updateTeacher();
                break;
            case 3:
                printAllTeacher();
                break;
            case 4:
                printCountOfTeacher();
                break;
            case 5:
                printTeacherFindById();
                break;
            case 6:
                managerAccess();
                break;
        }
        teacherAccess();
    }

    private static void creatTeacher() {
    }
    private static void updateTeacher() {
    }

    private static void printTeacherFindById() {
        try {
            Long id = Utility.getLong("Enter id of teacher you want find : ");
            TeacherDto teacher = ApplicationContext.getTeacherService().findById(id);
            System.out.println(String.format(CYAN+" Teacher " + "%-12s | %-12s | %-14s | %-12s | %-12s | %-12s",
                    "firstName", "lastName", "nationalCode", "dob", "entryDate", "courseTitle"+ RESET));
            Printer.printLine("==================================================================================================");
            System.out.println(BLUE+teacher+RESET);
            Printer.printLine("==================================================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printCountOfTeacher() {
        try {
            int count = ApplicationContext.getTeacherService().getCount();
            Printer.printLine("~~~~~~~~~~~~~~~");
            Printer.printDescription("#Teachers : " + count);
            Printer.printLine("~~~~~~~~~~~~~~~");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printAllTeacher() {
        try {
            Set<TeacherDto> all = ApplicationContext.getTeacherService().getAll();
            System.out.println(String.format(CYAN + "*** Teachers ***\n" + "%-12s | %-12s | %-14s | %-12s | %-12s | %-12s",
                    "firstName", "lastName", "nationalCode", "dob", "entryDate", "courseTitle"));
            Printer.printLine("==================================================================================================");
            Printer.printSet(all);
            Printer.printLine("==================================================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }


    private static void coursesAccess() {
        Printer.printMenu(ApplicationContext.coursesMenu, "COURSES");
        int choice = Utility.getInt("Enter your choice :");
        switch (choice) {
            case 1:
                creatCourse();
                break;
            case 2:
                updateCourse();
                break;
            case 3:
                printAllCourse();
                break;
            case 4:
                printCountOfCourse();
                break;
            case 5:
                printCourseFindById();
                break;
            case 6:
                managerAccess();
                break;
        }
        coursesAccess();
    }

    private static void updateCourse() {
    }
    private static void creatCourse() {
    }

    private static void printCourseFindById() {
        try {
            Long id = Utility.getLong("Enter id of course you want find : ");
            CourseDto course = ApplicationContext.getCourseService().findById(id);
            System.out.println(String.format(CYAN + "Courses\n" + "%-12s | %-12s | %-15s | %-15s\n", "courseTitle", "courseUnit", "teacherFirstName", "teacherLastName" + RESET));
            Printer.printLine("==========================================================================");
            System.out.println(BLUE+course+RESET);
            Printer.printLine("==========================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printCountOfCourse() {
        try {
            int count = ApplicationContext.getCourseService().getCount();
            Printer.printLine("~~~~~~~~~~~~~~~");
            Printer.printDescription("#Courses : " + count);
            Printer.printLine("~~~~~~~~~~~~~~~");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }

    }

    private static void printAllCourse() {
        try {
            Set<CourseDto> all = ApplicationContext.getCourseService().getAll();
            System.out.println(String.format(CYAN + "Courses\n" + "%-12s | %-12s | %-15s | %-15s\n", "courseTitle", "courseUnit", "teacherFirstName", "teacherLastName" + RESET));
            Printer.printLine("================================================================================");
            Printer.printSet(all);
            Printer.printLine("================================================================================");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            Printer.printError("There is a problem to connecting database!!!");
        }

    }


    private static void examAccess() {
        Printer.printMenu(ApplicationContext.examsMenu, "EXAM");
        int choice = Utility.getInt("Enter your choice :");
        switch (choice) {
            case 1:
                creatExam();
                break;
            case 2:
                updateExam();
                break;
            case 3:
                printAllExam();
                break;
            case 4:
                printCountOfExam();
                break;
            case 5:
                printExamFindById();
                break;
            case 6:
                managerAccess();
                break;
        }
        examAccess();
    }

    private static void updateExam() {
    }
    private static void creatExam() {
    }

    private static void printCountOfExam() {
        try {
            int count = ApplicationContext.getExamService().getCount();
            Printer.printLine("~~~~~~~~~~~~~~~");
            Printer.printDescription("#Exams : " + count);
            Printer.printLine("~~~~~~~~~~~~~~~");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printExamFindById() {
        try {
            Long id = Utility.getLong("Enter id of exam you want find : ");
            ExamDto exam = ApplicationContext.getExamService().findById(id);
            System.out.println(String.format(CYAN + "Exams\n" + "%-15s | %-15s | %-12s | %-12s"
                    , "teacherFistName", "teacherLastName", "courseTitle", "examDate"+ RESET));
            Printer.printLine("============================================================================");
            System.out.println(exam);
            Printer.printLine("============================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private static void printAllExam() {
        try {
            Set<ExamDto> all = ApplicationContext.getExamService().getAll();
            System.out.println(String.format(CYAN+"Exams\n" + "%-15s | %-15s | %-12s | %-12s"
                    , "teacherFistName", "teacherLastName", "courseTitle", "examDate" +RESET));
            Printer.printLine("============================================================================");
            Printer.printSet(all);
            Printer.printLine("============================================================================");
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

}
