package com.example.tutor_app.Model_Classes;

public class AreaFragment_List {

    private String classtoteach;
    private String prefsubject;
    private String prefarea;

    public String getClasstoteach() {
        return classtoteach;
    }

    public void setClasstoteach(String classtoteach) {
        this.classtoteach = classtoteach;
    }

    public String getPrefsubject() {
        return prefsubject;
    }

    public void setPrefsubject(String prefsubject) {
        this.prefsubject = prefsubject;
    }

    public String getPrefarea() {
        return prefarea;
    }

    public void setPrefarea(String prefarea) {
        this.prefarea = prefarea;
    }

    public AreaFragment_List(String classtoteach, String prefsubject, String prefarea) {
        this.classtoteach = classtoteach;
        this.prefsubject = prefsubject;
        this.prefarea = prefarea;
    }
}
