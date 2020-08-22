package com.example.tutor_app.Model_Classes;


public class InstitudeView_List {

    private String Id;
    private String Name;
    private String Type;
    private String ClassName;
    private String Subjects;
    private String Status;
    private String JobId;

    public InstitudeView_List(String id, String name, String type, String className, String subjects, String status, String jobId) {
        Id = id;
        Name = name;
        Type = type;
        ClassName = className;
        Subjects = subjects;
        Status = status;
        JobId = jobId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
        Subjects = subjects;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }
}
