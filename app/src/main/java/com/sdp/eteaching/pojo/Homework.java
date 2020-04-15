package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Homework implements Serializable {
    private Integer homework_id;
    private Integer teacher_id;
    private Integer student_id;
    private Integer class_id;
    private String start_time;
    private String end_time;
    private String deadline;
    private String homework_path;
    private String comment_path;
    private String homework_description;


    public Homework(Integer homework_id, Integer teacher_id, Integer student_id, Integer class_id, String start_time, String end_time, String deadline, String homework_path, String comment_path, String homework_description) {
        this.homework_id = homework_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.class_id = class_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.deadline = deadline;
        this.homework_path = homework_path;
        this.comment_path = comment_path;
        this.homework_description = homework_description;
    }

    public Homework() {
        super();
    }

    public String getHomework_description() {
        return homework_description;
    }

    public void setHomework_description(String homework_description) {
        this.homework_description = homework_description;
    }

    public Integer getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(Integer homework_id) {
        this.homework_id = homework_id;
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

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getHomework_path() {
        return homework_path;
    }

    public void setHomework_path(String homework_path) {
        this.homework_path = homework_path;
    }

    public String getComment_path() {
        return comment_path;
    }

    public void setComment_path(String comment_path) {
        this.comment_path = comment_path;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "homework_id=" + homework_id +
                ", teacher_id=" + teacher_id +
                ", student_id=" + student_id +
                ", class_id=" + class_id +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", deadline='" + deadline + '\'' +
                ", homework_path='" + homework_path + '\'' +
                ", comment_path='" + comment_path + '\'' +
                ", homework_description='" + homework_description + '\'' +
                '}';
    }
}
