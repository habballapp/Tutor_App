package com.example.tutor_app.Model_Classes;


import androidx.annotation.NonNull;

public class ViewStudent_List {

    private String Name;
    private String ClassName;
    private String Subjects;

    public ViewStudent_List(String Name, String className, String subjects) {
        Name = Name;
        ClassName = className;
        this.Subjects = subjects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getSubjects() {
        return Subjects;
    }

    public void setSubjects(String subjects) {
        this.Subjects = subjects;
    }
}
