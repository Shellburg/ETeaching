package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer student_id;
    private String student_name;
    private String gender;
    private String student_password;
    private String s_photo;
    private String s_phonenum;
    private String school_name;
    private Integer class_id;

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    public String getS_photo() {
        return s_photo;
    }

    public void setS_photo(String s_photo) {
        this.s_photo = s_photo;
    }

    public String getS_phonenum() {
        return s_phonenum;
    }

    public void setS_phonenum(String s_phonenum) {
        this.s_phonenum = s_phonenum;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public Student() {
        super();
    }

    public Student(Integer student_id, String student_name, String gender, String student_password, String s_photo, String s_phonenum, String school_name, Integer class_id) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.gender = gender;
        this.student_password = student_password;
        this.s_photo = s_photo;
        this.s_phonenum = s_phonenum;
        this.school_name = school_name;
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", gender='" + gender + '\'' +
                ", student_password='" + student_password + '\'' +
                ", s_photo='" + s_photo + '\'' +
                ", s_phonenum='" + s_phonenum + '\'' +
                ", school_name='" + school_name + '\'' +
                ", class_id=" + class_id +
                '}';
    }
}
