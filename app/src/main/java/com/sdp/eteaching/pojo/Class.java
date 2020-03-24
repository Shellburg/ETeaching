package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Class implements Serializable {
    private Integer class_id;
    private String class_name;
    private String school_name;
    private Integer teacher_id;


    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }



    public Class(Integer class_id, String class_name, String school_name, Integer teacher_id, Integer student_id) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.school_name = school_name;
        this.teacher_id = teacher_id;
    }

    public Class() {
        super();
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", school_name='" + school_name + '\'' +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
