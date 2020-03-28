package com.sdp.eteaching.pojo;

public class Student {
    /*学生表s
     * @author
     * @date 2017年11月28日19:44:25
     * */
    //属性值
    private int stu_id;
    private String stu_num;//学号
    private String stu_name;//姓名
    private String psw;//登录密码
    private String phone;//电话
    private String eamil;//电子邮件
    private String gender;//性别
    private String department;//系别
    private String classT;//班级

    /**
     * 构造函数
     */
    public Student() {
    }

    public Student(String stu_num) {
        super();
        this.stu_num = stu_num;
    }


    /**
     * get set
     */

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassT() {
        return classT;
    }

    public void setClassT(String classT) {
        this.classT = classT;
    }
}