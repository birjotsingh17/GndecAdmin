package com.birjot.gndec_sports_admin.Model;

import android.widget.Spinner;

/**
 * Created by birjot on 5/12/17.
 */

public class resultupload {

    public Spinner spin;
    public String firstname;
    public String secondname;
    private String thirdname;
    public String uniroll1;
    public String uniroll2;
    public String uniroll3;
    private String reg_token;
    public String datee;

    public resultupload() {
    }

    public Spinner getSpin() {
        return spin;
    }

    public void setSpin(Spinner spin) {
        this.spin = spin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }

    public String getUniroll1() {
        return uniroll1;
    }

    public void setUniroll1(String uniroll1) {
        this.uniroll1 = uniroll1;
    }

    public String getUniroll2() {
        return uniroll2;
    }

    public void setUniroll2(String uniroll2) {
        this.uniroll2 = uniroll2;
    }

    public String getUniroll3() {
        return uniroll3;
    }

    public void setUniroll3(String uniroll3) {
        this.uniroll3 = uniroll3;
    }

    public String getReg_token() {
        return reg_token;
    }

    public void setReg_token(String reg_token) {
        this.reg_token = reg_token;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    @Override
    public String toString() {
        return "resultupload{" +
                "spin=" + spin +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", thirdname='" + thirdname + '\'' +
                ", uniroll1='" + uniroll1 + '\'' +
                ", uniroll2='" + uniroll2 + '\'' +
                ", uniroll3='" + uniroll3 + '\'' +
                ", reg_token='" + reg_token + '\'' +
                ", datee='" + datee + '\'' +
                '}';
    }
}
