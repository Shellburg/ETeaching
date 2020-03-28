package com.sdp.eteaching.pojo;

public class Teacher {
    /**
     * 管理员表
     * @author
     * @date 2017年11月27日23:07:00
     */
    private long ad_id;
    private String name;//姓名
    private String loginname;//登录名
    private String pwd;//登陆密码
    private String adminLevel;//管理级别（系统管理员，教师）

    //无参构造函数
    public Teacher() {

    }

    public Teacher(String name, String loginname, String pwd, String adminLevel) {
        super();
        this.name = name;
        this.loginname = loginname;
        this.pwd = pwd;
        this.adminLevel = adminLevel;
    }

    /**
     * get set函数
     * @return
     */
    public long getAd_id() {
        return ad_id;
    }
    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLoginname() {
        return loginname;
    }
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }




}