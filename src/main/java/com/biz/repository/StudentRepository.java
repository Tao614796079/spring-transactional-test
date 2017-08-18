package com.biz.repository;

import com.biz.po.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentRepository
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
