package com.example.demo1;

import java.util.List;

/**
 * @ClassName StudentReq
 * @Description TODO
 * @Author HDXYA
 * @Date 2020/4/5 15:00
 * @Version 1.0
 **/
public class StudentReq {
    private Integer sid;
    private List<Integer> sids;

    private String sname;

    public List<Integer> getSids() {
        return sids;
    }

    public void setSids(List<Integer> sids) {
        this.sids = sids;
    }

    private Integer sage;

    private String ssex;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}
