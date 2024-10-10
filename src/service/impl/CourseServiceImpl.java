package service.impl;
import model.Course;
import model.Teacher;
import model.dto.CourseDto;
import repository.CourseRepository;
import repository.TeacherRepository;
import service.CourseService;
import util.Printer;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void creatOrUpdate(Course entity) {
        try {
        checkCourse(entity);
        courseRepository.creatOrUpdate(new Course(
                entity.getId(),
                entity.getTitle(),
                entity.getUnite()
        ));

        } catch (SQLException sqlException){
            Printer.printError("There is a problem to connecting database!!!");
        }
    }
    private void checkCourse(Course course){
        if (course.getTitle() == null){
            throw new IllegalArgumentException("course unit cannot be null !");
        }
    }

    @Override
    public CourseDto findById(Long id) throws SQLException{
            Course course = courseRepository.findById(id);
            if (course.getId() == null){
                Printer.printError("There is no course with this  id !!!");
            } else {
                Teacher teacher = teacherRepository.getCourseTeacher(id);
                return new CourseDto(course.getTitle(), course.getUnite(), teacher.getFirstName(), teacher.getLastName());
            }
            return null;
    }

    @Override
    public void delete(Long id) {
        try {
            courseRepository.delete(id);
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    public Set<CourseDto> getAll() throws SQLException{
            Set<Course> courses = courseRepository.getAll();
            Set<CourseDto> courseDtos = new HashSet<>();
            for (Course course : courses){
                CourseDto courseDto = new CourseDto();
                Teacher teacher = teacherRepository.getCourseTeacher(course.getId());
                courseDto.setCourseTitle(course.getTitle());
                courseDto.setCourseUnit(course.getUnite());
                courseDto.setTeacherFirstName(teacher.getFirstName());
                courseDto.setTeacherLastName(teacher.getLastName());
                courseDtos.add(courseDto);
            }
            return courseDtos;
    }

    public int getCount() throws SQLException {
        return this.courseRepository.getCount();
    }
}
