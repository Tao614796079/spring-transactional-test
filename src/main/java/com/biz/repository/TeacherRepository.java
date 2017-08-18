package com.biz.repository;

import com.biz.po.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeacherRepository
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
