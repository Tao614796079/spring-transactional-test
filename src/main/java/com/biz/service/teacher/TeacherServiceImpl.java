package com.biz.service.teacher;

import com.biz.po.Teacher;
import com.biz.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TeacherServiceImpl
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        //        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addTeacher2(Teacher teacher) {
        teacherRepository.save(teacher);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addTeacher3(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addTeacher4(Teacher teacher) {
        teacherRepository.save(teacher);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addTeacher6(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addTeacher7(Teacher teacher) {
        teacherRepository.save(teacher);
    }

}
