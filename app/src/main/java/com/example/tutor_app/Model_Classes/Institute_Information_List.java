package com.example.tutor_app.Model_Classes;

public class Institute_Information_List {

    private String classtype;
    private String subtype;

    public Institute_Information_List(String classtype, String subtype) {
        this.classtype = classtype;
        this.subtype = subtype;
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
