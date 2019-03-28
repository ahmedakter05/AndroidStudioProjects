package com.aa.taseen.kobitashomogrodemo1;

public class DataModel {
    String id;
    String title;
    String desc;
    String image;
    String others;

    public DataModel() {

    }

    public DataModel(String id, String title, String desc, String image, String others) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.others = others;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
