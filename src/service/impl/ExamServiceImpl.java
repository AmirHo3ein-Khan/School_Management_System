package service.impl;

import model.Course;
import model.Exam;
import model.Teacher;
import model.dto.ExamDto;
import repository.CourseRepository;
import repository.ExamRepository;
import repository.TeacherRepository;
import service.ExamService;
import util.Printer;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public ExamServiceImpl(ExamRepository examRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void creatOrUpdate(Exam entity) {
        try {
            checkExam(entity);
            examRepository.creatOrUpdate(new Exam(
                    entity.getId(),
                    entity.getDate(),
                    entity.getTeacherId(),
                    entity.getCourseId()
            ));
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private void checkExam(Exam exam) {
        if (exam.getTeacherId() == null || exam.getCourseId() == null) {
            throw new IllegalArgumentException("course unit cannot be null !");
        }
    }

    @Override
    public ExamDto findById(Long id) throws SQLException {
        Exam exam = examRepository.findById(id);
        if (exam.getId() == null){
            Printer.printError("There is no exam with this id !!!");
        } else {
            Course course = courseRepository.getCourseAndExams(exam.getCourseId());
            Teacher teacher = teacherRepository.getCourseTeacher(course.getId());
            return new ExamDto(teacher.getFirstName(), teacher.getLastName(), course.getTitle(), exam.getDate());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            examRepository.delete(id);
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    public Set<ExamDto> getAll() throws SQLException {
        Set<Exam> exams = examRepository.getAll();
        Set<ExamDto> examDtos = new HashSet<>();
        for (Exam exam : exams) {
            Course course = courseRepository.getCourseAndExams(exam.getCourseId());
            Teacher teacher = teacherRepository.getCourseTeacher(course.getId());
            examDtos.add (new ExamDto(teacher.getFirstName() , teacher.getLastName() , course.getTitle() , exam.getDate()));
        }
        return examDtos;
    }

    public int getCount() throws SQLException{
        return examRepository.getCount();
    }
}
