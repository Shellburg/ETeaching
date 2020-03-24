package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Teacher implements Serializable {
    private Integer teacher_id;
    private String teacher_name;
    private String gender;
    private String teacher_password;
    private String school_name;
    private String t_photo;
    private String t_phonenum;

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getGender() {
        return gender;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getT_photo() {
        return t_photo;
    }


    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public void setT_photo(String t_photo) {
        this.t_photo = t_photo;
    }

    public String getT_phonenum() {
        return t_phonenum;
    }

    public void setT_phonenum(String t_phonenum) {
        this.t_phonenum = t_phonenum;
    }

    public Teacher(Integer teacher_id, String teacher_name, String gender, String teacher_password, String school_name, String t_photo, String t_phonenum) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.gender = gender;
        this.teacher_password = teacher_password;
        this.school_name = school_name;
        this.t_photo = t_photo;
        this.t_phonenum = t_phonenum;
    }

    public Teacher() {
        super();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", gender='" + gender + '\'' +
                ", teacher_password='" + teacher_password + '\'' +
                ", school_name='" + school_name + '\'' +
                ", t_photo='" + t_photo + '\'' +
                ", t_phonenum='" + t_phonenum + '\'' +
                '}';
    }
}
