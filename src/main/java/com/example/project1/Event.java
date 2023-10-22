package com.example.project1;

public class Event {public int id_;
    public String sEvent_;
    public String sYear_;
    public String sMonth_;
    public String sDay_;
    public String sHour_;
    public String sMinute_;

    public Event(int id_, String sEvent_, String sYear_, String sMonth_, String sDay_,String sHour_,String sMinute_)
    {
        this.id_ = id_;
        this.sEvent_ = sEvent_;
        this.sYear_ = sYear_;
        this.sMonth_ = sMonth_;
        this.sDay_ = sDay_;
        this.sHour_ = sHour_;
        this.sMinute_ = sMinute_;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getsEvent_() {
        return sEvent_;
    }

    public void setsEvent_(String sEvent_) {
        this.sEvent_ = sEvent_;
    }

    public String getsYear_() {
        return sYear_;
    }

    public void setsYear_(String sYear) {
        this.sYear_ = sYear;
    }

    public String getsMonth_() {
        return sMonth_;
    }

    public void setsMonth_(String sMonth_) {
        this.sMonth_ = sMonth_;
    }

    public String getsDay_() {
        return sDay_;
    }

    public void setsDay_(String sDay_) {
        this.sDay_ = sDay_;
    }

    public String getsHour_() {
        return sHour_;
    }

    public void setsHour_(String sHour_) {
        this.sHour_ = sHour_;
    }

    public String getsMinute_() {
        return sMinute_;
    }

    public void setsMinute_(String sMinute_) {
        this.sMinute_ = sMinute_;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id_=" + id_ +
                ", sEvent_='" + sEvent_ + '\'' +
                ", sYear_='" + sYear_ + '\'' +
                ", sMonth_='" + sMonth_ + '\'' +
                ", sDay_='" + sDay_ + '\'' +
                ", sHour_='" + sHour_ + '\'' +
                ", sMinute_='" + sMinute_ + '\'' +
                '}';
    }
}