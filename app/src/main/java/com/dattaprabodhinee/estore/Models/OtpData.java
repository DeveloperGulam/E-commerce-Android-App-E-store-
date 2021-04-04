package com.dattaprabodhinee.estore.Models;

public class OtpData {
    int otpcode;
    String otpnumber, insert_id, message;

    public OtpData(int otpcode, String otpnumber, String insert_id, String message) {
        this.otpcode = otpcode;
        this.otpnumber = otpnumber;
        this.insert_id = insert_id;
        this.message = message;
    }

    public int getOtpcode() {
        return otpcode;
    }

    public void setOtpcode(int otpcode) {
        this.otpcode = otpcode;
    }

    public String getOtpnumber() {
        return otpnumber;
    }

    public void setOtpnumber(String otpnumber) {
        this.otpnumber = otpnumber;
    }

    public String getInsert_id() {
        return insert_id;
    }

    public void setInsert_id(String insert_id) {
        this.insert_id = insert_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
