package com.example.tutor_app.Model_Classes;

public class JobExperince_List {

    private String jobtitle;
    private String orgname;
    private String fromto;
    private String till;

    public JobExperince_List(String jobtitle, String orgname, String fromto, String till) {
        this.jobtitle = jobtitle;
        this.orgname = orgname;
        this.fromto = fromto;
        this.till = till;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }
}
