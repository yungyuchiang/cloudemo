package com.yungyu.bpmserver.entity;

import java.io.Serializable;

public class Leave implements Serializable {

    private Integer id;
    private String name;
    private Integer days;
    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days=" + days +
                ", reason='" + reason + '\'' +
                '}';
    }
}
