package com.biz.po;

import javax.persistence.*;

/**
 * Teacher
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
@Entity
@Table
public class Teacher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String name;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
