package com.biz.service.student;

import com.biz.po.Student;
import com.biz.po.Teacher;
import com.biz.repository.StudentRepository;
import com.biz.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * StudentServiceImpl
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherService teacherService;


    @Transactional(propagation = Propagation.REQUIRED)//会开启事务，在事务范围内使用则使用同一个事务，否则开启新事务
    public void addStudent(Student student, Teacher teacher) {

        studentRepository.save(student);
        teacherService.addTeacher(teacher);
        //        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addStudent2(Student student, Teacher teacher) {
        studentRepository.save(student);
        teacherService.addTeacher2(teacher);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addStudent3(Student student, Teacher teacher) {
        studentRepository.save(student);
        teacherService.addTeacher3(teacher);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent4(Student student, Teacher teacher) {
        studentRepository.save(student);
        teacherService.addTeacher4(teacher);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addStudent5(Student student) {
        studentRepository.save(student);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent6(Student student, Teacher teacher) {
        studentRepository.save(student);
        teacherService.addTeacher6(teacher);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addStudent7(Student student, Teacher teacher) {
        studentRepository.save(student);
        teacherService.addTeacher7(teacher);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent8(Student student, Teacher teacher) {
        studentRepository.save(student);
        this.addStudent8a();
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void addStudent8a() {
        Student student = new Student("bbbb");
        studentRepository.save(student);
    }
}
