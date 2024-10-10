package service.impl;

import model.Course;
import model.Teacher;
import model.dto.TeacherDto;
import repository.CourseRepository;
import repository.TeacherRepository;
import service.TeacherService;
import util.Printer;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void creatOrUpdate(Teacher entity) {
        try {
            checkTeacher(entity);
            teacherRepository.creatOrUpdate(new Teacher(
                    entity.getId(),
                    entity.getCourseId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getDob(),
                    entity.getNationalCode(),
                    entity.getEntryDate(),
                    entity.getUsername(),
                    entity.getPassword()
            ));
        } catch (SQLException sqlException) {

        }
    }

    private void checkTeacher(Teacher teacher) {
        if (teacher.getFirstName() == null || teacher.getLastName() == null || teacher.getNationalCode() == null) {
            throw new IllegalArgumentException("course unit cannot be null !");
        }
    }

    @Override
    public TeacherDto findById(Long id) throws SQLException {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher.getId() == null) {
            Printer.printError("There id no teacher with this id !!!");
        } else {
            String teacherCourse = courseRepository.getTeacherCourse(teacher.getId());
            return new TeacherDto(teacher.getFirstName(), teacher.getLastName(),
                    teacher.getNationalCode(), teacher.getDob(), teacher.getEntryDate(), teacherCourse);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            teacherRepository.delete(id);
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    @Override
    public Set<TeacherDto> getAll() throws SQLException {
        Set<Teacher> teachers = this.teacherRepository.getAll();
        Set<TeacherDto> teacherDtoSet = new HashSet<>();
        for (Teacher teacher : teachers) {
            String teacherCourse = courseRepository.getTeacherCourse(teacher.getId());
            TeacherDto teacherDto = new TeacherDto(teacher.getFirstName(),teacher.getLastName(),
                    teacher.getNationalCode(), teacher.getDob(),teacher.getEntryDate(),teacherCourse);
            teacherDtoSet.add(teacherDto);
        }
        return teacherDtoSet;
    }

    @Override
    public int getCount() throws SQLException{
        return this.teacherRepository.getCount();
    }

}
