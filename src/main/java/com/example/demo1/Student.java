package com.example.demo1;

public class Student {
    private Integer sid;

    private String sname;

    private Integer sAge;

    private String ssex;

    public Student(Integer sid,String sname, Integer sAge, String ssex) {
        this.sid=sid;
        this.sname = sname;
        this.sAge = sAge;
        this.ssex = ssex;
    }

    /**
     * @return sid
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * @return sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * @param sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * @return s_age
     */
    public Integer getsAge() {
        return sAge;
    }

    /**
     * @param sAge
     */
    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    /**
     * @return ssex
     */
    public String getSsex() {
        return ssex;
    }

    /**
     * @param ssex
     */
    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}