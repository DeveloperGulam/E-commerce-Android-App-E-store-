package com.dattaprabodhinee.estore.Models;

public class UserResponseModel {
    UserData userdata;
    String response;
    OtpData otpdata;

    public UserResponseModel(UserData userdata, String response) {
        this.userdata = userdata;
        this.response = response;
    }

    public UserResponseModel(String response, OtpData otpdata) {
        this.response = response;
        this.otpdata = otpdata;
    }

    public UserData getUserdata() {
        return userdata;
    }

    public void setUserdata(UserData userdata) {
        this.userdata = userdata;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public OtpData getOtpdata() {
        return otpdata;
    }

    public void setOtpdata(OtpData otpdata) {
        this.otpdata = otpdata;
    }
}
