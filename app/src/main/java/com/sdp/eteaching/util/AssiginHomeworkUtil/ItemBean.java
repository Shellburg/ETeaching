package com.sdp.eteaching.util.AssiginHomeworkUtil;

public class ItemBean {
    //private int pictureId;
    private Integer class_id_homework;
    private String class_name_homework;
    private String school_name_homework;
    private boolean isSelect;
    private boolean isShowCheckBox;

    public boolean isShowCheckBox() {
        return isShowCheckBox;
    }

    public void setIsShowCheckBox(boolean isShowCheckBox) {
        this.isShowCheckBox = isShowCheckBox;
    }

    public ItemBean(Integer class_id_homework, String class_name_homework, String school_name_homework, boolean isSelect, boolean isShowCheckBox) {
        this.class_id_homework = class_id_homework;
        this.class_name_homework = class_name_homework;
        this.school_name_homework = school_name_homework;
        this.isSelect = isSelect;
        this.isShowCheckBox = isShowCheckBox;
    }


    //    public int getPictureId() {
//        return pictureId;
//    }
//
//    public void setPictureId(int pictureId) {
//        this.pictureId = pictureId;
//    }

    public Integer getClass_id_homework() {
        return class_id_homework;
    }

    public void setClass_id_homework(Integer class_id_homework) {
        this.class_id_homework = class_id_homework;
    }

    public String getClass_name_homework() {
        return class_name_homework;
    }

    public void setClass_name_homework(String class_name_homework) {
        this.class_name_homework = class_name_homework;
    }

    public String getSchool_name_homework() {
        return school_name_homework;
    }

    public void setSchool_name_homework(String school_name_homework) {
        this.school_name_homework = school_name_homework;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setShowCheckBox(boolean showCheckBox) {
        isShowCheckBox = showCheckBox;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}

