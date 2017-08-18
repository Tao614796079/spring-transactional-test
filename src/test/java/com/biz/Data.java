package com.biz;

import com.biz.po.Student;
import com.biz.po.Teacher;

/**
 * Data
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
public class Data {
    private Student student;
    private Teacher teacher;

    public Data() {
        this.student = new Student("zhangsan");
        this.teacher = new Teacher("lisi");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
