package com.example.sqllitedatabase;

public class Record {

    private String name,dob,qualification;

    public Record(){}

    public Record( String name, String dob,String qualification) {

        this.name = name;
        this.dob = dob;
        this.qualification = qualification;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
