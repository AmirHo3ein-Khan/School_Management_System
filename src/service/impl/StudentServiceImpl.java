package service.impl;

import model.Student;
import model.dto.StudentDto;
import repository.StudentRepository;
import service.StudentService;
import util.Printer;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void creatOrUpdate(Student entity) {
        try {
            checkStudent(entity);
            studentRepository.creatOrUpdate(new Student(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getDob(),
                    entity.getNationalCode(),
                    entity.getGpu(),
                    entity.getEntryDate(),
                    entity.getUsername(),
                    entity.getPassword()
            ));
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    private void checkStudent(Student student) {
        if (student.getFirstName() == null || student.getLastName() == null || student.getNationalCode() == null) {
            throw new IllegalArgumentException("course unit cannot be null !");
        }
    }

    @Override
    public StudentDto findById(Long id) throws SQLException {
        Student student = studentRepository.findById(id);
        if (student.getId() == null){
            Printer.printError("There is no student with this  id !!!");
        } else return new StudentDto(student.getFirstName(),student.getLastName(),
                student.getNationalCode(), student.getDob(),student.getEntryDate(),student.getGpu());
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            studentRepository.delete(id);
        } catch (SQLException sqlException) {
            Printer.printError("There is a problem to connecting database!!!");
        }
    }

    @Override
    public int getCount() throws SQLException {
        return this.studentRepository.getCount();
    }

    @Override
    public Set<StudentDto> getAll() throws SQLException {
        Set<Student> students = this.studentRepository.getAll();
        Set<StudentDto> studentDtoSet = new HashSet<>();
        for (Student student : students){
            StudentDto studentDto = new StudentDto(student.getFirstName(),student.getLastName(),
                   student.getNationalCode(), student.getDob(),student.getEntryDate(),student.getGpu());
            studentDtoSet.add(studentDto);
        }
        return studentDtoSet;
    }
}
